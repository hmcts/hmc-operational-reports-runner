package uk.gov.hmcts.reform.hmc.data;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HearingEntityTest {


    @Nested
    class GetLatestHearingResponse {

        @Test
        void shouldGetLatestHearingResponse() {
            HearingEntity hearing = new HearingEntity();
            HearingResponseEntity hearingResponse1 = hearingResponse(1, 2000);
            HearingResponseEntity hearingResponse2 = hearingResponse(2, 2002);
            HearingResponseEntity hearingResponse3 = hearingResponse(2, 2004);
            hearing.setHearingResponses(List.of(hearingResponse1, hearingResponse2, hearingResponse3));

            Optional<HearingResponseEntity> latestResponse = hearing.getLatestHearingResponse();

            assertTrue(latestResponse.isPresent());
            assertEquals(hearingResponse3, latestResponse.get());
        }

        @Test
        void shouldReturnEmptyOptionalWhenNoHearingResponsesExist() {
            HearingEntity hearing = new HearingEntity();

            Optional<HearingResponseEntity> latestResponse = hearing.getLatestHearingResponse();

            assertFalse(latestResponse.isPresent());
        }
    }

    private HearingResponseEntity hearingResponse(int requestVersion, int timestampYear) {
        HearingResponseEntity hearingResponse = new HearingResponseEntity();
        hearingResponse.setRequestVersion(requestVersion);
        hearingResponse.setRequestTimeStamp(LocalDateTime.of(timestampYear, 1, 1, 12, 0));
        return hearingResponse;
    }
}
