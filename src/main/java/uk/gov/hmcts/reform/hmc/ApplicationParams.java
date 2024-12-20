package uk.gov.hmcts.reform.hmc;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Named
@Singleton
public class ApplicationParams {

    @Value("${awaiting-actuals.configured-number-of-days}")
    private Long configuredNumberOfDays;
    @Value("${notify.api-key}")
    private String notifyApiKey;
    @Value("${notify.email-templates.error.template-id}")
    private String notifyErrorTemplateId;
    @Value("#{'${notify.email-templates.error.email-address}'.split(',')}")
    private List<String> notifyErrorEmailAddress;
    @Value("${notify.email-templates.error.reply-to-email-address}")
    private String notifyErrorReplyToEmailAddress;
    @Value("${notify.email-templates.awaiting-actuals.template-id}")
    private String notifyAwaitingHearingsTemplateId;
    @Value("#{'${notify.email-templates.awaiting-actuals.email-address}'.split(',')}")
    private List<String> notifyAwaitingHearingsEmailAddress;
    @Value("${notify.email-templates.awaiting-actuals.reply-to-email-address}")
    private String notifyAwaitingHearingsReplyToEmailAddress;

    public String getNotifyApiKey() {
        return notifyApiKey;
    }

    public String getNotifyErrorTemplateId() {
        return notifyErrorTemplateId;
    }

    public String getNotifyAwaitingHearingsTemplateId() {
        return notifyAwaitingHearingsTemplateId;
    }

    public List<String> getNotifyErrorEmailAddress() {
        return notifyErrorEmailAddress;
    }

    public List<String> getNotifyAwaitingHearingsEmailAddress() {
        return notifyAwaitingHearingsEmailAddress;
    }

    public String getNotifyErrorReplyToEmailAddress() {
        return notifyErrorReplyToEmailAddress;
    }

    public String getNotifyAwaitingHearingsReplyToEmailAddress() {
        return notifyAwaitingHearingsReplyToEmailAddress;
    }

    public Long getConfiguredNumberOfDays() {
        return configuredNumberOfDays;
    }

}
