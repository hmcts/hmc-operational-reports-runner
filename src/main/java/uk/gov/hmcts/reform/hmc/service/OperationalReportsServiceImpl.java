package uk.gov.hmcts.reform.hmc.service;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.hmc.ApplicationParams;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.data.HearingDayDetailsEntity;
import uk.gov.hmcts.reform.hmc.data.HearingResponseEntity;
import uk.gov.hmcts.reform.hmc.domain.model.enums.HearingStatus;
import uk.gov.hmcts.reform.hmc.helper.GetHearingRequestToCsvMapper;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;
import uk.gov.hmcts.reform.hmc.repository.CaseHearingRequestRepository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
public class OperationalReportsServiceImpl implements OperationalReportsService {

    private static final String PATTERN = "dd-MM-yyyy";

    private final ApplicationParams appParams;
    private CaseHearingRequestRepository caseHearingRequestRepository;
    private GetHearingRequestToCsvMapper getHearingRequestToCsvMapper;

    @Autowired
    OperationalReportsServiceImpl(ApplicationParams appParams,
                                  CaseHearingRequestRepository caseHearingRequestRepository,
                                  GetHearingRequestToCsvMapper getHearingRequestToCsvMapper) {
        this.appParams = appParams;
        this.caseHearingRequestRepository = caseHearingRequestRepository;
        this.getHearingRequestToCsvMapper = getHearingRequestToCsvMapper;
    }

    @Override
    public File createCsvDataForExceptions() throws IOException {
        List<String> statuses = List.of(HearingStatus.EXCEPTION.name());
        List<HearingRequestForCsv> hearingRequestForCsvs = createCsvObjectsForGivenStatuses(statuses);
        String csv = createCsvData(hearingRequestForCsvs);
        return generateFileFromString(csv);
    }

    @Override
    public File createCsvDataForAwaitingActuals() throws IOException {
        List<String> statuses = List.of(HearingStatus.LISTED.name(),
                HearingStatus.UPDATE_REQUESTED.name(),
                HearingStatus.UPDATE_SUBMITTED.name());
        List<CaseHearingRequestEntity> caseHearingRequestEntities =  getHearingsForStatuses(statuses);
        List<CaseHearingRequestEntity> filteredCaseHearingRequests =
                filterCaseHearingRequests(caseHearingRequestEntities);
        String csv = createCsvData(mapToCsvObjects(filteredCaseHearingRequests));
        return generateFileFromString(csv);
    }


    @Override
    public List<CaseHearingRequestEntity> filterCaseHearingRequests(
            List<CaseHearingRequestEntity> caseHearingRequestEntities) {
        List<CaseHearingRequestEntity> filteredEntities = new ArrayList<>();

        caseHearingRequestEntities.stream().forEach(e -> {
            log.debug("caseHearingRequest id: {}; status {}", e.getCaseHearingID(), e.getHearing().getStatus());
            Optional<HearingResponseEntity> hearingResponse = e.getHearing().getLatestHearingResponse();
            if (hearingResponse.isPresent()) {
                HearingResponseEntity latestHearingResponse = hearingResponse.get();
                log.debug("latestHearingResponse id: {}", latestHearingResponse.getHearingResponseId());
                Optional<HearingDayDetailsEntity> hearingDayDetails =
                        latestHearingResponse.getLatestHearingDayDetails();
                if (latestHearingResponse.hasHearingDayDetails() && hearingDayDetails.isPresent()) {
                    HearingDayDetailsEntity hearingDayDetailsEntity = hearingDayDetails.get();
                    log.debug("latestHearingDayDetails id: {}", hearingDayDetailsEntity.getHearingDayId());
                    if (isToBeIncluded(hearingDayDetailsEntity.getEndDateTime())) {
                        log.debug("isToBeIncluded == true");
                        filteredEntities.add(e);
                    } else {
                        log.debug("isToBeIncluded == false");
                    }
                }
            }
        });

        log.info("Filtered to {} caseHearingRequests.", filteredEntities.size());
        return filteredEntities;
    }

    @Override
    public List<HearingRequestForCsv> createCsvObjectsForGivenStatuses(List<String> statuses) {
        List<CaseHearingRequestEntity> entities = getHearingsForStatuses(statuses);
        log.info("Found {} caseHearingRequests.", entities.size());
        return mapToCsvObjects(entities);
    }

    @Override
    public List<CaseHearingRequestEntity> getHearingsForStatuses(List<String> statuses) {
        List<CaseHearingRequestEntity> entities =
            caseHearingRequestRepository.getCaseHearingDetailsWithStatuses(statuses);
        log.info("Found {} caseHearingRequests.", entities.size());
        return entities;
    }

    @Override
    public List<HearingRequestForCsv> mapToCsvObjects(List<CaseHearingRequestEntity> caseHearings) {
        List<HearingRequestForCsv> csvRequests = new ArrayList<>();
        caseHearings.stream().forEach(requestEntity ->
                                          csvRequests.add(getHearingRequestToCsvMapper.toHearingRequestForCsv(
                                              requestEntity))
        );
        log.info("Created {} CSV Request objects.", csvRequests.size());
        return csvRequests;
    }

    @Override
    public String createCsvData(List<HearingRequestForCsv> hearingRequestForCsvs) {
        final CsvMapper csvMapper = new CsvMapper();
        final CsvSchema schemaWithHeader = csvMapper.schemaFor(HearingRequestForCsv.class)
            .withHeader();
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(csvMapper.writer(schemaWithHeader).writeValueAsString(hearingRequestForCsvs));
        } catch (Exception e) {
            log.error("Exception converting to CSV");
        }
        log.info(sb.toString());
        return sb.toString();
    }

    @Override
    public File generateFileFromString(String data) throws IOException {
        File csvOutputFile = new File(new SimpleDateFormat(PATTERN, Locale.ENGLISH)
                                          .format(new Date()) + ".csv");
        FileUtils.writeStringToFile(csvOutputFile, data, Charset.defaultCharset());
        return csvOutputFile;
    }

    @Override
    public boolean isToBeIncluded(LocalDateTime endDate) {
        long configuredNumberOfDays = appParams.getConfiguredNumberOfDays();
        return isToBeIncluded(endDate, LocalDate.now(), configuredNumberOfDays);
    }

    @Override
    public boolean isToBeIncluded(LocalDateTime endDateTime, LocalDate now, Long configuredNumberOfDays) {
        if (endDateTime.toLocalDate().isAfter(now)) {
            log.debug("endDateTime {} is after now {}", endDateTime, now);
            return false;
        }
        log.debug("Days between endDateTime {} and now {} = {}. configuredNumberOfDays {}",
                endDateTime, now, DAYS.between(endDateTime.toLocalDate(), now), configuredNumberOfDays);
        return (DAYS.between(endDateTime.toLocalDate(), now) > configuredNumberOfDays);
    }

}
