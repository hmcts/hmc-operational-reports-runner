package uk.gov.hmcts.reform.hmc.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.hmc.ApplicationParams;
import uk.gov.hmcts.reform.hmc.data.HearingDayDetailsEntity;
import uk.gov.hmcts.reform.hmc.data.HearingEntity;
import uk.gov.hmcts.reform.hmc.data.HearingResponseEntity;
import uk.gov.hmcts.reform.hmc.domain.model.enums.HearingStatus;

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
        if (hearingEntity.getStatus().equals(HearingStatus.LISTED)
            || hearingEntity.getStatus().equals(HearingStatus.UPDATE_REQUESTED)
            || hearingEntity.getStatus().equals(HearingStatus.UPDATE_SUBMITTED)) {
            Optional<HearingResponseEntity> hearingResponse = hearingEntity.getLatestHearingResponse();
            if (hearingResponse.isPresent()) {
                HearingResponseEntity latestHearingResponse = hearingResponse.get();
                if (isEarliestPlannedHearingDayValid(latestHearingResponse)
                    && isLastPlannedHearingDayValid(latestHearingResponse)) {
                    return AWAITING_ACTUALS;
                }
            }
        }
        return hearingEntity.getStatus();
    }

    public boolean isEarliestPlannedHearingDayValid(HearingResponseEntity latestHearingResponse) {
        Optional<HearingDayDetailsEntity> hearingDayDetails =
                latestHearingResponse.getEarliestHearingDayDetails();
        if (latestHearingResponse.hasHearingDayDetails() && hearingDayDetails.isPresent()) {
            HearingDayDetailsEntity hearingDayDetailsEntity = hearingDayDetails.get();
            log.debug("is now {} after earliest planned day {} ?", LocalDate.now(),
                    hearingDayDetailsEntity.getStartDateTime().toLocalDate());
            return (LocalDate.now().isAfter(hearingDayDetailsEntity.getStartDateTime().toLocalDate()));
        }
        return false;
    }

    public boolean isLastPlannedHearingDayValid(HearingResponseEntity latestHearingResponse) {
        Optional<HearingDayDetailsEntity> hearingDayDetails =
                latestHearingResponse.getLatestHearingDayDetails();
        if (latestHearingResponse.hasHearingDayDetails() && hearingDayDetails.isPresent()) {
            HearingDayDetailsEntity hearingDayDetailsEntity = hearingDayDetails.get();
            return isLastPlannedHearingDayValid(hearingDayDetailsEntity.getEndDateTime());
        }
        return false;
    }

    public boolean isLastPlannedHearingDayValid(LocalDateTime endDate) {
        long configuredNumberOfDays = appParams.getConfiguredNumberOfDays();
        return isLastPlannedHearingDayValid(endDate, LocalDate.now(), configuredNumberOfDays);
    }

    public boolean isLastPlannedHearingDayValid(LocalDateTime endDateTime, LocalDate now,
                                                 Long configuredNumberOfDays) {
        if (endDateTime.toLocalDate().isAfter(now)) {
            log.debug("last planned datetime {} is not after now {}", endDateTime, now);
            return false;
        }
        log.debug("Days between endDateTime {} and now {} = {}. configuredNumberOfDays {}",
                endDateTime, now, DAYS.between(endDateTime.toLocalDate(), now), configuredNumberOfDays);
        return (DAYS.between(endDateTime.toLocalDate(), now) > configuredNumberOfDays);
    }
}
