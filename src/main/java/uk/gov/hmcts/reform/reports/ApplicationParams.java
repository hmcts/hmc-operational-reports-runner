package uk.gov.hmcts.reform.reports;

import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class ApplicationParams {

    @Value("${notify.api-key}")
    private String notifyApiKey;
    @Value("${notify.email-templates.error-template-id}")
    private String notifyErrorTemplateId;
    @Value("${notify.email-templates.awaiting-hearings-template-id}")
    private String notifyAwaitingHearingsTemplateId;
    @Value("${notify.email-addresses.error-email-address}")
    private String notifyErrorEmailAddress;
    @Value("${notify.email-addresses.awaiting-hearings-email-address}")
    private String notifyAwaitingHearingsEmailAddress;

    public String getNotifyApiKey() {
        return notifyApiKey;
    }

    public String getNotifyErrorTemplateId() {
        return notifyErrorTemplateId;
    }

    public String getNotifyAwaitingHearingsTemplateId() {
        return notifyAwaitingHearingsTemplateId;
    }

    public String getNotifyErrorEmailAddress() {
        return notifyErrorEmailAddress;
    }

    public String getNotifyAwaitingHearingsEmailAddress() {
        return notifyAwaitingHearingsEmailAddress;
    }
}
