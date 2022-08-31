package uk.gov.hmcts.reform.reports;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;

@ExtendWith(MockitoExtension.class)
class ApplicationBootstrapTest {

    @Mock
    private ApplicationArguments applicationArguments;

    @InjectMocks
    private ApplicationBootstrap underTest;

    @Test
    void testShouldRunExecutor() {
        underTest.run(applicationArguments);
    }

}
