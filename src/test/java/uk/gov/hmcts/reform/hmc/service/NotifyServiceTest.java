package uk.gov.hmcts.reform.hmc.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.gov.service.notify.NotificationClient;
import uk.gov.service.notify.NotificationClientException;
import uk.gov.service.notify.SendEmailResponse;

import java.io.File;
import java.io.IOException;
import javax.validation.ValidationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class NotifyServiceTest {

    @Mock
    private NotificationClient notificationClient;

    private NotifyService notifyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.notifyService = new NotifyService(notificationClient);
    }


    @Test
    @DisplayName("should fail when email addresses is null")
    void shouldThrowValidationExceptionWhenEmailAddressesIsNull() throws IOException {
        String templateId = "TestTemplateId";
        String replyToId = "TestReplyToId";

        File temp = File.createTempFile("test.csv", ".csv");

        ValidationException exception = assertThrows(ValidationException.class, () ->
            this.notifyService.sendEmail(templateId, null, temp, replyToId));

        assertThat(exception.getMessage(), is("An email address is required to send notification"));
    }

    @Test
    @DisplayName("should invoke notification client sendEmail")
    void shouldInvokeNotificationClientSendNotification() throws NotificationClientException, IOException {
        String emailTemplateId = "TestEmailTemplateId";
        String templateId = "TestTemplateId";
        String replyToId = "TestReplyToId";

        File temp = File.createTempFile("test.csv", ".csv");


        SendEmailResponse sendEmailResponse = mock(SendEmailResponse.class);
        given(this.notificationClient.sendEmail(
                      anyString(),
                      anyString(),
                      anyMap(),
                      anyString(),
                      anyString()
                  ))
            .willReturn(sendEmailResponse);

        SendEmailResponse response =  this.notifyService.sendEmail(templateId, emailTemplateId, temp, replyToId);

        assertNotNull(response);
        verify(this.notificationClient).sendEmail(
            anyString(),
            anyString(),
            anyMap(),
            anyString(),
            anyString()
        );
    }
}
