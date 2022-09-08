package uk.gov.hmcts.reform.hmc.helper;

import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.data.HearingDayDetailsEntity;
import uk.gov.hmcts.reform.hmc.data.HearingResponseEntity;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;

import java.util.Optional;

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
        Optional<HearingResponseEntity> hearingResponseEntity = requestEntity.getHearing().getLatestHearingResponse();
        if (hearingResponseEntity.isPresent()) {
            HearingResponseEntity latestHearingResponse = hearingResponseEntity.get();
            if (null != latestHearingResponse.getHearingResponseId()) {
                hearingRequestForCsv.setListAssistId(latestHearingResponse.getListingTransactionId());
            }
            if (null != latestHearingResponse.getRequestTimeStamp()) {
                hearingRequestForCsv.setHearingResponseReceivedDateTime(
                        latestHearingResponse.getRequestTimeStamp().toString());
            }
            Optional<HearingDayDetailsEntity> hearingDayDetails = latestHearingResponse.getEarliestHearingDayDetails();
            if (hearingDayDetails.isPresent()) {
                HearingDayDetailsEntity earliestHearingDayDetails = hearingDayDetails.get();
                hearingRequestForCsv.setFirstScheduledHearingDate(earliestHearingDayDetails
                                                                      .getStartDateTime().toString());
            }
        }
    }

}
