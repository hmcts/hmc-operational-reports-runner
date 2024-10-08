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
        if (hearingEntity.getStatus().equals(HearingStatus.LISTED.name())
            || hearingEntity.getStatus().equals(HearingStatus.UPDATE_REQUESTED.name())
            || hearingEntity.getStatus().equals(HearingStatus.UPDATE_SUBMITTED.name())) {
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

    protected boolean isEarliestPlannedHearingDayValid(HearingResponseEntity latestHearingResponse) {
        Optional<HearingDayDetailsEntity> hearingDayDetails =
            latestHearingResponse.getEarliestHearingDayDetails();
        if (latestHearingResponse.hasHearingDayDetails() && hearingDayDetails.isPresent()) {
            HearingDayDetailsEntity hearingDayDetailsEntity = hearingDayDetails.get();
            LocalDateTime date = null;
            if (hearingDayDetailsEntity.getStartDateTime() != null) {
                date = hearingDayDetailsEntity.getStartDateTime();
                log.debug("is now {} after earliest planned day {} ?", LocalDate.now(), date);
                return (!LocalDate.now().isBefore(date.toLocalDate()));
            } else {
                log.debug("startDateTime is null");
                return false;
            }
        }
        return false;
    }

    protected boolean isLastPlannedHearingDayValid(HearingResponseEntity latestHearingResponse) {
        Optional<HearingDayDetailsEntity> hearingDayDetails =
            latestHearingResponse.getLatestHearingDayDetails();
        if (latestHearingResponse.hasHearingDayDetails() && hearingDayDetails.isPresent()) {
            HearingDayDetailsEntity hearingDayDetailsEntity = hearingDayDetails.get();
            return isLastPlannedHearingDayValid(hearingDayDetailsEntity.getEndDateTime());
        }
        return false;
    }

    protected boolean isLastPlannedHearingDayValid(LocalDateTime endDateTime, LocalDate now,
                                                   Long configuredNumberOfDays) {
        if (endDateTime != null) {
            if (endDateTime.toLocalDate().isAfter(now)) {
                log.debug("last planned datetime {} is not after now {}", endDateTime, now);
                return false;
            }
            log.debug("Days between endDateTime {} and now {} = {}. configuredNumberOfDays {}",
                      endDateTime, now, DAYS.between(endDateTime.toLocalDate(), now), configuredNumberOfDays
            );
            return (DAYS.between(endDateTime.toLocalDate(), now) > configuredNumberOfDays);
        } else {
            log.debug("last planned datetime is null");
            return false;
        }
    }

    private boolean isLastPlannedHearingDayValid(LocalDateTime endDate) {
        long configuredNumberOfDays = appParams.getConfiguredNumberOfDays();
        return isLastPlannedHearingDayValid(endDate, LocalDate.now(), configuredNumberOfDays);
    }

}
