package uk.gov.hmcts.reform.reports.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import uk.gov.service.notify.NotificationClient;
import uk.gov.service.notify.NotificationClientException;
import uk.gov.service.notify.SendEmailResponse;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.validation.ValidationException;

@Slf4j
@Service
public class NotifyService {

    private final NotificationClient notificationClient;
    private static final String PATTERN = "dd-MM-yyyy";

    @Autowired
    public NotifyService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    @Retryable(value = {ConnectException.class}, backoff = @Backoff(delay = 1000, multiplier = 3))
    public SendEmailResponse sendEmail(String templateId, String emailAddressId, File file, String replyToEmailAddress)
        throws IOException, NotificationClientException {
        if (emailAddressId == null) {
            throw new ValidationException("An email address is required to send notification");
        }

        HashMap<String, Object> personalisation = new HashMap<>();
        personalisation.put("link_to_file", notificationClient.prepareUpload(FileUtils.readFileToByteArray(file),
                                                                             true));
        personalisation.put("date", new SimpleDateFormat(PATTERN, Locale.ENGLISH).format(new Date()));

        log.info("Invoking Notify Service");
        return this.notificationClient.sendEmail(
            templateId,
            emailAddressId,
            personalisation,
            StringUtils.EMPTY,
            replyToEmailAddress
        );
    }

}
