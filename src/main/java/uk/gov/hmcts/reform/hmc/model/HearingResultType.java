package uk.gov.hmcts.reform.hmc.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

@Getter
public enum HearingResultType {
    COMPLETED("COMPLETED"),
    ADJOURNED("ADJOURNED"),
    CANCELLED("CANCELLED");

    public final String label;

    HearingResultType(String label) {
        this.label = label;
    }
}
