package uk.gov.hmcts.reform.hmc.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.hmcts.reform.hmc.ApplicationParams;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.data.HearingDayDetailsEntity;
import uk.gov.hmcts.reform.hmc.data.HearingEntity;
import uk.gov.hmcts.reform.hmc.data.HearingResponseEntity;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class GetHearingRequestToCsvMapperTest {

    @Mock
    private ApplicationParams applicationParams;

    @Mock
    private HearingActualsHelper hearingActualsHelper;

    @InjectMocks
    private GetHearingRequestToCsvMapper getHearingRequestToCsvMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getHearingRequestToCsvMapper =
                new GetHearingRequestToCsvMapper();
    }

    @Test
    void toHearingRequestForCsv() {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(2000000001L);

        HearingDayDetailsEntity hearingDayDetails =
            hearingDayDetailsEntities(LocalDateTime.parse("2020-08-10T12:20:00"),
                                      LocalDateTime.parse("2021-08-10T12:20:00"));
        HearingDayDetailsEntity hearingDayDetails1 =
            hearingDayDetailsEntities(LocalDateTime.parse("2020-08-11T12:20:00"),
                                      LocalDateTime.parse("2021-08-11T12:20:00"));

        HearingResponseEntity hearingResponse1 = hearingResponse(1, 2000);
        hearingResponse1.setHearingDayDetails(List.of(hearingDayDetails, hearingDayDetails1));
        HearingResponseEntity hearingResponse2 = hearingResponse(2, 2002);
        hearingResponse2.setHearingDayDetails(List.of(hearingDayDetails, hearingDayDetails1));
        HearingResponseEntity hearingResponse3 = hearingResponse(2, 2004);
        hearingResponse3.setHearingDayDetails(List.of(hearingDayDetails, hearingDayDetails1));
        hearingEntity.setHearingResponses(List.of(hearingResponse1, hearingResponse2, hearingResponse3));

        CaseHearingRequestEntity caseHearingRequestEntity = new CaseHearingRequestEntity();
        caseHearingRequestEntity.setHearing(hearingEntity);
        caseHearingRequestEntity.setHearingWindowEndDateRange(LocalDate.now());
        caseHearingRequestEntity.setHearingRequestReceivedDateTime(LocalDateTime.now());
        HearingRequestForCsv hearingRequestForCsv =
                getHearingRequestToCsvMapper.toHearingRequestForCsv(caseHearingRequestEntity);
        assertEquals(hearingRequestForCsv.getHearingId(), hearingEntity.getId().toString());
        assertEquals(hearingRequestForCsv.getHearingResponseReceivedDateTime(),"2004-01-01T12:00");
        assertEquals(hearingRequestForCsv.getFirstScheduledHearingDate(), "2020-08-10T12:20");
        assertNull(hearingRequestForCsv.getHearingStatus());
        assertNull(hearingRequestForCsv.getCaseReference());
        assertNull(hearingRequestForCsv.getListAssistId());
    }

    private HearingResponseEntity hearingResponse(int requestVersion, int timestampYear) {
        HearingResponseEntity hearingResponse = new HearingResponseEntity();
        hearingResponse.setRequestVersion(requestVersion);
        hearingResponse.setRequestTimeStamp(
            LocalDateTime.of(timestampYear, 1, 1, 12, 0));
        return hearingResponse;
    }

    public static HearingDayDetailsEntity hearingDayDetailsEntities(LocalDateTime startTime, LocalDateTime endTime) {
        HearingDayDetailsEntity entity = new HearingDayDetailsEntity();
        entity.setStartDateTime(startTime);
        entity.setEndDateTime(endTime);
        entity.setVenueId("venue1");
        entity.setRoomId("room1");
        return entity;
    }
}
