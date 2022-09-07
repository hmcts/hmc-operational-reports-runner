package uk.gov.hmcts.reform.hmc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.hmcts.reform.hmc.service.OperationalReportsService;

import javax.inject.Named;

@Slf4j
@Named
public class ApplicationExecutor {

    @Autowired
    OperationalReportsService operationalReportsService;

    public void execute() {
        log.info("Hmc-Operational-Reports-Runner started...");

        log.info("Creating CSV data for Exceptions...");
        String csvExceptions = operationalReportsService.createCsvDataForExceptions();
        log.info("CSV Data for Exceptions successfully created.");

        log.info("Hmc-Operational-Reports-Runner finished.");
    }

}
