package uk.gov.hmcts.reform.hmc.helper;

import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
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
        loadFirstScheduledHearingValues(requestEntity, hearingRequestForCsv);
        return hearingRequestForCsv;
    }

    private void loadHearingResponseValues(CaseHearingRequestEntity requestEntity,
                                           HearingRequestForCsv hearingRequestForCsv) {
        // TODO:
        //hearingRequestForCsv.setListAssistId(null);
        //hearingRequestForCsv.setHearingResponseReceivedDateTime(null);
    }

    private void loadFirstScheduledHearingValues(CaseHearingRequestEntity requestEntity,
                                                 HearingRequestForCsv hearingRequestForCsv) {
        // TODO:
        //hearingRequestForCsv.setFirstScheduledHearingDate(null);
    }

}
