package uk.gov.hmcts.reform.reports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.hmcts.reform.reports.service.NotifyService;
import uk.gov.service.notify.NotificationClientException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Named;

@Slf4j
@Named
public class ApplicationExecutor {

    private NotifyService notifyService;
    private ApplicationParams appParams;

    @Autowired
    public ApplicationExecutor(ApplicationParams appParams,
                               NotifyService notifyService) {
        this.notifyService = notifyService;
        this.appParams = appParams;
    }

    public void execute() throws IOException, NotificationClientException {
        log.info("Hmc-Operational-Reports-Runner started...");
        List<String[]> dataLines = new ArrayList<>();
        dataLines.add(
            new String[]
                {"John", "Doe", "38", "Comment Data\nAnother line of comment data"});
        dataLines.add(
            new String[]
                {"Jane", "Doe, Jr.", "19", "She said \"I'm being quoted\""});

        File csvOutputFile = new File("test.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                .map(this::convertToCsv)
                .forEach(pw::println);
        }

        notifyService.sendEmail(appParams.getNotifyErrorTemplateId(), appParams.getNotifyErrorEmailAddress(),
            csvOutputFile, appParams.getNotifyErrorReplyToEmailAddress());
        log.info("Hmc-Operational-Reports-Runner finished.");
    }

    public String convertToCsv(String[] data) {
        return Stream.of(data)
            .map(this::escapeSpecialCharacters)
            .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

}
