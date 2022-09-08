package uk.gov.hmcts.reform.hmc.domain.model.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

@Getter
public enum LinkType {
    ORDERED("Ordered"),
    SAME_SLOT("Same Slot");

    public final String label;

    LinkType(String label) {
        this.label = label;
    }
}
