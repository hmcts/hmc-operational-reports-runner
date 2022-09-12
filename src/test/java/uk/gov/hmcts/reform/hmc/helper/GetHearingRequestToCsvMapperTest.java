package uk.gov.hmcts.reform.hmc.helper;

import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.data.HearingDayDetailsEntity;
import uk.gov.hmcts.reform.hmc.data.HearingEntity;
import uk.gov.hmcts.reform.hmc.data.HearingResponseEntity;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetHearingRequestToCsvMapperTest {

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
        GetHearingRequestToCsvMapper mapper = new GetHearingRequestToCsvMapper();
        HearingRequestForCsv hearingRequestForCsv = mapper.toHearingRequestForCsv(caseHearingRequestEntity);
        assertEquals(hearingRequestForCsv.getHearingId(), hearingEntity.getId().toString());
    }

    private HearingResponseEntity hearingResponse(int requestVersion, int timestampYear) {
        HearingResponseEntity hearingResponse = new HearingResponseEntity();
        hearingResponse.setRequestVersion(requestVersion);
        hearingResponse.setRequestTimeStamp(
            LocalDateTime.of(timestampYear, 1, 1, 12, 0));
        hearingResponse.setListingTransactionId("transactionId");
        return hearingResponse;
    }

    public static HearingDayDetailsEntity hearingDayDetailsEntities(LocalDateTime startTime, LocalDateTime endTime) {
        HearingDayDetailsEntity entity = new HearingDayDetailsEntity();
        entity.setStartDateTime(LocalDateTime.parse("2020-08-10T12:20:00"));
        entity.setEndDateTime(LocalDateTime.parse("2021-08-10T12:20:00"));
        entity.setVenueId("venue1");
        entity.setRoomId("room1");
        return entity;
    }
}
