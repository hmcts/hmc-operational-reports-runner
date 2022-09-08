package uk.gov.hmcts.reform.hmc.model;

import lombok.Getter;
import uk.gov.hmcts.reform.hmc.exceptions.BadRequestException;

import java.util.Arrays;
import java.util.Locale;

@Getter
public enum DayOfWeekUnAvailableType {
    AM("AM"),
    PM("PM"),
    ALL("All Day");

    public final String label;

    DayOfWeekUnAvailableType(String label) {
        this.label = label;
    }

}
