package uk.gov.hmcts.reform.hmc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.inject.Inject;

@Slf4j
@SpringBootApplication
@SuppressWarnings("HideUtilityClassConstructor") // Spring needs a constructor, it is not a utility class
public class ApplicationBootstrap implements ApplicationRunner {

    @Inject
    private ApplicationExecutor applicationExecutor;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Starting the Hmc-Operational-Reports-Runner job.");
        applicationExecutor.execute();
        log.info("Completed the Hmc-Operational-Reports-Runner job successfully.");
    }

    public static void main(final String[] args) {
        final ApplicationContext context = SpringApplication.run(ApplicationBootstrap.class);
        SpringApplication.exit(context);
    }

}
