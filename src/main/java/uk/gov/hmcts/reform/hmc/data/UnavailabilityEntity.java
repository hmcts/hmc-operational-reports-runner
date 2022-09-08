package uk.gov.hmcts.reform.hmc.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.hmc.model.DayOfWeekUnAvailableType;
import uk.gov.hmcts.reform.hmc.model.DayOfWeekUnavailable;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "unavailability")
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class UnavailabilityEntity extends BaseEntity  implements Serializable {

    private static final long serialVersionUID = 5423332025288476165L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
        generator = "unavailability_id_seq")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week_unavailable")
    private DayOfWeekUnavailable dayOfWeekUnavailable;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week_unavailable_type")
    private DayOfWeekUnAvailableType dayOfWeekUnavailableType;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "unavailability_type")
    private String unAvailabilityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_party_id")
    private HearingPartyEntity hearingParty;

    public UnavailabilityEntity(UnavailabilityEntity original) {
        this.id = original.id;
        this.dayOfWeekUnavailable = original.dayOfWeekUnavailable;
        this.dayOfWeekUnavailableType = original.dayOfWeekUnavailableType;
        this.startDate = original.startDate;
        this.endDate = original.endDate;
        this.unAvailabilityType = original.unAvailabilityType;
        this.hearingParty = original.hearingParty;
    }
}
