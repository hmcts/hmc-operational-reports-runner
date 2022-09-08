package uk.gov.hmcts.reform.hmc.service;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.domain.model.enums.HearingStatus;
import uk.gov.hmcts.reform.hmc.helper.GetHearingRequestToCsvMapper;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;
import uk.gov.hmcts.reform.hmc.repository.CaseHearingRequestRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OperationalReportsServiceImpl implements OperationalReportsService {

    private CaseHearingRequestRepository caseHearingRequestRepository;
    private GetHearingRequestToCsvMapper getHearingRequestToCsvMapper;

    @Autowired
    OperationalReportsServiceImpl(CaseHearingRequestRepository caseHearingRequestRepository,
                                  GetHearingRequestToCsvMapper getHearingRequestToCsvMapper) {
        this.caseHearingRequestRepository = caseHearingRequestRepository;
        this.getHearingRequestToCsvMapper = getHearingRequestToCsvMapper;
    }

    @Override
    public String createCsvDataForExceptions() {
        List<String> statuses = List.of(HearingStatus.EXCEPTION.name());
        List<HearingRequestForCsv> hearingRequestForCsvs = createCsvObjectsForGivenStatuses(statuses);
        return createCsvData(hearingRequestForCsvs);
    }

    @Override
    public String createCsvDataForAwaitingActuals() {
        List<String> statuses = List.of(HearingStatus.LISTED.name(),
                                        HearingStatus.UPDATE_REQUESTED.name(),
                                        HearingStatus.UPDATE_SUBMITTED.name());
        List<CaseHearingRequestEntity> caseHearingRequestEntities =  getHearingsForStatuses(statuses);
        List<CaseHearingRequestEntity> filteredCaseHearingRequests =
                filterCaseHearingRequests(caseHearingRequestEntities);
        List<HearingRequestForCsv> hearingRequestForCsvs = mapToCsvObjects(filteredCaseHearingRequests);
        return createCsvData(hearingRequestForCsvs);
    }

    @Override
    public List<CaseHearingRequestEntity> filterCaseHearingRequests(
            List<CaseHearingRequestEntity> caseHearingRequestEntities) {

        return caseHearingRequestEntities;
        //        List<CaseHearingRequestEntity> filteredEntities = new ArrayList<>();
        //        caseHearingRequestEntities.stream().forEach(e -> {
        //            LocalDate
        //            if (e.getHearingWindowEndDateRange().)
        //        });
        //
        //        log.info("Found {} caseHearingRequests.", entities.size());
        //        return mapToCsvObjects(entities);
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
            csvRequests.add(getHearingRequestToCsvMapper.toHearingRequestForCsv(requestEntity))
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


}
