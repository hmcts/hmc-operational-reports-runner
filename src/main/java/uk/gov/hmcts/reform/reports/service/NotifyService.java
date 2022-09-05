package uk.gov.hmcts.reform.reports.service;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.reports.ApplicationParams;
import uk.gov.service.notify.NotificationClient;
import uk.gov.service.notify.NotificationClientException;
import uk.gov.service.notify.SendEmailResponse;

import javax.validation.ValidationException;

@Service
public class NotifyService {

    private final NotificationClient notificationClient;
    private final ApplicationParams appParams;

    @Autowired
    public NotifyService(ApplicationParams appParams,
                         NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
        this.appParams = appParams;
    }

    @Retryable(value = {ConnectException.class}, backoff = @Backoff(delay = 1000, multiplier = 3))
    public SendEmailResponse sendEmail(String templateId, String emailAddressId, File file) throws IOException, NotificationClientException {
        byte[] fileContents = FileUtils.readFileToByteArray(file);
        String pattern = "dd-MM-yyyy";
        String currentDate = new SimpleDateFormat(pattern).format(new Date());

        if (emailAddressId == null) {
            throw new ValidationException("An email address is required to send notification");
        }

        HashMap<String, Object> personalisation = new HashMap<String,Object>();
        //personalisation.put("link_to_file", notificationClient.prepareUpload(fileContents, true));
        personalisation.put("link_to_file", notificationClient.prepareUpload(fileContents));
        personalisation.put("date", currentDate);

        return this.notificationClient.sendEmail(
            templateId,
            emailAddressId,
            personalisation,
            "null",
            "emailReplyToId"
        );
    }

}
