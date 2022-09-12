package uk.gov.hmcts.reform.hmc;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationParamsTest {

    private static final String VALUE = "test-value";
    private static final Long LONG_VALUE = 2L;
    private final ApplicationParams applicationParams = new ApplicationParams();


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

    @Test
    void shouldGetNotifyAwaitingActualsReplyToEmailAddress() {
        ReflectionTestUtils.setField(applicationParams, "notifyAwaitingHearingsReplyToEmailAddress", VALUE);
        assertEquals(VALUE, applicationParams.getNotifyAwaitingHearingsReplyToEmailAddress());
    }

    @Test
    void shouldGetNotifyErrorReplyToEmailAddress() {
        ReflectionTestUtils.setField(applicationParams, "notifyErrorReplyToEmailAddress", VALUE);
        assertEquals(VALUE, applicationParams.getNotifyErrorReplyToEmailAddress());
    }

    @Test
    void shouldGetConfiguredNumberOfDays() {
        ReflectionTestUtils.setField(applicationParams, "configuredNumberOfDays", LONG_VALUE);
        assertEquals(LONG_VALUE, applicationParams.getConfiguredNumberOfDays());
    }
}
