package uk.gov.hmcts.reform.reports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.hmcts.reform.hmc.service.OperationalReportsService;

import java.io.File;
import javax.inject.Named;

@Slf4j
@Named
public class ApplicationExecutor {

    @Autowired
    OperationalReportsService operationalReportsService;

    public void execute() {
        log.info("Hmc-Operational-Reports-Runner started...");

        log.info("Creating CSV File for Exceptions...");
        File csvExceptions = operationalReportsService.createCsvFileForExceptions();
        log.info("CSV File for Exceptions successfully created.");

        log.info("Hmc-Operational-Reports-Runner finished.");
    }

}
