package uk.gov.hmcts.reform.hmc.service;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
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
    public List<CaseHearingRequestEntity> getHearingsForStatuses(List<String> statuses) {
        return caseHearingRequestRepository.getCaseHearingDetailsWithStatuses(statuses);
    }

    @Override
    public List<HearingRequestForCsv> mapToCsvObjects(List<CaseHearingRequestEntity> caseHearings) {
        List<HearingRequestForCsv> csvRequests = new ArrayList<>();
        caseHearings.stream().forEach(requestEntity ->
            csvRequests.add(getHearingRequestToCsvMapper.toHearingRequestForCsv(requestEntity))
        );
        return csvRequests;
    }

    @Override
    public File createCsvFile(List<HearingRequestForCsv> hearingRequestForCsvs) {

        return null;
    }


}
