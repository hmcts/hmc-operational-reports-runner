package uk.gov.hmcts.reform.hmc.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.hmc.ApplicationParams;
import uk.gov.hmcts.reform.hmc.data.HearingDayDetailsEntity;
import uk.gov.hmcts.reform.hmc.data.HearingEntity;
import uk.gov.hmcts.reform.hmc.data.HearingResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Component
public class HearingActualsHelper {

    public static final String AWAITING_ACTUALS = "AWAITING_ACTUALS";
    private final ApplicationParams appParams;

    public HearingActualsHelper(ApplicationParams appParams) {
        this.appParams = appParams;
    }

    public String getHearingStatus(HearingEntity hearingEntity) {
        final String[] hearingStatus = {null};
        switch (hearingEntity.getStatus()) {
            case "LISTED", "UPDATE_REQUESTED", "UPDATE_SUBMITTED" -> {
                hearingStatus[0] = hearingEntity.getStatus();
                Optional<HearingResponseEntity> hearingResponse = hearingEntity.getLatestHearingResponse();
                hearingResponse.ifPresent(
                        latestHearingResponse -> {
                            if (isEarliestPlannedHearingDayValid(latestHearingResponse)
                                    && isLastPlannedHearingDaysValid(latestHearingResponse)) {
                                hearingStatus[0] = AWAITING_ACTUALS;
                            }
                        });
            }
            default -> hearingStatus[0] = hearingEntity.getStatus();
        }
        return hearingStatus[0];
    }

    public boolean isEarliestPlannedHearingDayValid(HearingResponseEntity latestHearingResponse) {
        Optional<HearingDayDetailsEntity> hearingDayDetails =
                latestHearingResponse.getEarliestHearingDayDetails();
        if (latestHearingResponse.hasHearingDayDetails() && hearingDayDetails.isPresent()) {
            HearingDayDetailsEntity hearingDayDetailsEntity = hearingDayDetails.get();
            return (LocalDate.now().isAfter(hearingDayDetailsEntity.getStartDateTime().toLocalDate()));
        }
        return false;
    }

    public boolean isLastPlannedHearingDaysValid(HearingResponseEntity latestHearingResponse) {
        Optional<HearingDayDetailsEntity> hearingDayDetails =
                latestHearingResponse.getLatestHearingDayDetails();
        if (latestHearingResponse.hasHearingDayDetails() && hearingDayDetails.isPresent()) {
            HearingDayDetailsEntity hearingDayDetailsEntity = hearingDayDetails.get();
            return isLastPlannedHearingDaysValid(hearingDayDetailsEntity.getStartDateTime());
        }
        return false;
    }

    public boolean isLastPlannedHearingDaysValid(LocalDateTime endDate) {
        long configuredNumberOfDays = appParams.getConfiguredNumberOfDays();
        return isLastPlannedHearingDaysValid(endDate, LocalDate.now(), configuredNumberOfDays);
    }

    public boolean isLastPlannedHearingDaysValid(LocalDateTime endDateTime, LocalDate now,
                                                 Long configuredNumberOfDays) {
        if (endDateTime.toLocalDate().isAfter(now)) {
            log.debug("endDateTime {} is after now {}", endDateTime, now);
            return false;
        }
        log.debug("Days between endDateTime {} and now {} = {}. configuredNumberOfDays {}",
                endDateTime, now, DAYS.between(endDateTime.toLocalDate(), now), configuredNumberOfDays);
        return (DAYS.between(endDateTime.toLocalDate(), now) > configuredNumberOfDays);
    }
}
