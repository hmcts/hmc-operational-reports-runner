package uk.gov.hmcts.reform.hmc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.microsoft.applicationinsights.TelemetryClient;
import org.springframework.boot.ApplicationArguments;

import static org.mockito.Mockito.doNothing;
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
}
