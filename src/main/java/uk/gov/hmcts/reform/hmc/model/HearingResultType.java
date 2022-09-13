package uk.gov.hmcts.reform.hmc.model;

import lombok.Getter;

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
