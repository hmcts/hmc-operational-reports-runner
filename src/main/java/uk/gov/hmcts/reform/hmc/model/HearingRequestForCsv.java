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
    @JsonProperty("Hearing Id")
    private String hearingId;
    @JsonProperty("List Assist Id")
    private String listAssistId;
    @JsonProperty("Hearing Request Received Date Time")
    private String hearingRequestReceivedDateTime;
    @JsonProperty("Hearing Response Received Date Time")
    private String hearingResponseReceivedDateTime;
    @JsonProperty("First Scheduled Hearing Date Time")
    private String firstScheduledHearingDate;
}
