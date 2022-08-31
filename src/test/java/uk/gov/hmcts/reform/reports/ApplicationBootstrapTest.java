package uk.gov.hmcts.reform.reports;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ApplicationBootstrapTest {

    @Mock
    private ApplicationArguments applicationArguments;

    @InjectMocks
    private ApplicationBootstrap underTest;

    @Test
    void shouldRunExecutor() {
        underTest.run(applicationArguments);
    }

    @Test
    void shouldExecuteTask() {
        assertTrue(underTest.executeTask());
    }

    @Test
    void shouldLogMessage() {
        assertTrue(underTest.logMessage("Testing 1, 2, 3!"));
    }
}
