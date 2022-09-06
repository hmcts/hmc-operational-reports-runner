package uk.gov.hmcts.reform.hmc.reports;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.hmcts.reform.hmc.ApplicationBootstrap;

@SpringBootTest(classes = {
    ApplicationBootstrap.class
})
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("itest")
public class BaseTest {

}
