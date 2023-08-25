package uk.gov.hmcts.reform.hmc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HearingRequestForCsv {
    @JsonProperty("Case Reference")
    private String caseReference;
    @JsonProperty("Case Name")
    private String caseName;
    @JsonProperty("Hearing Status")
    private String hearingStatus;
    @JsonProperty("HMC Id")
    private String hearingId;
    @JsonProperty("List Assist Id")
    private String listAssistId;
    @JsonProperty("Date/time of last request")
    private String hearingRequestReceivedDateTime;
    @JsonProperty("Date/time of last LA response")
    private String hearingResponseReceivedDateTime;
    @JsonProperty("Date/time of first scheduled hearing date")
    private String firstScheduledHearingDate;
    @JsonProperty("Error Description")
    private String errorDescription;
}
