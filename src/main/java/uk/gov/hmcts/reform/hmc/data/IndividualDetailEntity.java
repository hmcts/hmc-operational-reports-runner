package uk.gov.hmcts.reform.hmc.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table(name = "individual_detail")
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class IndividualDetailEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4817549124719790363L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
        generator = "individual_detail_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "vulnerability_details")
    private String vulnerabilityDetails;

    @Column(name = "vulnerable_flag")
    private Boolean vulnerableFlag;

    @Column(name = "interpreter_language")
    private String interpreterLanguage;

    @Column(name = "channel_type")
    private String channelType;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_party_id")
    private HearingPartyEntity hearingParty;

    @Column(name = "other_reasonable_adjustment_details")
    private String otherReasonableAdjustmentDetails;

    @Column(name = "custody_status")
    private String custodyStatus;

}
