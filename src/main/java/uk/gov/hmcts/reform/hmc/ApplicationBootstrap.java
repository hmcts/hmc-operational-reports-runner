package uk.gov.hmcts.reform.hmc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
@SuppressWarnings("HideUtilityClassConstructor") // Spring needs a constructor, it's not a utility class
public class ApplicationBootstrap implements ApplicationRunner {

    private final ApplicationExecutor applicationExecutor;

    @Autowired
    public ApplicationBootstrap(ApplicationExecutor applicationExecutor) {
        this.applicationExecutor = applicationExecutor;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            log.info("Starting the Hmc-Operational-Reports-Runner job.");
            applicationExecutor.execute();
            log.info("Completed the Hmc-Operational-Reports-Runner job successfully.");
        } catch (Exception exception) {
            log.error("Error executing Hmc-Operational-Reports-Runner job.", exception);
        }
        System.exit(0);
    }

    public static void main(final String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(ApplicationBootstrap.class);
        context.close();
    }
}
