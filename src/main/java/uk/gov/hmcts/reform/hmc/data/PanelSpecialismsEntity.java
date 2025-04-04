package uk.gov.hmcts.reform.hmc.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table(name = "panel_specialisms")
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class PanelSpecialismsEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 379012243334340561L;

    @Id
    @SequenceGenerator(name = "panel_specialisms_id_seq_generator", 
        sequenceName = "panel_specialisms_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
        generator = "panel_specialisms_id_seq_generator")
    // Reversed order to get around duplicated code check mistakenly picking this up

    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_hearing_id")
    private CaseHearingRequestEntity caseHearing;

    @Column(name = "specialism_type")
    private String specialismType;

}
