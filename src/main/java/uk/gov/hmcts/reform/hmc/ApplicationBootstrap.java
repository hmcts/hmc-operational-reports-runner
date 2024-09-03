package uk.gov.hmcts.reform.hmc;

import com.microsoft.applicationinsights.TelemetryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
@SuppressWarnings("HideUtilityClassConstructor") // Spring needs a constructor, it's not a utility class
public class ApplicationBootstrap implements ApplicationRunner {

    @Value("${telemetry.wait.period:10000}")
    private int waitPeriod;

    private final TelemetryClient client;

    private final ApplicationExecutor applicationExecutor;

    @Autowired
    public ApplicationBootstrap(TelemetryClient client,
                                ApplicationExecutor applicationExecutor) {
        this.client = client;
        this.applicationExecutor = applicationExecutor;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            log.info("Starting the Hmc-Operational-Reports-Runner job.");
            applicationExecutor.execute();
            log.info("Completed the Hmc-Operational-Reports-Runner job successfully.");
        } catch (Exception exception) {
            log.error("Error executing Next-Hearing-Date-Updater job.", exception);
        } finally {
            client.flush();
            waitTelemetryGracefulPeriod();
        }
        System.exit(0);
    }

    public static void main(final String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(ApplicationBootstrap.class);
        context.close();
    }

    private void waitTelemetryGracefulPeriod() throws InterruptedException {
        Thread.sleep(waitPeriod);
    }
}
