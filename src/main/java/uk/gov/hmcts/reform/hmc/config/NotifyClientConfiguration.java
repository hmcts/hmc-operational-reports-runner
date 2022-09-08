package uk.gov.hmcts.reform.reports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.hmcts.reform.reports.ApplicationParams;
import uk.gov.service.notify.NotificationClient;

@Configuration
public class NotifyClientConfiguration {

    @Bean
    public NotificationClient notificationClient(ApplicationParams applicationParams) {
        return new NotificationClient(applicationParams.getNotifyApiKey());
    }
}
