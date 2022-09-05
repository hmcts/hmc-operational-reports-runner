package uk.gov.hmcts.reform.hmc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HearingRequestForCsv {
    private String caseReference;
    private String caseName;
    private String hearingStatus;
    private String hearingId;
    private String listAssistId;
    private String hearingRequestReceivedDateTime;
    private String hearingResponseReceivedDateTime;
    private String firstScheduledHearingDate;
}
