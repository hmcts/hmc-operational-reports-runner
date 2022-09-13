package uk.gov.hmcts.reform.hmc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import uk.gov.hmcts.reform.hmc.ApplicationParams;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.data.HearingEntity;
import uk.gov.hmcts.reform.hmc.domain.model.enums.HearingStatus;
import uk.gov.hmcts.reform.hmc.helper.GetHearingRequestToCsvMapper;
import uk.gov.hmcts.reform.hmc.helper.HearingActualsHelper;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;
import uk.gov.hmcts.reform.hmc.repository.CaseHearingRequestRepository;
import uk.gov.hmcts.reform.hmc.utils.TestingUtil;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OperationalReportsServiceImplTest {

    @Mock
    private ApplicationParams applicationParams;

    @Mock
    private HearingActualsHelper hearingActualsHelper;

    @Mock
    private CaseHearingRequestRepository caseHearingRequestRepository;

    @Mock
    private GetHearingRequestToCsvMapper getHearingRequestToCsvMapper;

    @InjectMocks
    private OperationalReportsServiceImpl operationalReportsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hearingActualsHelper = new HearingActualsHelper(applicationParams);
        operationalReportsService = new OperationalReportsServiceImpl(
                caseHearingRequestRepository,
                getHearingRequestToCsvMapper,
                hearingActualsHelper
        );
    }

    @Test
    void createCsvData() {
        List<HearingRequestForCsv> testData = createHearingRequestForCsvTestData();
        String csvData = operationalReportsService.createCsvData(testData);
        assertTrue(csvData.length() > 0);
    }

    @Test
    void getAwaitingActualsRequests() {
        CaseHearingRequestEntity entity1 = createCaseHearingEntity(2000000001L,
                9000000000000001L, HearingStatus.UPDATE_SUBMITTED);
        CaseHearingRequestEntity entity2 = createCaseHearingEntity(2000000002L,
                9000000000000002L, HearingStatus.UPDATE_REQUESTED);
        CaseHearingRequestEntity entity3 = createCaseHearingEntity(2000000003L,
                9000000000000003L, HearingStatus.UPDATE_REQUESTED);
        List<CaseHearingRequestEntity> entities = List.of(entity1, entity2, entity3);

        List<CaseHearingRequestEntity> awaitingActualsEntities =
                operationalReportsService.getAwaitingActualsCases(entities);

        // TODO: filtration!!
        assertEquals(0, awaitingActualsEntities.size(), 0);
    }

    private CaseHearingRequestEntity createCaseHearingEntity(Long hearingId, Long caseHearingId, HearingStatus status) {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(hearingId);
        hearingEntity.setStatus(status.name());
        CaseHearingRequestEntity caseHearingEntity = TestingUtil.caseHearingRequestEntityWithPartyOrg();
        caseHearingEntity.setCaseHearingID(caseHearingId);
        caseHearingEntity.setHearing(hearingEntity);
        hearingEntity.setCaseHearingRequests(List.of(caseHearingEntity));
        return caseHearingEntity;
    }

    private List<HearingRequestForCsv> createHearingRequestForCsvTestData() {
        HearingRequestForCsv hearingRequest1 = createHearingRequestForCsv1();
        HearingRequestForCsv hearingRequest2 = createHearingRequestForCsv2();
        HearingRequestForCsv hearingRequest3 = createHearingRequestForCsv3();

        return List.of(hearingRequest1, hearingRequest2, hearingRequest3);
    }

    private HearingRequestForCsv createHearingRequestForCsv1() {
        HearingRequestForCsv hearingRequest = new HearingRequestForCsv();
        hearingRequest.setHearingId("2000000001");
        hearingRequest.setHearingId(LocalDateTime.now().toString());
        hearingRequest.setCaseName("CASE 1");
        hearingRequest.setFirstScheduledHearingDate(LocalDateTime.now().toString());
        hearingRequest.setHearingResponseReceivedDateTime(LocalDateTime.now().toString());
        hearingRequest.setListAssistId("LA00001");
        hearingRequest.setCaseReference("CASEREF0001");
        hearingRequest.setHearingStatus(HearingStatus.EXCEPTION.name());
        return hearingRequest;
    }

    private HearingRequestForCsv createHearingRequestForCsv2() {
        HearingRequestForCsv hearingRequest = new HearingRequestForCsv();
        hearingRequest.setHearingId("2000000002");
        hearingRequest.setHearingId(LocalDateTime.now().toString());
        hearingRequest.setCaseName("CASE 2");
        hearingRequest.setFirstScheduledHearingDate(LocalDateTime.now().toString());
        hearingRequest.setHearingResponseReceivedDateTime(LocalDateTime.now().toString());
        hearingRequest.setListAssistId("LA00002");
        hearingRequest.setCaseReference("CASEREF0002");
        hearingRequest.setHearingStatus(HearingStatus.UPDATE_SUBMITTED.name());
        return hearingRequest;
    }

    private HearingRequestForCsv createHearingRequestForCsv3() {
        HearingRequestForCsv hearingRequest = new HearingRequestForCsv();
        hearingRequest.setHearingId("2000000003");
        hearingRequest.setHearingId(LocalDateTime.now().toString());
        hearingRequest.setCaseName("CASE3");
        hearingRequest.setFirstScheduledHearingDate(LocalDateTime.now().toString());
        hearingRequest.setHearingResponseReceivedDateTime(LocalDateTime.now().toString());
        hearingRequest.setListAssistId("LA00003");
        hearingRequest.setCaseReference("CASEREF0003");
        hearingRequest.setHearingStatus(HearingStatus.UPDATE_REQUESTED.name());
        return hearingRequest;
    }
}