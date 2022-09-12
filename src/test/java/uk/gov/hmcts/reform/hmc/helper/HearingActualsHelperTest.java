package uk.gov.hmcts.reform.hmc.helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.gov.hmcts.reform.hmc.ApplicationParams;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class HearingActualsHelperTest {

    @MockBean
    private ApplicationParams applicationParams;

    @InjectMocks
    private HearingActualsHelper hearingActualsHelper;


    @Test
    void isAwaitingActuals() {
        // All the cases from HMAN-390
        LocalDateTime endDateTime = LocalDateTime.of(2022,2, 15, 13, 1);
        LocalDate now = LocalDate.of(2022, 2, 16);
        assertTrue(hearingActualsHelper.isLastPlannedHearingDaysValid(endDateTime, now, 0L));

        endDateTime = LocalDateTime.of(2022,2, 15, 13, 1);
        now = LocalDate.of(2022, 2, 16);
        assertFalse(hearingActualsHelper.isLastPlannedHearingDaysValid(endDateTime, now, 2L));

        endDateTime = LocalDateTime.of(2022,2, 17, 13, 1);
        now = LocalDate.of(2022, 2, 16);
        assertFalse(hearingActualsHelper.isLastPlannedHearingDaysValid(endDateTime, now, 2L));

        endDateTime = LocalDateTime.of(2022,2, 14, 13, 1);
        now = LocalDate.of(2022, 2, 16);
        assertTrue(hearingActualsHelper.isLastPlannedHearingDaysValid(endDateTime, now, 1L));

        endDateTime = LocalDateTime.of(2022,2, 14, 13, 1);
        now = LocalDate.of(2022, 2, 16);
        assertTrue(hearingActualsHelper.isLastPlannedHearingDaysValid(endDateTime, now, 1L));

        endDateTime = LocalDateTime.of(2022,2, 16, 13, 1);
        now = LocalDate.of(2022, 2, 16);
        assertFalse(hearingActualsHelper.isLastPlannedHearingDaysValid(endDateTime, now, 1L));

        endDateTime = LocalDateTime.of(2022,2, 17, 13, 1);
        now = LocalDate.of(2022, 2, 16);
        assertFalse(hearingActualsHelper.isLastPlannedHearingDaysValid(endDateTime, now, 0L));
    }

}