package uk.gov.hmcts.reform.hmc.reports;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {
    ApplicationBootstrap.class
})
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("itest")
public class BaseTest {

}
