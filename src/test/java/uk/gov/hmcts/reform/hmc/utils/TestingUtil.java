package uk.gov.hmcts.reform.hmc.utils;

import uk.gov.hmcts.reform.hmc.client.hmi.ListingReasonCode;
import uk.gov.hmcts.reform.hmc.data.CaseCategoriesEntity;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.data.HearingChannelsEntity;
import uk.gov.hmcts.reform.hmc.data.HearingPartyEntity;
import uk.gov.hmcts.reform.hmc.data.NonStandardDurationsEntity;
import uk.gov.hmcts.reform.hmc.data.OrganisationDetailEntity;
import uk.gov.hmcts.reform.hmc.data.PanelAuthorisationRequirementsEntity;
import uk.gov.hmcts.reform.hmc.data.PanelRequirementsEntity;
import uk.gov.hmcts.reform.hmc.data.PanelSpecialismsEntity;
import uk.gov.hmcts.reform.hmc.data.PanelUserRequirementsEntity;
import uk.gov.hmcts.reform.hmc.data.RequiredFacilitiesEntity;
import uk.gov.hmcts.reform.hmc.data.RequiredLocationsEntity;
import uk.gov.hmcts.reform.hmc.data.UnavailabilityEntity;
import uk.gov.hmcts.reform.hmc.model.CaseCategoryType;
import uk.gov.hmcts.reform.hmc.model.DayOfWeekUnAvailableType;
import uk.gov.hmcts.reform.hmc.model.DayOfWeekUnavailable;
import uk.gov.hmcts.reform.hmc.model.LocationType;
import uk.gov.hmcts.reform.hmc.model.PartyType;
import uk.gov.hmcts.reform.hmc.model.RequirementType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static uk.gov.hmcts.reform.hmc.constants.Constants.UNAVAILABILITY_DOW_TYPE;
import static uk.gov.hmcts.reform.hmc.constants.Constants.UNAVAILABILITY_RANGE_TYPE;

public class TestingUtil {
    public static Long ID = 2000000000L;

    private TestingUtil() {
    }

    public static PanelRequirementsEntity panelRequirementsEntity() {
        PanelRequirementsEntity panelRequirements = new PanelRequirementsEntity();
        panelRequirements.setRoleType("RoleType1");
        return panelRequirements;
    }

    public static PanelAuthorisationRequirementsEntity panelAuthorisationRequirementsEntity() {
        PanelAuthorisationRequirementsEntity panelAuthorisationRequirements
            = new PanelAuthorisationRequirementsEntity();
        panelAuthorisationRequirements.setAuthorisationType("AuthorisationType1");
        panelAuthorisationRequirements.setAuthorisationSubType("AuthorisationSubType2");
        return panelAuthorisationRequirements;
    }

    public static PanelSpecialismsEntity panelSpecialismsEntity() {
        PanelSpecialismsEntity paneSpecialisms = new PanelSpecialismsEntity();
        paneSpecialisms.setSpecialismType("Specialism 1");
        return paneSpecialisms;
    }

    public static PanelUserRequirementsEntity panelUserRequirementsEntity() {
        PanelUserRequirementsEntity panelUserRequirements = new PanelUserRequirementsEntity();
        panelUserRequirements.setUserType("Type 1");
        panelUserRequirements.setJudicialUserId("judge1");
        panelUserRequirements.setRequirementType(RequirementType.MUSTINC);
        return panelUserRequirements;
    }

    public static List<UnavailabilityEntity> unavailabilityEntity() {
        List<UnavailabilityEntity> unavailabilityEntities = new ArrayList<>();
        setUnAvailabilityValues(unavailabilityEntities, UNAVAILABILITY_DOW_TYPE);
        setUnAvailabilityValues(unavailabilityEntities, UNAVAILABILITY_RANGE_TYPE);
        return unavailabilityEntities;
    }

    private static void setUnAvailabilityValues(List<UnavailabilityEntity> unavailabilityEntities,
                                                String unavailabilityType) {
        LocalDate startDate = LocalDate.of(2020, 12, 20);
        LocalDate endDate = LocalDate.of(2020, 12, 20);
        UnavailabilityEntity unavailabilityEntity = new UnavailabilityEntity();
        unavailabilityEntity.setDayOfWeekUnavailable(DayOfWeekUnavailable.FRIDAY);
        unavailabilityEntity.setEndDate(endDate);
        unavailabilityEntity.setDayOfWeekUnavailableType(DayOfWeekUnAvailableType.ALL);
        unavailabilityEntity.setStartDate(startDate);
        unavailabilityEntity.setUnAvailabilityType(unavailabilityType);
        unavailabilityEntities.add(unavailabilityEntity);
    }

    public static OrganisationDetailEntity organisationDetailEntity() {
        OrganisationDetailEntity organisationDetailEntity = new OrganisationDetailEntity();
        organisationDetailEntity.setOrganisationName("name");
        organisationDetailEntity.setOrganisationTypeCode("code");
        organisationDetailEntity.setHmctsOrganisationReference("reference");
        return organisationDetailEntity;
    }

    public static List<CaseCategoriesEntity> caseCategoriesEntities() {
        CaseCategoriesEntity caseCategoriesEntity = new CaseCategoriesEntity();
        caseCategoriesEntity.setCategoryType(CaseCategoryType.CASETYPE);
        caseCategoriesEntity.setCaseCategoryValue("PROBATE");
        return List.of(caseCategoriesEntity);
    }

    public static List<HearingChannelsEntity> hearingChannelsEntity() {
        HearingChannelsEntity hce1 = new HearingChannelsEntity();
        hce1.setHearingChannelType("someChannelType");
        HearingChannelsEntity hce2 = new HearingChannelsEntity();
        hce2.setHearingChannelType("someOtherChannelType");
        return List.of(hce1,hce2);
    }

    public static List<NonStandardDurationsEntity> getNonStandardDurationEntities() {
        NonStandardDurationsEntity nonStandardDurationsEntity = new NonStandardDurationsEntity();
        nonStandardDurationsEntity.setNonStandardHearingDurationReasonType("Reason");
        return List.of(nonStandardDurationsEntity);
    }

    public static HearingPartyEntity hearingPartyEntityOrg() {
        HearingPartyEntity entity = new HearingPartyEntity();
        entity.setPartyReference("reference");
        entity.setPartyType(PartyType.ORG);
        entity.setPartyRoleType("role");
        entity.setUnavailabilityEntity(unavailabilityEntity());
        entity.setOrganisationDetailEntity(organisationDetailEntity());

        return entity;
    }

    public static CaseHearingRequestEntity caseHearingRequestEntityWithPartyOrg() {
        CaseHearingRequestEntity entity1 = new CaseHearingRequestEntity();
        entity1.setVersionNumber(1);
        entity1.setCaseHearingID(ID);
        entity1.setHmctsServiceCode("ABA1");
        entity1.setCaseReference("12345");
        entity1.setHearingType("Some hearing type");
        entity1.setListingAutoChangeReasonCode(ListingReasonCode.NO_MAPPING_AVAILABLE.getLabel());
        entity1.setHearingParties(List.of(hearingPartyEntityOrg()));
        entity1.setHearingChannels(hearingChannelsEntity());
        return entity1;
    }

    public static CaseHearingRequestEntity caseHearingRequestEntityWithPartyOrgForClone() {
        CaseHearingRequestEntity entity1 = new CaseHearingRequestEntity();
        entity1.setVersionNumber(1);
        entity1.setCaseHearingID(ID);
        entity1.setHmctsServiceCode("ABA1");
        entity1.setCaseReference("12345");
        entity1.setHearingType("Some hearing type");
        entity1.setHearingParties(List.of(hearingPartyEntityOrg()));
        entity1.setCaseCategories(caseCategoriesEntities());
        entity1.setNonStandardDurations(getNonStandardDurationEntities());
        entity1.setRequiredFacilities(List.of(requiredFacilitiesEntity()));
        entity1.setRequiredLocations(List.of(requiredLocationsEntity()));
        entity1.setPanelRequirements(List.of(panelRequirementsEntity()));
        entity1.setPanelAuthorisationRequirements(List.of(panelAuthorisationRequirementsEntity()));
        entity1.setPanelUserRequirements(List.of(panelUserRequirementsEntity()));
        entity1.setPanelSpecialisms(List.of(panelSpecialismsEntity()));
        return entity1;
    }

    private static RequiredFacilitiesEntity requiredFacilitiesEntity() {
        RequiredFacilitiesEntity entity = new RequiredFacilitiesEntity();
        entity.setFacilityType("string");
        return entity;
    }

    private static RequiredLocationsEntity requiredLocationsEntity() {
        RequiredLocationsEntity entity = new RequiredLocationsEntity();
        entity.setLocationLevelType(LocationType.COURT);
        return entity;
    }

}

