package uk.gov.hmcts.reform.hmc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.domain.model.enums.HearingStatus;
import uk.gov.hmcts.reform.hmc.helper.GetHearingRequestToCsvMapper;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;
import uk.gov.hmcts.reform.hmc.repository.CaseHearingRequestRepository;

import java.io.File;
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
    public File createCsvFileForExceptions() {
        List<String> statuses = List.of(HearingStatus.EXCEPTION.name());
        List<HearingRequestForCsv> hearingRequestForCsvs = createCsvObjectsForGivenStatuses(statuses);
        return createCsvFile(hearingRequestForCsvs);
    }

    @Override
    public File createCsvFileForAwaitingActuals() {
        List<String> statuses = List.of(HearingStatus.LISTED.name(), HearingStatus.UPDATE_REQUESTED.name(),
                                            HearingStatus.UPDATE_SUBMITTED.name());
        List<HearingRequestForCsv> hearingRequestForCsvs = createCsvObjectsForGivenStatuses(statuses);
        // filter

        return createCsvFile(hearingRequestForCsvs);
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
        log.info("Created {} CSV Request bjects.", csvRequests.size());
        return csvRequests;
    }

    @Override
    public File createCsvFile(List<HearingRequestForCsv> hearingRequestForCsvs) {

        return null;
    }


}
