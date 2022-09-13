package uk.gov.hmcts.reform.hmc.helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.hmcts.reform.hmc.ApplicationParams;
import uk.gov.hmcts.reform.hmc.data.HearingDayDetailsEntity;
import uk.gov.hmcts.reform.hmc.data.HearingEntity;
import uk.gov.hmcts.reform.hmc.data.HearingResponseEntity;
import uk.gov.hmcts.reform.hmc.domain.model.enums.HearingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HearingActualsHelperTest {

    @Mock
    private ApplicationParams applicationParams;

    @InjectMocks
    private HearingActualsHelper hearingActualsHelper;


    @Test
    void isEarliestPlannedHearingDayNotValidForEmptyHearingDays() {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setStatus(HearingStatus.UPDATE_REQUESTED.name());
        HearingResponseEntity latestHearingResponse = new HearingResponseEntity();
        latestHearingResponse.setHearingDayDetails(List.of());
        assertFalse(hearingActualsHelper.isEarliestPlannedHearingDayValid(latestHearingResponse));
    }

    @Test
    void isEarliestPlannedHearingDayValidForEarlyHearingDays() {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setStatus(HearingStatus.UPDATE_REQUESTED.name());
        HearingResponseEntity latestHearingResponse = new HearingResponseEntity();
        HearingDayDetailsEntity hearingDayDetails1 = new HearingDayDetailsEntity();
        hearingDayDetails1.setStartDateTime(LocalDateTime.now().minusMonths(1));
        HearingDayDetailsEntity hearingDayDetails2 = new HearingDayDetailsEntity();
        hearingDayDetails2.setStartDateTime(LocalDateTime.now().minusMonths(2));
        latestHearingResponse.setHearingDayDetails(List.of(hearingDayDetails1, hearingDayDetails2));
        assertTrue(hearingActualsHelper.isEarliestPlannedHearingDayValid(latestHearingResponse));
    }

    @Test
    void isEarliestPlannedHearingDayNotValidForLaterHearingDays() {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setStatus(HearingStatus.UPDATE_REQUESTED.name());
        HearingResponseEntity latestHearingResponse = new HearingResponseEntity();
        HearingDayDetailsEntity hearingDayDetails1 = new HearingDayDetailsEntity();
        hearingDayDetails1.setStartDateTime(LocalDateTime.now().plusMonths(1));
        HearingDayDetailsEntity hearingDayDetails2 = new HearingDayDetailsEntity();
        hearingDayDetails2.setStartDateTime(LocalDateTime.now().plusMonths(2));
        latestHearingResponse.setHearingDayDetails(List.of(hearingDayDetails1, hearingDayDetails2));
        assertFalse(hearingActualsHelper.isEarliestPlannedHearingDayValid(latestHearingResponse));
    }

    @Test
    void isLastPlannedHearingDayNotValidForEmptyHearingDays() {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setStatus(HearingStatus.UPDATE_REQUESTED.name());
        HearingResponseEntity latestHearingResponse = new HearingResponseEntity();
        latestHearingResponse.setHearingDayDetails(List.of());
        assertFalse(hearingActualsHelper.isLastPlannedHearingDayValid(latestHearingResponse));
    }

    @Test
    void isLastPlannedHearingDayValidForEndedHearingDays() {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setStatus(HearingStatus.UPDATE_REQUESTED.name());
        HearingResponseEntity latestHearingResponse = new HearingResponseEntity();
        HearingDayDetailsEntity hearingDayDetails1 = new HearingDayDetailsEntity();
        hearingDayDetails1.setEndDateTime(LocalDateTime.now().minusMonths(1));
        HearingDayDetailsEntity hearingDayDetails2 = new HearingDayDetailsEntity();
        hearingDayDetails2.setEndDateTime(LocalDateTime.now().minusMonths(2));
        latestHearingResponse.setHearingDayDetails(List.of(hearingDayDetails1, hearingDayDetails2));
        when(applicationParams.getConfiguredNumberOfDays()).thenReturn(0L);
        assertTrue(hearingActualsHelper.isLastPlannedHearingDayValid(latestHearingResponse));
    }

    @Test
    void isLastPlannedHearingDayNotValidForFutureHearingDays() {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setStatus(HearingStatus.UPDATE_REQUESTED.name());
        HearingResponseEntity latestHearingResponse = new HearingResponseEntity();
        HearingDayDetailsEntity hearingDayDetails1 = new HearingDayDetailsEntity();
        hearingDayDetails1.setEndDateTime(LocalDateTime.now().minusMonths(1));
        HearingDayDetailsEntity hearingDayDetails2 = new HearingDayDetailsEntity();
        hearingDayDetails2.setEndDateTime(LocalDateTime.now().plusMonths(1));
        latestHearingResponse.setHearingDayDetails(List.of(hearingDayDetails1, hearingDayDetails2));
        when(applicationParams.getConfiguredNumberOfDays()).thenReturn(0L);
        assertFalse(hearingActualsHelper.isLastPlannedHearingDayValid(latestHearingResponse));
    }

    @Test
    void isAwaitingActuals() {
        // All the cases from HMAN-390
        LocalDateTime endDateTime = LocalDateTime.of(2022,2, 15, 13, 1);
        LocalDate now = LocalDate.of(2022, 2, 16);
        assertTrue(hearingActualsHelper.isLastPlannedHearingDayValid(endDateTime, now, 0L));

        endDateTime = LocalDateTime.of(2022,2, 15, 13, 1);
        now = LocalDate.of(2022, 2, 16);
        assertFalse(hearingActualsHelper.isLastPlannedHearingDayValid(endDateTime, now, 2L));

        endDateTime = LocalDateTime.of(2022,2, 17, 13, 1);
        now = LocalDate.of(2022, 2, 16);
        assertFalse(hearingActualsHelper.isLastPlannedHearingDayValid(endDateTime, now, 2L));

        endDateTime = LocalDateTime.of(2022,2, 14, 13, 1);
        now = LocalDate.of(2022, 2, 16);
        assertTrue(hearingActualsHelper.isLastPlannedHearingDayValid(endDateTime, now, 1L));

        endDateTime = LocalDateTime.of(2022,2, 14, 13, 1);
        now = LocalDate.of(2022, 2, 16);
        assertTrue(hearingActualsHelper.isLastPlannedHearingDayValid(endDateTime, now, 1L));

        endDateTime = LocalDateTime.of(2022,2, 16, 13, 1);
        now = LocalDate.of(2022, 2, 16);
        assertFalse(hearingActualsHelper.isLastPlannedHearingDayValid(endDateTime, now, 1L));

        endDateTime = LocalDateTime.of(2022,2, 17, 13, 1);
        now = LocalDate.of(2022, 2, 16);
        assertFalse(hearingActualsHelper.isLastPlannedHearingDayValid(endDateTime, now, 0L));
    }

}