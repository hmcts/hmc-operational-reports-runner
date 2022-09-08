package uk.gov.hmcts.reform.hmc.service;

import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;

import java.util.List;

public interface OperationalReportsService {

    String createCsvDataForExceptions();

    List<HearingRequestForCsv> createCsvObjectsForGivenStatuses(List<String> statuses);

    List<CaseHearingRequestEntity> getHearingsForStatuses(List<String> statuses);

    List<HearingRequestForCsv> mapToCsvObjects(List<CaseHearingRequestEntity> caseHearings);

    String createCsvData(List<HearingRequestForCsv> hearingRequestForCsvs);

}
