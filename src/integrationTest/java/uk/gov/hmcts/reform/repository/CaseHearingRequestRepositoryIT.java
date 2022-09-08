package uk.gov.hmcts.reform.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import uk.gov.hmcts.reform.hmc.ApplicationParams;
import uk.gov.hmcts.reform.hmc.BaseTest;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.repository.CaseHearingRequestRepository;
import uk.gov.hmcts.reform.hmc.service.NotifyService;
import uk.gov.service.notify.NotificationClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaseHearingRequestRepositoryIT extends BaseTest {

    @Autowired
    CaseHearingRequestRepository caseHearingRequestRepository;

    @MockBean
    ApplicationParams applicationParams;

    @MockBean
    NotificationClient notificationClient;

    @MockBean
    NotifyService notifyService;

    private static final String DELETE_HEARING_DATA_SCRIPT = "classpath:sql/delete-hearing-tables.sql";

    private static final String GET_HEARINGS_DATA_SCRIPT = "classpath:sql/get-caseHearings_request.sql";

    @Test
    @Sql(scripts = {DELETE_HEARING_DATA_SCRIPT, GET_HEARINGS_DATA_SCRIPT})
    void getCaseHearingDetailsForStatusesListed() {
        List<String> statuses = List.of("LISTED");
        List<CaseHearingRequestEntity> entities = caseHearingRequestRepository
                .getCaseHearingDetailsWithStatuses(statuses);
        assertEquals(1, entities.size());
    }

    @Test
    @Sql(scripts = {DELETE_HEARING_DATA_SCRIPT, GET_HEARINGS_DATA_SCRIPT})
    void getCaseHearingDetailsForStatusesHearingRequested() {
        List<String> statuses = List.of("HEARING_REQUESTED");
        List<CaseHearingRequestEntity> entities = caseHearingRequestRepository
                .getCaseHearingDetailsWithStatuses(statuses);
        assertEquals(3, entities.size());
    }

    @Test
    @Sql(scripts = {DELETE_HEARING_DATA_SCRIPT, GET_HEARINGS_DATA_SCRIPT})
    void testGetCaseHearingDetailsStatusesListedAndHearingRequested() {
        List<String> statuses = List.of("LISTED", "HEARING_REQUESTED");
        List<CaseHearingRequestEntity> entities = caseHearingRequestRepository
                .getCaseHearingDetailsWithStatuses(statuses);
        assertEquals(4, entities.size());
    }

}
