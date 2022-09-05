package uk.gov.hmcts.reform.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.repository.CaseHearingRequestRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaseHearingRequestRepositoryIT {

    @Autowired
    CaseHearingRequestRepository caseHearingRequestRepository;

    private static final String DELETE_HEARING_DATA_SCRIPT = "classpath:sql/delete-hearing-tables.sql";

    private static final String GET_HEARINGS_DATA_SCRIPT = "classpath:sql/get-caseHearings_request.sql";

    @Test
    @Sql(scripts = {DELETE_HEARING_DATA_SCRIPT, GET_HEARINGS_DATA_SCRIPT})
    void testGetCaseHearingDetails_StatusesListed() {
        List<String> statuses = List.of("LISTED");
        List<CaseHearingRequestEntity> entities = caseHearingRequestRepository
                .getCaseHearingDetailsWithStatuses(statuses);
        assertEquals(1, entities.size());
    }

    @Test
    @Sql(scripts = {DELETE_HEARING_DATA_SCRIPT, GET_HEARINGS_DATA_SCRIPT})
    void testGetCaseHearingDetails_StatusesHearingRequested() {
        List<String> statuses = List.of("HEARING_REQUESTED");
        List<CaseHearingRequestEntity> entities = caseHearingRequestRepository
                .getCaseHearingDetailsWithStatuses(statuses);
        assertEquals(3, entities.size());
    }

    @Test
    @Sql(scripts = {DELETE_HEARING_DATA_SCRIPT, GET_HEARINGS_DATA_SCRIPT})
    void testGetCaseHearingDetails_StatusesListedAndHearingRequested() {
        List<String> statuses = List.of("LISTED", "HEARING_REQUESTED");
        List<CaseHearingRequestEntity> entities = caseHearingRequestRepository
                .getCaseHearingDetailsWithStatuses(statuses);
        assertEquals(4, entities.size());
    }

}
