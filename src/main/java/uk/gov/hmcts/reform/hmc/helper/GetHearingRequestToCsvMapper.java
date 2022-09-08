package uk.gov.hmcts.reform.hmc.helper;

import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.data.HearingDayDetailsEntity;
import uk.gov.hmcts.reform.hmc.data.HearingResponseEntity;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;

@Component
public class GetHearingRequestToCsvMapper {

    public HearingRequestForCsv toHearingRequestForCsv(CaseHearingRequestEntity requestEntity) {
        HearingRequestForCsv hearingRequestForCsv = new HearingRequestForCsv();
        hearingRequestForCsv.setCaseReference(requestEntity.getCaseReference());
        hearingRequestForCsv.setCaseName(requestEntity.getPublicCaseName());
        hearingRequestForCsv.setHearingStatus(requestEntity.getHearing().getStatus());
        hearingRequestForCsv.setHearingId(requestEntity.getHearing().getId().toString());
        hearingRequestForCsv.setHearingRequestReceivedDateTime(
                requestEntity.getHearingRequestReceivedDateTime().toString());
        loadHearingResponseValues(requestEntity, hearingRequestForCsv);
        return hearingRequestForCsv;
    }

    private void loadHearingResponseValues(CaseHearingRequestEntity requestEntity,
                                           HearingRequestForCsv hearingRequestForCsv) {
        if (requestEntity.getHearing().getLatestHearingResponse().isPresent()) {
            HearingResponseEntity latestHearingResponse =
                    requestEntity.getHearing().getLatestHearingResponse().get();
            if (null != latestHearingResponse.getHearingResponseId()) {
                hearingRequestForCsv.setListAssistId(latestHearingResponse.getHearingResponseId().toString());
            }
            if (null != latestHearingResponse.getRequestTimeStamp()) {
                hearingRequestForCsv.setHearingResponseReceivedDateTime(
                        latestHearingResponse.getRequestTimeStamp().toString());
            }
            if (latestHearingResponse.getEarliestHearingDayDetails().isPresent()) {
                HearingDayDetailsEntity hearingDayDetails = latestHearingResponse.getEarliestHearingDayDetails().get();
                hearingRequestForCsv.setFirstScheduledHearingDate(hearingDayDetails.getStartDateTime().toString());
            }
        }
    }

}
