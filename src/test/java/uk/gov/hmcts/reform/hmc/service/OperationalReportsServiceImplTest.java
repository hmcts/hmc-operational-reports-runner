package uk.gov.hmcts.reform.hmc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import uk.gov.hmcts.reform.hmc.ApplicationParams;
import uk.gov.hmcts.reform.hmc.domain.model.enums.HearingStatus;
import uk.gov.hmcts.reform.hmc.helper.GetHearingRequestToCsvMapper;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;
import uk.gov.hmcts.reform.hmc.repository.CaseHearingRequestRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OperationalReportsServiceImplTest {

    private ApplicationParams appParams;

    private OperationalReportsService operationalReportsService;

    @Mock
    private CaseHearingRequestRepository caseHearingRequestRepository;

    @Mock
    private GetHearingRequestToCsvMapper getHearingRequestToCsvMapper;

    @BeforeEach
    void setUp() {
        operationalReportsService = new OperationalReportsServiceImpl(
                appParams,
                caseHearingRequestRepository,
                getHearingRequestToCsvMapper
        );
    }

    @Test
    void createCsvData() {
        List<HearingRequestForCsv> testData = createHearingRequestForCsvTestData();
        String csvData = operationalReportsService.createCsvData(testData);
        assertTrue(csvData.length() > 0);
    }

    @Test
    void isToBeIncluded() {
        // All the cases from HMAN-390
        LocalDateTime endDateTime = LocalDateTime.of(2022,2, 15, 13, 01);
        LocalDate now = LocalDate.of(2022, 2, 16);
        assertTrue(operationalReportsService.isToBeIncluded(endDateTime, now, 0L));

        endDateTime = LocalDateTime.of(2022,2, 15, 13, 01);
        now = LocalDate.of(2022, 2, 16);
        assertFalse(operationalReportsService.isToBeIncluded(endDateTime, now, 2L));

        endDateTime = LocalDateTime.of(2022,2, 17, 13, 01);
        now = LocalDate.of(2022, 2, 16);
        assertFalse(operationalReportsService.isToBeIncluded(endDateTime, now, 2L));

        endDateTime = LocalDateTime.of(2022,2, 14, 13, 01);
        now = LocalDate.of(2022, 2, 16);
        assertTrue(operationalReportsService.isToBeIncluded(endDateTime, now, 1L));

        endDateTime = LocalDateTime.of(2022,2, 14, 13, 01);
        now = LocalDate.of(2022, 2, 16);
        assertTrue(operationalReportsService.isToBeIncluded(endDateTime, now, 1L));

        endDateTime = LocalDateTime.of(2022,2, 16, 13, 01);
        now = LocalDate.of(2022, 2, 16);
        assertFalse(operationalReportsService.isToBeIncluded(endDateTime, now, 1L));

        endDateTime = LocalDateTime.of(2022,2, 17, 13, 01);
        now = LocalDate.of(2022, 2, 16);
        assertFalse(operationalReportsService.isToBeIncluded(endDateTime, now, 0L));
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