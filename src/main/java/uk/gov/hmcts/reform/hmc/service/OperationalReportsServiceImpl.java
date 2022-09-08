package uk.gov.hmcts.reform.hmc.service;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.domain.model.enums.HearingStatus;
import uk.gov.hmcts.reform.hmc.helper.GetHearingRequestToCsvMapper;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;
import uk.gov.hmcts.reform.hmc.repository.CaseHearingRequestRepository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class OperationalReportsServiceImpl implements OperationalReportsService {

    private static final String PATTERN = "dd-MM-yyyy";
    private CaseHearingRequestRepository caseHearingRequestRepository;
    private GetHearingRequestToCsvMapper getHearingRequestToCsvMapper;

    @Autowired
    OperationalReportsServiceImpl(CaseHearingRequestRepository caseHearingRequestRepository,
                                  GetHearingRequestToCsvMapper getHearingRequestToCsvMapper) {
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


}
