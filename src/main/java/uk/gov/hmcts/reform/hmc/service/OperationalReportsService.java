package uk.gov.hmcts.reform.hmc.service;

import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.model.HearingRequestForCsv;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OperationalReportsService {

    File createCsvDataForExceptions() throws IOException;

    File createCsvDataForAwaitingActuals() throws IOException;

    List<HearingRequestForCsv> createCsvObjectsForGivenStatuses(List<String> statuses);

    List<CaseHearingRequestEntity> getHearingsForStatuses(List<String> statuses);

    List<HearingRequestForCsv> mapToCsvObjects(List<CaseHearingRequestEntity> caseHearings);

    String createCsvData(List<HearingRequestForCsv> hearingRequestForCsvs);

    File generateFileFromString(String file) throws IOException;

    List<CaseHearingRequestEntity> filterCaseHearingRequests(
            List<CaseHearingRequestEntity> caseHearingRequestEntities);

    boolean isToBeIncluded(LocalDateTime endDate);

    boolean isToBeIncluded(LocalDateTime endDate, LocalDate now, Long configuredNumberOfDays);

}
