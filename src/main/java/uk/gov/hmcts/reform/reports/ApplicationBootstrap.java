package uk.gov.hmcts.reform.reports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
@SuppressWarnings("HideUtilityClassConstructor") // Spring needs a constructor, it's not a utility class
public class ApplicationBootstrap implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        executeTask();
        logMessage("Completed the Operational-Reports-Runner job successfully.");
    }

    public static void main(final String[] args) {
        final ApplicationContext context = SpringApplication.run(ApplicationBootstrap.class);
        SpringApplication.exit(context);
    }

    protected boolean executeTask() {
        int x = dummyCodeCoverageAnswer(200, 4);
        logMessage("answer = " + x);
        logMessage("Task run successfully.");
        return true;
    }

    protected boolean logMessage(String msg) {
        log.info(msg);
        return true;
    }

    protected Integer dummyCodeCoverageAnswer(Integer x, Integer y) {
        int z = 0;
        if (y != 0) {
            z = x / y;
        }
        return z;
    }

}