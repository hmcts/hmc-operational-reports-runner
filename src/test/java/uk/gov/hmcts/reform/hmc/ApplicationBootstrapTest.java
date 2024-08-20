package uk.gov.hmcts.reform.hmc;

import com.microsoft.applicationinsights.TelemetryClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ApplicationBootstrapTest {

    @Mock
    private ApplicationArguments applicationArguments;

    @Mock
    private ApplicationExecutor applicationExecutor;

    @Mock
    private TelemetryClient client;

    @InjectMocks
    private ApplicationBootstrap underTest;

    @Test
    void testShouldRunExecutor() throws Exception {
        doNothing().when(client).flush();
        underTest.run(applicationArguments);
        verify(applicationExecutor).execute();
        verify(client).flush();
    }

    @Test
    void shouldRunExecutorTenTimes() throws Exception {
        doNothing().when(client).flush();
        for (int i = 0; i < 10; i++) {
            underTest.run(applicationArguments);
        }
        verify(applicationExecutor, times(10)).execute();
        verify(client, times(10)).flush();
    }
}
