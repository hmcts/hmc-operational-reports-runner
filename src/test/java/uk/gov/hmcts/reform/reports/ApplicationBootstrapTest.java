package uk.gov.hmcts.reform.reports;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;


import uk.gov.hmcts.reform.hmc.ApplicationBootstrap;
import uk.gov.hmcts.reform.hmc.ApplicationExecutor;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ApplicationBootstrapTest {

    @Mock
    private ApplicationArguments applicationArguments;

    @Mock
    private ApplicationExecutor applicationExecutor;

    @InjectMocks
    private ApplicationBootstrap underTest;

    @Test
    void testShouldRunExecutor() throws Exception {
        underTest.run(applicationArguments);

        verify(applicationExecutor).execute();
    }
}
