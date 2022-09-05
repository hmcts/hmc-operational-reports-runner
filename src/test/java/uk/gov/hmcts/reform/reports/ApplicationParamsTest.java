package uk.gov.hmcts.reform.reports;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationParamsTest {

    private static final String VALUE = "test-value";
    private ApplicationParams applicationParams = new ApplicationParams();


    @Test
    void shouldGetNotifyApiKey() {
        ReflectionTestUtils.setField(applicationParams, "notifyApiKey", VALUE);
        assertEquals(VALUE, applicationParams.getNotifyApiKey());
    }

    @Test
    void shouldGetNotifyErrorTemplateId() {
        ReflectionTestUtils.setField(applicationParams, "notifyErrorTemplateId", VALUE);
        assertEquals(VALUE, applicationParams.getNotifyErrorTemplateId());
    }


    @Test
    void shouldGetNotifyAwaitingActualsTemplateId() {
        ReflectionTestUtils.setField(applicationParams, "notifyAwaitingHearingsTemplateId", VALUE);
        assertEquals(VALUE, applicationParams.getNotifyAwaitingHearingsTemplateId());
    }

    @Test
    void shouldGetNotifyErrorEmailAddress() {
        ReflectionTestUtils.setField(applicationParams, "notifyErrorEmailAddress", VALUE);
        assertEquals(VALUE, applicationParams.getNotifyErrorEmailAddress());
    }

    @Test
    void shouldGetNotifyAwaitingActualsEmailAddress() {
        ReflectionTestUtils.setField(applicationParams, "notifyAwaitingHearingsEmailAddress", VALUE);
        assertEquals(VALUE, applicationParams.getNotifyAwaitingHearingsEmailAddress());
    }
}
