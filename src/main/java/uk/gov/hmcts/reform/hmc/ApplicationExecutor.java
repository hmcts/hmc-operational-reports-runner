package uk.gov.hmcts.reform.hmc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.hmcts.reform.hmc.domain.model.enums.HearingStatus;
import uk.gov.hmcts.reform.hmc.service.NotifyService;
import uk.gov.hmcts.reform.hmc.service.OperationalReportsService;
import uk.gov.service.notify.NotificationClientException;

import java.io.File;
import java.io.IOException;
import javax.inject.Named;

@Slf4j
@Named
public class ApplicationExecutor {

    private final NotifyService notifyService;
    private final ApplicationParams appParams;

    @Autowired
    OperationalReportsService operationalReportsService;

    @Autowired
    public ApplicationExecutor(ApplicationParams appParams,
                               NotifyService notifyService) {
        this.notifyService = notifyService;
        this.appParams = appParams;
    }

    public void execute() throws IOException, NotificationClientException {
        log.info("Hmc-Operational-Reports-Runner started...");
        generateExceptionReports();
        log.info("Hmc-Operational-Reports-Runner finished.");
    }

    private void generateExceptionReports() throws IOException, NotificationClientException {
        log.info("Creating CSV data for Exceptions...");
        File reportExceptions = operationalReportsService.createCsvDataForExceptions();
        log.info("CSV Data for Exceptions successfully created.");
        log.info("Invoking Notify Service for the Exceptions report...");
        notifyService.sendEmail(appParams.getNotifyErrorTemplateId(), appParams.getNotifyErrorEmailAddress(),
                              reportExceptions, appParams.getNotifyErrorReplyToEmailAddress(),
                                HearingStatus.EXCEPTION.name()
        );
        log.info("Successfully invoked Notify Service for the Exceptions report.");

        log.info("Creating CSV data for Awaiting Actuals...");
        File reportAwaitingActuals = operationalReportsService.createCsvDataForAwaitingActuals();
        log.info("CSV Data for Awaiting Actuals successfully created.");

    }


}
