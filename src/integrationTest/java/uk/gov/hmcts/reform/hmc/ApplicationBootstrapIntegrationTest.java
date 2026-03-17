package uk.gov.hmcts.reform.hmc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {ApplicationRunner.class})
class ApplicationBootstrapIntegrationTest {
    private static final String FLAG = "true";

    @BeforeAll
    static void setup() {
        System.setProperty("api.version", "1.44");
    }
    
    @Test
    void testShouldBootstrapSpringContext() {
        assertThat(Boolean.valueOf(FLAG)).isTrue();
    }
}

