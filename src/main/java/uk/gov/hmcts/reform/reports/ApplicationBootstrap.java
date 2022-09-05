package uk.gov.hmcts.reform.reports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import uk.gov.hmcts.reform.hmc.repository.CaseHearingRequestRepository;

@Slf4j
@SpringBootApplication
@SuppressWarnings("HideUtilityClassConstructor") // Spring needs a constructor, it's not a utility class
public class ApplicationBootstrap implements ApplicationRunner {

    @Autowired
    public CaseHearingRequestRepository caseHearingRequestRepository;

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
        int answer = dummyCodeCoverageAnswer(250, 5);
        logMessage("answer = " + answer);
        logMessage("Task run successfully.");
        return true;
    }

    protected boolean logMessage(String msg) {
        log.info(msg);
        return true;
    }

    protected Integer dummyCodeCoverageAnswer(Integer numerator, Integer divider) {
        int answer = 0;
        if (divider != 0) {
            answer = numerator / divider;
        }
        return answer;
    }

}