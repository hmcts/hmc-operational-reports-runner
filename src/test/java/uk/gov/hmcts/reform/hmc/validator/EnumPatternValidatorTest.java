package uk.gov.hmcts.reform.hmc.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.hmcts.reform.hmc.model.CaseCategory;
import uk.gov.hmcts.reform.hmc.model.CaseCategoryType;
import uk.gov.hmcts.reform.hmc.model.HearingLocation;
import uk.gov.hmcts.reform.hmc.model.LocationType;
import uk.gov.hmcts.reform.hmc.model.PanelPreference;
import uk.gov.hmcts.reform.hmc.model.RequirementType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.gov.hmcts.reform.hmc.exceptions.ValidationError.CATEGORY_TYPE_EMPTY;
import static uk.gov.hmcts.reform.hmc.exceptions.ValidationError.LOCATION_ID_EMPTY;

class EnumPatternValidatorTest {

    static Validator validator;

    private static final Logger logger = LoggerFactory.getLogger(EnumPatternValidatorTest.class);

    private static final String FRIDAY = "Friday";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenInvalidLocationTypeIsNull() {
        HearingLocation location = new HearingLocation();
        location.setLocationId("Id");
        location.setLocationType(null);
        Set<ConstraintViolation<HearingLocation>> violations = validator.validate(location);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        List<String> validationErrors = new ArrayList<>();
        violations.forEach(e -> validationErrors.add(e.getMessage()));
        assertEquals("Unsupported type for locationType", validationErrors.get(0));
    }

    @Test
    void whenInvalidLocationTypeIsEmpty() {
        HearingLocation location = new HearingLocation();
        location.setLocationId("Id");
        location.setLocationType("");
        Set<ConstraintViolation<HearingLocation>> violations = validator.validate(location);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        List<String> validationErrors = new ArrayList<>();
        violations.forEach(e -> validationErrors.add(e.getMessage()));
        assertEquals("Unsupported type for locationType", validationErrors.get(0));
    }

    @Test
    void whenInvalidLocationIdIsEmpty() {
        HearingLocation location = new HearingLocation();
        location.setLocationId("");
        location.setLocationType(LocationType.CLUSTER.toString());
        Set<ConstraintViolation<HearingLocation>> violations = validator.validate(location);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        List<String> validationErrors = new ArrayList<>();
        violations.forEach(e -> validationErrors.add(e.getMessage()));
        assertEquals(LOCATION_ID_EMPTY, validationErrors.get(0));
    }

    @Test
    void whenInvalidLocationType() {
        HearingLocation location = new HearingLocation();
        location.setLocationId("Loc");
        location.setLocationType("Loc");
        Set<ConstraintViolation<HearingLocation>> violations = validator.validate(location);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        List<String> validationErrors = new ArrayList<>();
        violations.forEach(e -> validationErrors.add(e.getMessage()));
        assertEquals("Unsupported type for locationType", validationErrors.get(0));
    }

    @Test
    void whenValidLocationType() {
        HearingLocation location = new HearingLocation();
        location.setLocationType(LocationType.COURT.toString());
        location.setLocationId("LocType");
        Set<ConstraintViolation<HearingLocation>> violations = validator.validate(location);
        assertTrue(violations.isEmpty());
    }

    @Test
    void whenInvalidCaseCategoryIsNull() {
        CaseCategory category = new CaseCategory();
        category.setCategoryValue("categoryValue");
        category.setCategoryType(null);
        Set<ConstraintViolation<CaseCategory>> violations = validator.validate(category);
        assertFalse(violations.isEmpty());
        List<String> validationErrors = new ArrayList<>();
        violations.forEach(e -> {
            validationErrors.add(e.getMessage());
            logger.info(e.getMessage());
        });
        assertEquals(2, violations.size());
        assertTrue(validationErrors.contains("Unsupported type for categoryType"));
        assertTrue(validationErrors.contains(CATEGORY_TYPE_EMPTY));
    }

    @Test
    void whenInvalidCaseCategoryIsEmpty() {
        CaseCategory category = getCaseCategory();
        category.setCategoryType("");
        Set<ConstraintViolation<CaseCategory>> violations = validator.validate(category);
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
        List<String> validationErrors = new ArrayList<>();
        violations.forEach(e -> validationErrors.add(e.getMessage()));
        assertTrue(validationErrors.contains("Unsupported type for categoryType"));
        assertTrue(validationErrors.contains(CATEGORY_TYPE_EMPTY));
    }

    private CaseCategory getCaseCategory() {
        CaseCategory category = new CaseCategory();
        category.setCategoryValue("categoryValue");
        return category;
    }

    @Test
    void whenInvalidCaseCategory() {
        CaseCategory category = new CaseCategory();
        category.setCategoryValue("categoryValue");
        category.setCategoryType("categoryType");
        Set<ConstraintViolation<CaseCategory>> violations = validator.validate(category);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        List<String> validationErrors = new ArrayList<>();
        violations.forEach(e -> validationErrors.add(e.getMessage()));
        assertEquals("Unsupported type for categoryType", validationErrors.get(0));
    }

    @Test
    void whenValidCaseCategory() {
        CaseCategory category = new CaseCategory();
        category.setCategoryValue("caseValue");
        category.setCategoryType(CaseCategoryType.CASESUBTYPE.toString());
        Set<ConstraintViolation<CaseCategory>> violations = validator.validate(category);
        assertTrue(violations.isEmpty());
    }


    @Test
    void whenInvalidRequirementTypeIsEmpty() {
        PanelPreference panelPreference = new PanelPreference();
        panelPreference.setMemberID("id");
        panelPreference.setMemberType("memType");
        panelPreference.setRequirementType("");
        Set<ConstraintViolation<PanelPreference>> violations = validator.validate(panelPreference);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        List<String> validationErrors = new ArrayList<>();
        violations.forEach(e -> validationErrors.add(e.getMessage()));
        assertEquals("Unsupported type for requirementType", validationErrors.get(0));
    }

    @Test
    void whenInvalidRequirementType() {
        PanelPreference panelPreference = new PanelPreference();
        panelPreference.setMemberID("id");
        panelPreference.setMemberType("memType");
        panelPreference.setRequirementType("preference");
        Set<ConstraintViolation<PanelPreference>> violations = validator.validate(panelPreference);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        List<String> validationErrors = new ArrayList<>();
        violations.forEach(e -> validationErrors.add(e.getMessage()));
        assertEquals("Unsupported type for requirementType", validationErrors.get(0));
    }

    @Test
    void whenValidRequirementType() {
        PanelPreference panelPreference = new PanelPreference();
        panelPreference.setMemberID("id");
        panelPreference.setMemberType("memType");
        panelPreference.setRequirementType(RequirementType.MUSTINC.toString());
        Set<ConstraintViolation<PanelPreference>> violations = validator.validate(panelPreference);
        assertTrue(violations.isEmpty());
    }

}
