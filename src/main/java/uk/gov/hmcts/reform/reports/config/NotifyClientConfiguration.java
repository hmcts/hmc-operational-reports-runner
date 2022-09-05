package uk.gov.hmcts.reform.reports.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.hmcts.reform.reports.ApplicationParams;
import uk.gov.service.notify.NotificationClient;

@Configuration
public class NotifyClientConfiguration {

    @Autowired
    private ApplicationParams applicationParams;

    @Bean
    public NotificationClient notificationClient() {
        return new NotificationClient(applicationParams.getNotifyApiKey());
    }
}
