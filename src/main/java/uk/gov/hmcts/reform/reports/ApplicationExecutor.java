package uk.gov.hmcts.reform.reports;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Slf4j
@Named
public class ApplicationExecutor {
    public void execute() {
        log.info("Hmc-Operational-Reports-Runner started...");
        log.info("Hmc-Operational-Reports-Runner finished.");
    }

}
