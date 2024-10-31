package uk.gov.hmcts.reform.hmc.service;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.hmc.domain.model.enums.HearingStatus;
import uk.gov.service.notify.NotificationClient;
import uk.gov.service.notify.NotificationClientException;
import uk.gov.service.notify.SendEmailResponse;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static uk.gov.hmcts.reform.hmc.ApplicationExecutor.AWAITING_ACTUALS;
import static uk.gov.service.notify.NotificationClient.prepareUpload;

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
    public List<SendEmailResponse> sendEmail(String templateId,
                                             List<String> emailAddressIds,
                                             File csvFile,
                                             String replyToEmailAddress, String status)
        throws IOException, NotificationClientException {
        if (null == emailAddressIds || emailAddressIds.isEmpty()) {
            throw new ValidationException("An email address is required to send notification");
        }

        HashMap<String, Object> personalisation = new HashMap<>();
        addPersonalisation(csvFile, status, personalisation);

        log.info("Invoking Notify Service for " + status);
        List<SendEmailResponse> responses = new ArrayList<>();
        for (String emailAddress : emailAddressIds) {
            responses.add(this.notificationClient.sendEmail(
                templateId,
                emailAddress,
                personalisation,
                StringUtils.EMPTY,
                replyToEmailAddress
            ));
        }
        return responses;
    }

    private void addPersonalisation(File csvFile, String status, HashMap<String, Object> personalisation)
        throws NotificationClientException, IOException {
        personalisation.put("link_to_file", prepareUpload(
            FileUtils.readFileToByteArray(csvFile),
            csvFile.getName()
        ));
        personalisation.put("date", new SimpleDateFormat(PATTERN, Locale.ENGLISH).format(new Date()));
        if (status.equalsIgnoreCase(HearingStatus.EXCEPTION.name())) {
            personalisation.put("subjectStatus", status);
            personalisation.put("bodyStatus", "an " + status.toLowerCase());
        } else if (status.equalsIgnoreCase(AWAITING_ACTUALS)) {
            personalisation.put("subjectStatus", status.toUpperCase(Locale.ROOT));
            personalisation.put("bodyStatus", "an awaiting hearing actuals");
        }
    }
}
