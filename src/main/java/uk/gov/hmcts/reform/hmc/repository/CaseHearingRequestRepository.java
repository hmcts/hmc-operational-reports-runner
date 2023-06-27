package uk.gov.hmcts.reform.hmc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@Repository
public interface CaseHearingRequestRepository extends CrudRepository<CaseHearingRequestEntity, Long> {

    @Query("from CaseHearingRequestEntity chr WHERE chr.hearing.status in :statuses "
            + "and chr.versionNumber = (select max(chr2.versionNumber) from CaseHearingRequestEntity chr2 "
            + "and chr.hmctsServiceCode = \"BBA3\" "
            + "where chr2.hearing.id = chr.hearing.id) "
            + "order by chr.hearing.id desc")
    List<CaseHearingRequestEntity> getCaseHearingDetailsWithStatuses(List<String> statuses);

}
