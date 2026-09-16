package uk.gov.hmcts.reform.hmc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ApplicationBootstrapTest {

    @Mock
    private ApplicationArguments applicationArguments;

    @Mock
    private ApplicationExecutor applicationExecutor;

    private ApplicationBootstrap underTest;

    @BeforeEach
    void setUp() {
        underTest = new ApplicationBootstrap(applicationExecutor);
    }

    @Test
    void testShouldRunExecutor() throws Exception {
        underTest.run(applicationArguments);
        verify(applicationExecutor).execute();
    }

    @Test
    void shouldRunExecutorTenTimes() throws Exception {
        for (int i = 0; i < 10; i++) {
            underTest.run(applicationArguments);
        }
        verify(applicationExecutor, times(10)).execute();
    }
}
