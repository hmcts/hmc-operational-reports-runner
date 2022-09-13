package uk.gov.hmcts.reform.hmc.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.hmcts.reform.hmc.ApplicationParams;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.data.HearingEntity;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetHearingRequestToCsvMapperTest {

    @Mock
    private ApplicationParams applicationParams;

    @Mock
    private HearingActualsHelper hearingActualsHelper;

    @InjectMocks
    private GetHearingRequestToCsvMapper getHearingRequestToCsvMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getHearingRequestToCsvMapper =
                new GetHearingRequestToCsvMapper();
    }

    @Test
    void toHearingRequestForCsv() {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(2000000001L);
        CaseHearingRequestEntity caseHearingRequestEntity = new CaseHearingRequestEntity();
        caseHearingRequestEntity.setHearing(hearingEntity);
        caseHearingRequestEntity.setHearingWindowEndDateRange(LocalDate.now());
        caseHearingRequestEntity.setHearingRequestReceivedDateTime(LocalDateTime.now());
        HearingRequestForCsv hearingRequestForCsv =
                getHearingRequestToCsvMapper.toHearingRequestForCsv(caseHearingRequestEntity);
        assertEquals(hearingRequestForCsv.getHearingId(), hearingEntity.getId().toString());
    }
}