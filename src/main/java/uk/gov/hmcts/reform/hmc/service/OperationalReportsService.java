package uk.gov.hmcts.reform.hmc.service;

import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;

import java.io.File;
import java.util.List;

public interface OperationalReportsService {

    File createCsvFileForExceptions();

    File createCsvFileForAwaitingActuals();

    List<HearingRequestForCsv> createCsvObjectsForGivenStatuses(List<String> statuses);

    List<CaseHearingRequestEntity> getHearingsForStatuses(List<String> statuses);

    List<HearingRequestForCsv> mapToCsvObjects(List<CaseHearingRequestEntity> caseHearings);

    File createCsvFile(List<HearingRequestForCsv> hearingRequestForCsvs);

}
