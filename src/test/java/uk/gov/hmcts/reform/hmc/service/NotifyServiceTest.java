package uk.gov.hmcts.reform.hmc.service;

import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.gov.hmcts.reform.hmc.domain.model.enums.HearingStatus;
import uk.gov.service.notify.NotificationClient;
import uk.gov.service.notify.NotificationClientException;
import uk.gov.service.notify.SendEmailResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
        MockitoAnnotations.openMocks(this);
        this.notifyService = new NotifyService(notificationClient);
    }


    @Test
    @DisplayName("should fail when email addresses is null")
    void shouldThrowValidationExceptionWhenEmailAddressesIsNull() throws IOException {
        String templateId = "TestTemplateId";
        String replyToId = "TestReplyToId";

        File temp = File.createTempFile("test.csv", ".csv");

        ValidationException exception = assertThrows(ValidationException.class, () ->
            this.notifyService.sendEmail(templateId, null, temp, replyToId,
                                         HearingStatus.EXCEPTION.name()));

        assertThat(exception.getMessage(), is("An email address is required to send notification"));
    }

    @Test
    @DisplayName("should invoke notification client sendEmail for Exception status")
    void shouldInvokeNotificationClientSendNotificationException() throws NotificationClientException, IOException {
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

        List<SendEmailResponse> responses =  this.notifyService.sendEmail(templateId,
                                                                          List.of(emailTemplateId),
                                                                          temp,
                                                                          replyToId,
                                                                          HearingStatus.EXCEPTION.name());

        assertNotNull(responses);
        verify(this.notificationClient).sendEmail(
            anyString(),
            anyString(),
            anyMap(),
            anyString(),
            anyString()
        );
    }

    @Test
    @DisplayName("should invoke notification client sendEmail for awaiting actuals status")
    void shouldInvokeNotificationClientSendNotificationAwaitingActuals()
        throws NotificationClientException, IOException {
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

        List<SendEmailResponse> responses =  this.notifyService.sendEmail(templateId,
                                                                          List.of(emailTemplateId),
                                                                          temp,
                                                                          replyToId,
                                                                   "awaiting actuals");

        assertNotNull(responses);
        verify(this.notificationClient).sendEmail(
            anyString(),
            anyString(),
            anyMap(),
            anyString(),
            anyString()
        );
    }
}
