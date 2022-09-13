package uk.gov.hmcts.reform.hmc.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "contact_details")
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class ContactDetailsEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4144280388835257685L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
        generator = "contact_details_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "contact_type")
    private String contactType;

    @Column(name = "contact_details")
    private String contactDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_party_id")
    private HearingPartyEntity hearingParty;

}
