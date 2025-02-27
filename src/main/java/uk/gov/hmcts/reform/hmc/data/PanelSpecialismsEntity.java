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

@Table(name = "panel_specialisms")
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class PanelSpecialismsEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 379012243334340561L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
        generator = "panel_specialisms_id_seq")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_hearing_id")
    private CaseHearingRequestEntity caseHearing;

    @Column(name = "specialism_type")
    private String specialismType;

}
