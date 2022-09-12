package uk.gov.hmcts.reform.hmc.data;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HearingResponseEntityTest {

    @Test
    void shouldReturnTrueHasHearingDayDetails() {
        HearingResponseEntity hearing = new HearingResponseEntity();
        hearing.setHearingDayDetails(List.of(hearingDayDetailsEntities(
            LocalDateTime.parse("2020-08-10T12:20:00"),
            LocalDateTime.parse("2021-08-10T12:20:00")
        )));
        assertTrue(hearing.hasHearingDayDetails());
    }

    @Test
    void shouldReturnFalseHasHearingDayDetails() {
        HearingResponseEntity hearing = new HearingResponseEntity();
        hearing.setHearingDayDetails(null);
        assertFalse(hearing.hasHearingDayDetails());
    }

    @Test
    void shouldReturnEarliestHearingDayDetails() {
        HearingResponseEntity hearing = new HearingResponseEntity();
        HearingDayDetailsEntity hearingDayDetails =
            hearingDayDetailsEntities(LocalDateTime.parse("2020-08-10T12:20:00"),
                                      LocalDateTime.parse("2021-08-10T12:20:00"));
        HearingDayDetailsEntity hearingDayDetails1 =
            hearingDayDetailsEntities(LocalDateTime.parse("2020-08-11T12:20:00"),
                                      LocalDateTime.parse("2021-08-11T12:20:00"));
        hearing.setHearingDayDetails(List.of(hearingDayDetails, hearingDayDetails1));
        assertEquals(hearingDayDetails.getStartDateTime(),
                     hearing.getEarliestHearingDayDetails().get().getStartDateTime());
        assertEquals(hearingDayDetails.getEndDateTime(),
                     hearing.getEarliestHearingDayDetails().get().getEndDateTime());
    }

    @Test
    void shouldReturnLatestHearingDayDetails() {
        HearingResponseEntity hearing = new HearingResponseEntity();
        HearingDayDetailsEntity hearingDayDetails =
            hearingDayDetailsEntities(LocalDateTime.parse("2020-08-10T12:20:00"),
                                      LocalDateTime.parse("2021-08-10T12:20:00"));
        HearingDayDetailsEntity hearingDayDetails1 =
            hearingDayDetailsEntities(LocalDateTime.parse("2020-08-11T12:20:00"),
                                      LocalDateTime.parse("2021-08-11T12:20:00"));
        hearing.setHearingDayDetails(List.of(hearingDayDetails, hearingDayDetails1));
        assertEquals(hearingDayDetails1.getStartDateTime(),
                     hearing.getLatestHearingDayDetails().get().getStartDateTime());
        assertEquals(hearingDayDetails1.getEndDateTime(),
                     hearing.getLatestHearingDayDetails().get().getEndDateTime());
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
