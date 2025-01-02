package uk.gov.hmcts.reform.hmc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import uk.gov.hmcts.reform.hmc.domain.model.enums.HearingStatus;
import uk.gov.hmcts.reform.hmc.service.NotifyService;
import uk.gov.hmcts.reform.hmc.service.OperationalReportsService;
import uk.gov.service.notify.NotificationClientException;

import java.io.File;
import java.io.IOException;

@Slf4j
@Named
@SpringBootApplication
public class ApplicationExecutor {

    public static final String AWAITING_ACTUALS =  "awaiting actuals";

    private final NotifyService notifyService;
    private final ApplicationParams appParams;
    private final OperationalReportsService operationalReportsService;

    @Inject
    public ApplicationExecutor(ApplicationParams appParams,
                               NotifyService notifyService,
                               OperationalReportsService operationalReportsService) {
        this.operationalReportsService = operationalReportsService;
        this.notifyService = notifyService;
        this.appParams = appParams;
    }

    public void execute() throws IOException, NotificationClientException {
        log.info("Hmc-Operational-Reports-Runner started...");
        generateExceptionReport();
        generateAwaitingActualsReport();
        log.info("Hmc-Operational-Reports-Runner finished.");
    }

    private void generateExceptionReport() throws IOException, NotificationClientException {
        log.debug("Creating CSV data for Exceptions...");
        File reportExceptions = operationalReportsService.createCsvDataForExceptions();
        log.info("CSV Data for Exceptions successfully created.");
        log.debug("Invoking Notify Service for the Exceptions report...");
        notifyService.sendEmail(appParams.getNotifyErrorTemplateId(),
                                appParams.getNotifyErrorEmailAddress(),
                                reportExceptions,
                                appParams.getNotifyErrorReplyToEmailAddress(),
                                HearingStatus.EXCEPTION.name()
        );
        log.info("Successfully invoked Notify Service for the Exceptions report.");
    }

    private void generateAwaitingActualsReport() throws IOException, NotificationClientException {
        log.debug("Creating CSV data for Awaiting Actuals...");
        File reportAwaitingActuals = operationalReportsService.createCsvDataForAwaitingActuals();
        log.info("CSV Data for Awaiting Actuals successfully created.");
        log.debug("Invoking Notify Service for the Awaiting Actuals report...");
        notifyService.sendEmail(appParams.getNotifyAwaitingHearingsTemplateId(),
                                appParams.getNotifyAwaitingHearingsEmailAddress(),
                                reportAwaitingActuals,
                                appParams.getNotifyAwaitingHearingsReplyToEmailAddress(),
                                AWAITING_ACTUALS
        );
        log.info("Successfully invoked Notify Service for the Awaiting Actuals report.");
    }
}
