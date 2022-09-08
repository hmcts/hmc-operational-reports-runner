package uk.gov.hmcts.reform.hmc.utils;

import com.google.common.collect.Lists;
import uk.gov.hmcts.reform.hmc.client.hmi.ListingReasonCode;
import uk.gov.hmcts.reform.hmc.data.ActualAttendeeIndividualDetailEntity;
import uk.gov.hmcts.reform.hmc.data.ActualHearingDayEntity;
import uk.gov.hmcts.reform.hmc.data.ActualHearingDayPausesEntity;
import uk.gov.hmcts.reform.hmc.data.ActualHearingEntity;
import uk.gov.hmcts.reform.hmc.data.ActualHearingPartyEntity;
import uk.gov.hmcts.reform.hmc.data.ActualPartyRelationshipDetailEntity;
import uk.gov.hmcts.reform.hmc.data.CaseCategoriesEntity;
import uk.gov.hmcts.reform.hmc.data.CaseHearingRequestEntity;
import uk.gov.hmcts.reform.hmc.data.ContactDetailsEntity;
import uk.gov.hmcts.reform.hmc.data.HearingAttendeeDetailsEntity;
import uk.gov.hmcts.reform.hmc.data.HearingChannelsEntity;
import uk.gov.hmcts.reform.hmc.data.HearingDayDetailsEntity;
import uk.gov.hmcts.reform.hmc.data.HearingDayPanelEntity;
import uk.gov.hmcts.reform.hmc.data.HearingEntity;
import uk.gov.hmcts.reform.hmc.data.HearingPartyEntity;
import uk.gov.hmcts.reform.hmc.data.HearingResponseEntity;
import uk.gov.hmcts.reform.hmc.data.IndividualDetailEntity;
import uk.gov.hmcts.reform.hmc.data.LinkedGroupDetails;
import uk.gov.hmcts.reform.hmc.data.NonStandardDurationsEntity;
import uk.gov.hmcts.reform.hmc.data.OrganisationDetailEntity;
import uk.gov.hmcts.reform.hmc.data.PanelAuthorisationRequirementsEntity;
import uk.gov.hmcts.reform.hmc.data.PanelRequirementsEntity;
import uk.gov.hmcts.reform.hmc.data.PanelSpecialismsEntity;
import uk.gov.hmcts.reform.hmc.data.PanelUserRequirementsEntity;
import uk.gov.hmcts.reform.hmc.data.PartyRelationshipDetailsEntity;
import uk.gov.hmcts.reform.hmc.data.ReasonableAdjustmentsEntity;
import uk.gov.hmcts.reform.hmc.data.RequiredFacilitiesEntity;
import uk.gov.hmcts.reform.hmc.data.RequiredLocationsEntity;
import uk.gov.hmcts.reform.hmc.data.UnavailabilityEntity;
import uk.gov.hmcts.reform.hmc.domain.model.enums.LinkType;
import uk.gov.hmcts.reform.hmc.domain.model.enums.ListAssistCaseStatus;
import uk.gov.hmcts.reform.hmc.model.CaseCategoryType;
import uk.gov.hmcts.reform.hmc.model.DayOfWeekUnAvailableType;
import uk.gov.hmcts.reform.hmc.model.DayOfWeekUnavailable;
import uk.gov.hmcts.reform.hmc.model.HearingResultType;
import uk.gov.hmcts.reform.hmc.model.LocationType;
import uk.gov.hmcts.reform.hmc.model.PartyType;
import uk.gov.hmcts.reform.hmc.model.RequirementType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static uk.gov.hmcts.reform.hmc.constants.Constants.CANCELLATION_REQUESTED;
import static uk.gov.hmcts.reform.hmc.constants.Constants.POST_HEARING_STATUS;
import static uk.gov.hmcts.reform.hmc.constants.Constants.UNAVAILABILITY_DOW_TYPE;
import static uk.gov.hmcts.reform.hmc.constants.Constants.UNAVAILABILITY_RANGE_TYPE;

public class TestingUtil {

    public static final String CASE_REFERENCE = "1111222233334444";
    public static final String INVALID_CASE_REFERENCE = "1111222233334445";
    public static final List<String> CANCELLATION_REASON_CODES = List.of("test 1", "test 2");
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
        PanelSpecialismsEntity panelRequirements = new PanelSpecialismsEntity();
        panelRequirements.setSpecialismType("Specialism 1");
        return panelRequirements;
    }

    public static ReasonableAdjustmentsEntity reasonableAdjustmentsEntity() {
        ReasonableAdjustmentsEntity reasonableAdjustments = new ReasonableAdjustmentsEntity();
        reasonableAdjustments.setReasonableAdjustmentCode("First reason");
        return reasonableAdjustments;
    }

    public static PanelUserRequirementsEntity panelUserRequirementsEntity() {
        PanelUserRequirementsEntity panelUserRequirements = new PanelUserRequirementsEntity();
        panelUserRequirements.setUserType("Type 1");
        panelUserRequirements.setJudicialUserId("judge1");
        panelUserRequirements.setRequirementType(RequirementType.MUSTINC);
        return panelUserRequirements;
    }

    public static RequiredFacilitiesEntity facilityEntity() {
        RequiredFacilitiesEntity requiredFacilitiesEntity = new RequiredFacilitiesEntity();
        requiredFacilitiesEntity.setFacilityType("RoleType1");
        return requiredFacilitiesEntity;
    }

    public static RequiredLocationsEntity locationEntity() {
        RequiredLocationsEntity requiredLocationsEntity = new RequiredLocationsEntity();
        requiredLocationsEntity.setLocationLevelType(LocationType.CLUSTER);
        return requiredLocationsEntity;
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

    public static IndividualDetailEntity individualDetailEntity() {
        IndividualDetailEntity individualDetailEntity = new IndividualDetailEntity();
        individualDetailEntity.setTitle("mr");
        individualDetailEntity.setFirstName("joe");
        individualDetailEntity.setLastName("bloggs");
        individualDetailEntity.setChannelType("channelType");
        individualDetailEntity.setInterpreterLanguage("english");
        individualDetailEntity.setVulnerableFlag(true);
        individualDetailEntity.setVulnerabilityDetails("details");
        individualDetailEntity.setCustodyStatus("custodyStatus");
        individualDetailEntity.setOtherReasonableAdjustmentDetails("otherReason");
        return individualDetailEntity;
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

    public static List<String> getHearingChannelsList() {
        return List.of("someChannelType", "someOtherChannelType");
    }

    public static HearingEntity hearingEntity() {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(1L);
        hearingEntity.setStatus(POST_HEARING_STATUS);
        hearingEntity.setLinkedOrder(1L);
        CaseHearingRequestEntity caseHearingRequestEntity = caseHearingRequestEntity();
        caseHearingRequestEntity.setHearingParties(List.of(new HearingPartyEntity()));
        hearingEntity.setCaseHearingRequests(List.of(caseHearingRequestEntity));
        return hearingEntity;
    }

    public static HearingEntity hearingEntityWithLinkDetails() {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(1L);
        hearingEntity.setStatus(POST_HEARING_STATUS);
        hearingEntity.setLinkedOrder(1L);
        LinkedGroupDetails linkedGroupDetailsEntity = linkedGroupDetailsEntity();
        hearingEntity.setLinkedGroupDetails(linkedGroupDetailsEntity);
        hearingEntity.setLinkedOrder(1L);
        hearingEntity.setIsLinkedFlag(Boolean.TRUE);
        CaseHearingRequestEntity caseHearingRequestEntity = caseHearingRequestEntity();
        hearingEntity.setCaseHearingRequests(List.of(caseHearingRequestEntity));
        return hearingEntity;
    }

    public static LinkedGroupDetails linkedGroupDetailsEntity() {
        LinkedGroupDetails entity = new LinkedGroupDetails();
        entity.setLinkedGroupId(1L);
        entity.setRequestName("RequestName");
        entity.setReasonForLink("ReasonForLink");
        entity.setLinkType(LinkType.ORDERED);
        entity.setLinkedComments("linkComments");
        entity.setStatus("PENDING");
        entity.setRequestDateTime(LocalDateTime.now());
        entity.setLinkedGroupLatestVersion(1L);
        return entity;
    }

    public static CaseHearingRequestEntity caseHearingRequestEntity() {
        CaseHearingRequestEntity entity = new CaseHearingRequestEntity();
        entity.setAutoListFlag(false);
        entity.setHearingType("Some hearing type");
        entity.setRequiredDurationInMinutes(10);
        entity.setHearingPriorityType("Priority type");
        entity.setHmctsServiceCode("ABA1");
        entity.setCaseReference("1111222233334444");
        entity.setCaseUrlContextPath("https://www.google.com");
        entity.setHmctsInternalCaseName("Internal case name");
        entity.setOwningLocationId("CMLC123");
        entity.setCaseRestrictedFlag(true);
        entity.setCaseSlaStartDate(LocalDate.parse("2020-08-10"));
        entity.setVersionNumber(1);
        entity.setHearingRequestReceivedDateTime(LocalDateTime.parse("2020-08-10T12:20:00"));
        return entity;

    }

    public static ContactDetailsEntity contactDetailsEntity_Email() {
        ContactDetailsEntity contactDetailsEntity = new ContactDetailsEntity();
        contactDetailsEntity.setContactDetails("hearing.channel@email.com");
        contactDetailsEntity.setContactType("email");
        return contactDetailsEntity;
    }

    public static ContactDetailsEntity contactDetailsEntity_Phone() {
        ContactDetailsEntity contactDetailsEntity = new ContactDetailsEntity();
        contactDetailsEntity.setContactDetails("01234567890");
        contactDetailsEntity.setContactType("phone");
        return contactDetailsEntity;
    }

    public static CaseHearingRequestEntity getCaseHearingsEntities() {
        CaseHearingRequestEntity entity = new CaseHearingRequestEntity();
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(ID);
        hearingEntity.setIsLinkedFlag(Boolean.TRUE);
        entity.setCaseHearingID(ID);
        hearingEntity.setStatus("HEARING_REQUESTED");
        entity.setVersionNumber(1);
        hearingEntity.setIsLinkedFlag(true);
        hearingEntity.setLinkedGroupDetails(getLinkedGroupDetails());
        entity.setHearing(hearingEntity);
        entity.setHmctsServiceCode("ABA1");
        entity.setCaseReference("12345");
        entity.setHearingType("Some hearing type");
        entity.getHearing().setHearingResponses(List.of(hearingResponseEntities()));
        entity.getHearing().getHearingResponses().get(0)
            .setHearingDayDetails(List.of(hearingDayDetailsEntities()));
        entity.setHearingChannels(hearingChannelsEntity());
        return entity;
    }

    public static CaseHearingRequestEntity getCaseHearingsEntities(String status) {
        CaseHearingRequestEntity entity = new CaseHearingRequestEntity();
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(2000000000L);
        hearingEntity.setIsLinkedFlag(Boolean.TRUE);
        entity.setCaseHearingID(2000000000L);
        hearingEntity.setStatus(status);
        entity.setVersionNumber(1);
        hearingEntity.setIsLinkedFlag(true);
        hearingEntity.setLinkedGroupDetails(getLinkedGroupDetails());
        entity.setHearing(hearingEntity);
        entity.setHmctsServiceCode("ABA1");
        entity.setCaseReference("12345");
        entity.setHearingType("Some hearing type");
        entity.getHearing().setHearingResponses(List.of(hearingResponseEntities()));
        entity.getHearing().getHearingResponses().get(0)
            .setHearingDayDetails(List.of(hearingDayDetailsEntities()));
        return entity;
    }

    public static LinkedGroupDetails getLinkedGroupDetails() {
        LinkedGroupDetails linkedGroupDetails = new LinkedGroupDetails();
        linkedGroupDetails.setLinkedGroupId(1L);
        linkedGroupDetails.setRequestId("requestId");
        linkedGroupDetails.setLinkedComments("linkComments");
        linkedGroupDetails.setRequestName("requestName");
        return linkedGroupDetails;
    }

    public static List<NonStandardDurationsEntity> getNonStandardDurationEntities() {
        NonStandardDurationsEntity nonStandardDurationsEntity = new NonStandardDurationsEntity();
        nonStandardDurationsEntity.setNonStandardHearingDurationReasonType("Reason");
        return List.of(nonStandardDurationsEntity);
    }

    public static List<CaseHearingRequestEntity> getCaseHearingsEntitiesWithStatus() {
        List<CaseHearingRequestEntity> entities = new ArrayList<>();
        getFirstEntity(entities);
        getSecondEntity(entities);
        return entities;
    }

    private static void getFirstEntity(List<CaseHearingRequestEntity> entities) {
        CaseHearingRequestEntity entity1 = new CaseHearingRequestEntity();
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(ID);
        entity1.setCaseHearingID(ID);
        hearingEntity.setStatus("HEARING_REQUESTED");
        entity1.setHearing(hearingEntity);
        entity1.setHmctsServiceCode("ABA1");
        entity1.setCaseReference("12345");
        entity1.setHearingType("Some hearing type");
        entity1.getHearing().setHearingResponses(List.of(hearingResponseEntities()));
        entity1.getHearing().getHearingResponses().get(0)
            .setHearingDayDetails(List.of(hearingDayDetailsEntities()));
        entities.add(entity1);
    }

    private static void getSecondEntity(List<CaseHearingRequestEntity> entities) {
        CaseHearingRequestEntity entity1 = new CaseHearingRequestEntity();
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(2000000001L);
        entity1.setCaseHearingID(2000000001L);
        hearingEntity.setStatus("HEARING_UPDATED");
        entity1.setHearing(hearingEntity);
        entity1.setHmctsServiceCode("ABA1");
        entity1.setCaseReference("4567");
        entity1.setHearingType("Some hearing type");
        entity1.getHearing().setHearingResponses(List.of(hearingResponseEntities()));
        entity1.getHearing().getHearingResponses().get(0)
            .setHearingDayDetails(List.of(hearingDayDetailsEntities()));
        entities.add(entity1);
    }

    public static HearingResponseEntity hearingResponseEntities() {
        HearingResponseEntity entity = new HearingResponseEntity();
        entity.setRequestVersion(1);
        entity.setRequestTimeStamp(LocalDateTime.parse("2020-08-10T12:20:00"));
        entity.setHearingResponseId(2L);
        entity.setListingStatus("listingStatus");
        entity.setListingCaseStatus("Case_listingStatus");
        entity.setCancellationReasonType("Cancelled Reason 1");
        return entity;
    }

    public static HearingDayDetailsEntity hearingDayDetailsEntities() {
        HearingDayDetailsEntity entity = new HearingDayDetailsEntity();
        entity.setStartDateTime(LocalDateTime.parse("2020-08-10T12:20:00"));
        entity.setEndDateTime(LocalDateTime.parse("2021-08-10T12:20:00"));
        entity.setVenueId("venue1");
        entity.setRoomId("room1");
        entity.setHearingAttendeeDetails(List.of(hearingAttendeeDetailsEntity()));
        entity.setHearingDayPanel(List.of(hearingDayPanelEntities()));
        return entity;
    }

    public static HearingDayPanelEntity hearingDayPanelEntities() {
        HearingDayPanelEntity entity = new HearingDayPanelEntity();
        entity.setPanelUserId("PanelUser1");
        entity.setIsPresiding(false);
        return entity;
    }

    public static HearingAttendeeDetailsEntity hearingAttendeeDetailsEntity() {
        HearingAttendeeDetailsEntity entity = new HearingAttendeeDetailsEntity();
        entity.setPartyId("Party1");
        entity.setPartySubChannelType("SubChannel1");
        return entity;
    }


    public static HearingEntity getCaseHearingsEntity(PartyType partyType) {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(ID);
        hearingEntity.setStatus("HEARING_REQUESTED");
        hearingEntity.setHearingResponses(List.of(hearingResponseEntity()));
        if (partyType.getLabel() == PartyType.IND.getLabel()) {
            hearingEntity.setCaseHearingRequests(List.of(caseHearingRequestEntityWithPartyOrg()));
        } else {
            hearingEntity.setCaseHearingRequests(List.of(caseHearingRequestEntityWithPartyInd()));
        }
        hearingEntity.getCaseHearingRequests().get(0).setVersionNumber(1);
        return hearingEntity;
    }

    public static HearingEntity getCaseHearingsEntity() {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(ID);
        hearingEntity.setStatus("HEARING_REQUESTED");
        hearingEntity.setHearingResponses(List.of(hearingResponseEntity()));
        hearingEntity.setCaseHearingRequests(List.of(caseHearingRequestEntityWithPartyOrg()));

        hearingEntity.getCaseHearingRequests().get(0).setVersionNumber(1);
        hearingEntity.getCaseHearingRequests().get(0).setHearingParties(List.of(
            hearingPartyEntitySetReference("reference")));
        return hearingEntity;
    }

    public static HearingEntity getCaseHearingsEntity(String status) {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(2000000000L);
        hearingEntity.setStatus(status);
        hearingEntity.setHearingResponses(Arrays.asList(hearingResponseEntity()));
        hearingEntity.setCaseHearingRequests(List.of(caseHearingRequestEntityWithPartyOrg()));

        hearingEntity.getCaseHearingRequests().get(0).setVersionNumber(1);
        hearingEntity.getCaseHearingRequests().get(0).setHearingParties(Arrays.asList(hearingPartyEntityOrg()));
        return hearingEntity;
    }

    public static HearingEntity getHearingsEntityForHearingActuals(String status) {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(ID);
        hearingEntity.setStatus(status);
        hearingEntity.setHearingResponses(List.of(hearingResponseEntity()));
        hearingEntity.getCaseHearingRequests().add(caseHearingRequestEntityWithPartyOrg());

        CaseHearingRequestEntity caseHearingRequestEntity = hearingEntity.getLatestCaseHearingRequest();
        caseHearingRequestEntity.setCaseHearingID(ID);
        caseHearingRequestEntity.setVersionNumber(1);
        caseHearingRequestEntity.setHearingType("hearingType");
        caseHearingRequestEntity.setHmctsServiceCode("serviceCode");
        caseHearingRequestEntity.setCaseReference("caseRef");
        caseHearingRequestEntity.setExternalCaseReference("extCaseRef");
        caseHearingRequestEntity.setCaseUrlContextPath("contextPath");
        caseHearingRequestEntity.setHmctsInternalCaseName("caseName");
        caseHearingRequestEntity.setPublicCaseName("publicCaseName");
        caseHearingRequestEntity.setAdditionalSecurityRequiredFlag(true);
        caseHearingRequestEntity.setInterpreterBookingRequiredFlag(true);
        caseHearingRequestEntity.setOwningLocationId("locationId");
        caseHearingRequestEntity.setCaseRestrictedFlag(true);
        caseHearingRequestEntity.setCaseSlaStartDate(LocalDate.of(2000, 01, 01));
        caseHearingRequestEntity.setHearingParties(List.of(
            hearingPartyEntitySetReference("reference2"),
            hearingPartyEntitySetReference("reference"),
            hearingPartyEntitySetReference("reference2")
        ));
        caseHearingRequestEntity.setCaseCategories(caseCategoriesEntities());

        hearingEntity.getHearingResponses().get(0).setActualHearingEntity(actualHearingEntity(PartyType.ORG));
        return hearingEntity;
    }

    public static HearingEntity getHearingsEntityForHearingActualsIndividual() {
        HearingEntity hearingEntity = new HearingEntity();
        hearingEntity.setId(ID);
        hearingEntity.setStatus("HEARING_REQUESTED");
        hearingEntity.setHearingResponses(List.of(hearingResponseEntity()));
        hearingEntity.getCaseHearingRequests().add(caseHearingRequestEntityWithPartyInd());

        CaseHearingRequestEntity caseHearingRequestEntity = hearingEntity.getLatestCaseHearingRequest();
        caseHearingRequestEntity.setVersionNumber(1);
        caseHearingRequestEntity.setCaseHearingID(ID);
        caseHearingRequestEntity.setHearingType("hearingType");
        caseHearingRequestEntity.setHmctsServiceCode("serviceCode");
        caseHearingRequestEntity.setCaseReference("caseRef");
        caseHearingRequestEntity.setExternalCaseReference("extCaseRef");
        caseHearingRequestEntity.setCaseUrlContextPath("contextPath");
        caseHearingRequestEntity.setHmctsInternalCaseName("caseName");
        caseHearingRequestEntity.setPublicCaseName("publicCaseName");
        caseHearingRequestEntity.setAdditionalSecurityRequiredFlag(true);
        caseHearingRequestEntity.setInterpreterBookingRequiredFlag(true);
        caseHearingRequestEntity.setOwningLocationId("locationId");
        caseHearingRequestEntity.setCaseRestrictedFlag(true);
        caseHearingRequestEntity.setCaseSlaStartDate(LocalDate.of(2000, 01, 01));
        caseHearingRequestEntity.setHearingParties(List.of(hearingPartyEntityInd()));
        caseHearingRequestEntity.setCaseCategories(caseCategoriesEntities());

        hearingEntity.getHearingResponses().get(0).setActualHearingEntity(actualHearingEntity(PartyType.IND));
        return hearingEntity;
    }

    public static ActualHearingEntity actualHearingEntity(PartyType partyType) {
        ActualHearingEntity entity = new ActualHearingEntity();
        entity.setActualHearingType("hearingType");
        entity.setHearingResultType(HearingResultType.ADJOURNED);
        entity.setHearingResultReasonType("resultReason");
        entity.setHearingResultDate(LocalDate.of(2000, 01, 01));
        entity.setActualHearingDay(List.of(actualHearingDayEntity(partyType)));
        entity.setActualHearingIsFinalFlag(true);
        return entity;
    }

    public static ActualPartyRelationshipDetailEntity actualPartyRelationshipDetailEntity(
        ActualHearingPartyEntity actualHearingPartyEntity) {
        ActualPartyRelationshipDetailEntity entity = ActualPartyRelationshipDetailEntity
            .builder()
            .actualPartyRelationshipId(1L)
            .targetActualParty(actualHearingPartyEntity)
            .sourceActualParty(actualHearingPartyEntity).build();
        return entity;
    }

    public static ActualHearingDayEntity actualHearingDayEntity(PartyType partyType) {
        ActualHearingDayEntity entity = new ActualHearingDayEntity();
        entity.setHearingDate(LocalDate.of(2000, 01, 01));
        entity.setStartDateTime(LocalDateTime.parse("2021-08-10T12:20:00"));
        entity.setEndDateTime(LocalDateTime.parse("2021-08-10T12:20:00"));
        entity.setActualHearingDayPauses(List.of(actualHearingDayPausesEntity()));
        entity.setActualHearingParty(List.of(actualHearingPartyEntity(partyType)));
        return entity;
    }

    public static ActualHearingDayPausesEntity actualHearingDayPausesEntity() {
        ActualHearingDayPausesEntity entity = new ActualHearingDayPausesEntity();
        entity.setPauseDateTime(LocalDateTime.parse("2021-08-10T12:20:00"));
        entity.setResumeDateTime(LocalDateTime.parse("2021-08-10T12:20:00"));
        return entity;
    }

    public static ActualHearingPartyEntity actualHearingPartyEntity(PartyType partyType) {
        ActualHearingPartyEntity entity = new ActualHearingPartyEntity();
        entity.setActualPartyId(1L);
        entity.setPartyId("1");
        entity.setActualPartyRoleType("roleType");
        entity.setDidNotAttendFlag(false);
        entity.setActualAttendeeIndividualDetail(actualAttendeeIndividualDetailEntity(partyType));
        entity.setActualPartyRelationshipDetail(List.of(actualPartyRelationshipDetailEntity(entity)));
        return entity;
    }

    public static ActualAttendeeIndividualDetailEntity actualAttendeeIndividualDetailEntity(PartyType partyType) {
        ActualAttendeeIndividualDetailEntity entity = new ActualAttendeeIndividualDetailEntity();
        if (PartyType.IND.equals(partyType)) {
            entity.setFirstName("firstName");
            entity.setLastName("lastName");
        } else {
            entity.setPartyOrganisationName("partyOrgName");
        }
        entity.setPartyActualSubChannelType("partySubChannel");
        return entity;
    }

    public static HearingResponseEntity hearingResponseEntity() {
        HearingResponseEntity entity = new HearingResponseEntity();
        entity.setRequestVersion(1);
        entity.setRequestTimeStamp(LocalDateTime.parse("2020-08-10T12:20:00"));
        entity.setHearingResponseId(2L);
        entity.setRequestVersion(10);
        entity.setListingStatus("Fixed");
        entity.setListingCaseStatus(ListAssistCaseStatus.CASE_CREATED.name());
        entity.setCancellationReasonType("Cancelled Reason 1");
        entity.setHearingDayDetails(List.of(hearingDayDetailsEntity()));
        return entity;
    }

    public static HearingResponseEntity hearingResponseEntity(Integer version, Integer requestVersion,
                                                              LocalDateTime requestTimestamp,
                                                              List<HearingDayDetailsEntity> hearingDayDetailsEntities) {
        HearingResponseEntity entity = new HearingResponseEntity();
        entity.setRequestVersion(requestVersion);
        entity.setRequestTimeStamp(requestTimestamp);
        entity.setHearingResponseId(2L);
        entity.setListingStatus("Fixed");
        entity.setListingCaseStatus(ListAssistCaseStatus.CASE_CREATED.name());
        entity.setCancellationReasonType("Cancelled Reason 1");
        entity.setHearingDayDetails(hearingDayDetailsEntities);
        return entity;
    }

    public static HearingDayDetailsEntity hearingDayDetailsEntity() {
        HearingDayDetailsEntity entity = new HearingDayDetailsEntity();
        entity.setStartDateTime(LocalDateTime.of(2000, 8, 10, 12, 20));
        entity.setEndDateTime(LocalDateTime.of(2000, 8, 10, 12, 20));
        entity.setRoomId("roomId");
        entity.setVenueId("venueId");

        HearingAttendeeDetailsEntity attendee = new HearingAttendeeDetailsEntity();
        attendee.setId(ID);
        attendee.setPartySubChannelType("partySubChannelA");
        attendee.setPartyId("reference");

        HearingAttendeeDetailsEntity attendee2 = new HearingAttendeeDetailsEntity();
        attendee2.setId(ID);
        attendee2.setPartySubChannelType("partySubChannelB");
        attendee2.setPartyId("reference2");


        HearingAttendeeDetailsEntity attendee3 = new HearingAttendeeDetailsEntity();
        attendee3.setId(ID);
        attendee3.setPartySubChannelType("partySubChannelC");
        attendee3.setPartyId("reference3");
        entity.setHearingAttendeeDetails(List.of(attendee, attendee2, attendee3));
        return entity;
    }

    public static HearingDayDetailsEntity hearingDayDetailsEntity(LocalDateTime startDateTime) {
        HearingDayDetailsEntity entity = new HearingDayDetailsEntity();
        entity.setStartDateTime(startDateTime);
        entity.setEndDateTime(LocalDateTime.parse("2021-08-10T12:20:00"));
        entity.setVenueId("venue1");
        entity.setRoomId("room1");
        return entity;
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


    public static HearingPartyEntity hearingPartyEntityForClone() {
        HearingPartyEntity entity = new HearingPartyEntity();
        entity.setPartyReference("reference");
        entity.setPartyType(PartyType.ORG);
        entity.setPartyRoleType("role");
        entity.setUnavailabilityEntity(unavailabilityEntity());
        entity.setContactDetailsEntity(List.of(contactDetailsEntity_Email()));
        entity.setOrganisationDetailEntity(organisationDetailEntity());
        entity.setReasonableAdjustmentsEntity(List.of(reasonableAdjustmentsEntity()));
        entity.setPartyRelationshipDetailsEntity(List.of(
            partyRelationshipDetailsEntity(
                "P1", "A"),
            partyRelationshipDetailsEntity(
                "P2", "B")
        ));

        return entity;
    }

    public static HearingPartyEntity hearingPartyEntitySetReference(String partyReference) {
        HearingPartyEntity entity = new HearingPartyEntity();
        entity.setPartyReference(partyReference);
        entity.setPartyType(PartyType.ORG);
        entity.setPartyRoleType("role");
        entity.setUnavailabilityEntity(unavailabilityEntity());
        entity.setOrganisationDetailEntity(organisationDetailEntity());
        entity.setCaseHearing(caseHearingRequestEntityWithPartyOrg());

        return entity;
    }

    public static HearingPartyEntity hearingPartyEntityInd() {
        HearingPartyEntity entity = new HearingPartyEntity();
        entity.setPartyReference("reference");
        entity.setPartyType(PartyType.IND);
        entity.setPartyRoleType("role");
        entity.setContactDetailsEntity(List.of(contactDetailsEntity_Email(), contactDetailsEntity_Phone()));
        entity.setIndividualDetailEntity(individualDetailEntity());
        entity.setCaseHearing(caseHearingRequestEntityWithPartyOrg());
        entity.setReasonableAdjustmentsEntity(List.of(reasonableAdjustmentsEntity()));
        entity.setPartyRelationshipDetailsEntity(List.of(
            partyRelationshipDetailsEntity("P1", "A"),
            partyRelationshipDetailsEntity("P2", "B")
        ));
        return entity;
    }

    public static PartyRelationshipDetailsEntity partyRelationshipDetailsEntity(String targetTechPartyId,
                                                                                 String relationshipType) {

        HearingPartyEntity targetHearingPartyEntity = new HearingPartyEntity();
        targetHearingPartyEntity.setPartyReference(targetTechPartyId);

        return PartyRelationshipDetailsEntity.builder()
            .targetTechParty(targetHearingPartyEntity)
            .relationshipType(relationshipType)
            .build();
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

    private static CaseHearingRequestEntity caseHearingRequestEntityWithPartyInd() {
        CaseHearingRequestEntity entity1 = new CaseHearingRequestEntity();
        entity1.setVersionNumber(1);
        entity1.setCaseHearingID(ID);

        entity1.setHearing(getCaseHearingsEntity());
        entity1.setHmctsServiceCode("ABA1");
        entity1.setCaseReference("12345");
        entity1.setHearingType("Some hearing type");
        entity1.setListingAutoChangeReasonCode(ListingReasonCode.NO_MAPPING_AVAILABLE.getLabel());
        entity1.getHearing().setHearingResponses(List.of(hearingResponseEntities()));
        entity1.getHearing().getHearingResponses().get(0)
            .setHearingDayDetails(List.of(hearingDayDetailsEntities()));
        entity1.setHearingParties(List.of(hearingPartyEntityInd()));
        entity1.setHearingChannels(hearingChannelsEntity());
        return entity1;
    }

    public static LocalDateTime convertDateTime(String dateStr) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateStr, format);
    }
}

