package uk.gov.hmcts.reform.hmc.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

@Getter
public enum DayOfWeekUnavailable {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    public final String label;

    DayOfWeekUnavailable(String label) {
        this.label = label;
    }
}
