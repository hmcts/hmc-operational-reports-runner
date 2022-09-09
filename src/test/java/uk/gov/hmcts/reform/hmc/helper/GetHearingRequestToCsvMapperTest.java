package uk.gov.hmcts.reform.hmc.helper;

import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.data.HearingEntity;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GetHearingRequestToCsvMapperTest {

    @Test
    void toHearingRequestForCsv() {
        GetHearingRequestToCsvMapper mapper = new GetHearingRequestToCsvMapper();
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(2000000001L);
        CaseHearingRequestEntity caseHearingRequestEntity = new CaseHearingRequestEntity();
        caseHearingRequestEntity.setHearing(hearingEntity);
        caseHearingRequestEntity.setHearingWindowEndDateRange(LocalDate.now());
        caseHearingRequestEntity.setHearingRequestReceivedDateTime(LocalDateTime.now());
        HearingRequestForCsv hearingRequestForCsv = mapper.toHearingRequestForCsv(caseHearingRequestEntity);
        assertEquals(hearingRequestForCsv.getHearingId(), hearingEntity.getId().toString());
    }
}