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

@Table(name = "panel_requirements")
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class PanelRequirementsEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -5997315507728667393L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "panel_requirements_id_seq")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_hearing_id")
    private CaseHearingRequestEntity caseHearing;

    @Column(name = "role_type")
    private String roleType;

}
