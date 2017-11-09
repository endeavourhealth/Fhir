package org.endeavourhealth.common.fhir;

public abstract class FhirUri
{
    public final static String IDENTIFIER_SYSTEM_NHSNUMBER = "http://fhir.nhs.net/Id/nhs-number";
    public final static String IDENTIFIER_SYSTEM_CHINUMBER = "http://www.endeavourhealth.org/fhir/Identifier/chinumber";
    public final static String IDENTIFIER_SYSTEM_ODS_CODE = "http://fhir.nhs.net/Id/ods-organization-code";
    public final static String IDENTIFIER_SYSTEM_GMC_NUMBER = "http://endeavourhealth.org/fhir/Identifier/gmc-number";
    public final static String IDENTIFIER_SYSTEM_CONSULTANT_CODE = "http://endeavourhealth.org/fhir/Identifier/consultant-code";
    public final static String IDENTIFIER_SYSTEM_DOCTOR_INDEX_NUMBER = "http://endeavourhealth.org/fhir/Identifier/doctor-index-number";
    public final static String IDENTIFIER_SYSTEM_GMP_PPD_CODE = "http://endeavourhealth.org/fhir/Identifier/gmp-ppd-code";
    public final static String IDENTIFIER_SYSTEM_GDP_NUMBER = "http://endeavourhealth.org/fhir/Identifier/gdp-number";
    public final static String IDENTIFIER_SYSTEM_UBRN = "http://endeavourhealth.org/fhir/Identifier/ubrn";
    public final static String IDENTIFIER_SYSTEM_EMIS_PATIENT_GUID = "http://emishealth.com/identifier/patient-guid"; //placeholder
    public final static String IDENTIFIER_SYSTEM_EMIS_PATIENT_NUMBER = "http://emishealth.com/identifier/patient-number"; //placeholder
    public final static String IDENTIFIER_SYSTEM_EMIS_DOCUMENT_GUID = "http://emishealth.com/identifier/document-guid"; //placeholder
    public final static String IDENTIFIER_SYSTEM_VISION_DOCUMENT_GUID = "http://visionhealth.co.uk/identifier/document-guid"; //placeholder
    public final static String IDENTIFIER_SYSTEM_TPP_PATIENT_ID = "http://tpp-uk.com/identifier/patient-id"; //placeholder
    public final static String IDENTIFIER_SYSTEM_HOMERTON_CNN_PATIENT_ID = "http://endeavourhealth.org/fhir/id/v2-local-patient-id/homerton-cnn";
    public final static String IDENTIFIER_SYSTEM_HOMERTON_MRN_PATIENT_ID = "http://endeavourhealth.org/fhir/id/v2-local-patient-id/homerton-mrn";
    public final static String IDENTIFIER_SYSTEM_HOMERTON_PERSONID_PATIENT_ID = "http://endeavourhealth.org/fhir/id/v2-local-patient-id/homerton-personid";
    public final static String IDENTIFIER_SYSTEM_BARTS_MRN_PATIENT_ID = "http://endeavourhealth.org/fhir/id/v2-local-patient-id/barts-mrn";
    public final static String IDENTIFIER_SYSTEM_NEWHAM_CNN_PATIENT_ID = "http://endeavourhealth.org/fhir/id/v2-local-patient-id/newham-cnn";
    public final static String IDENTIFIER_SYSTEM_NEWHAM_MRN_PATIENT_ID = "http://endeavourhealth.org/fhir/id/v2-local-patient-id/newham-mrn";
    public final static String IDENTIFIER_SYSTEM_HOMERTON_FIN_EPISODE_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/homerton-fin";
    public final static String IDENTIFIER_SYSTEM_HOMERTON_ATTENDANCE_NO_EPISODE_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/homerton-attendanceno";
    public final static String IDENTIFIER_SYSTEM_BARTS_FIN_EPISODE_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/barts-fin";
    public final static String IDENTIFIER_SYSTEM_BARTS_VISIT_NO_EPISODE_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/barts-visitno";
    public final static String IDENTIFIER_SYSTEM_HOMERTON_PRIMARY_PRACTITIONER_ID = "http://endeavourhealth.org/fhir/id/v2-local-practitioner-id/homerton-personnelprimaryid";
    public final static String IDENTIFIER_SYSTEM_BARTS_ORG_DR_NUMBER = "http://endeavourhealth.org/fhir/id/v2-local-practitioner-id/barts-org-dr-number";
    public final static String IDENTIFIER_SYSTEM_BARTS_PERSONNEL_ID = "http://endeavourhealth.org/fhir/id/v2-local-practitioner-id/barts-personnel-id";
    public final static String IDENTIFIER_SYSTEM_CONDITION_CATEGORY = "http://hl7.org/fhir/condition-category";

    public final static String CODE_SYSTEM_READ2 = "http://read.info/readv2"; //see https://github.com/HL7-UK/System-Identifiers
    public final static String CODE_SYSTEM_SNOMED_CT = "http://snomed.info/sct"; //see https://github.com/HL7-UK/System-Identifiers
    public final static String CODE_SYSTEM_CTV3 = "http://read.info/ctv3"; //see https://github.com/HL7-UK/System-Identifiers
    public final static String CODE_SYSTEM_EMISSNOMED = "http://www.endeavourhealth.org/fhir/emis-snomed";
    public final static String CODE_SYSTEM_EMISPREPARATION = "http://www.endeavourhealth.org/fhir/emis-prepration";
    public final static String CODE_SYSTEM_EMIS_CODE = "http://www.endeavourhealth.org/fhir/emis-code";
    public final static String CODE_SYSTEM_SNOMED_DESCRIPTION_ID = "http://www.endeavourhealth.org/fhir/snomed-description";
    public final static String CODE_SYSTEM_HL7V2_MESSAGE_TYPE = "http://endeavourhealth.org/fhir/v2-message-type";
    public final static String CODE_SYSTEM_ICD10 = "http://hl7.org/fhir/sid/icd-10"; // see https://www.hl7.org/fhir/icd.html
    public final static String CODE_SYSTEM_OPCS4 = "http://www.endeavourhealth.org/fhir/opcs4"; //placeholder - if there is an official OPCS4 code system then please add it here and remove this comment

    public final static String PROFILE_URI_ORGANIZATION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-organization";
    public final static String PROFILE_URI_LOCATION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-location";
    public final static String PROFILE_URI_PRACTITIONER = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-practitioner";
    public final static String PROFILE_URI_SCHEDULE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-schedule";
    public final static String PROFILE_URI_SLOT = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-slot";
    public final static String PROFILE_URI_APPOINTMENT = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-appointment";
    public final static String PROFILE_URI_ALLERGY_INTOLERANCE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-allergy-intolerance";
    public final static String PROFILE_URI_CONDITION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-condition";
    public final static String PROFILE_URI_DIAGNOSTIC_ORDER = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-diagnostic-order";
    public final static String PROFILE_URI_DIAGNOSTIC_REPORT = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-diagnostic-report";
    public final static String PROFILE_URI_FAMILY_MEMBER_HISTORY = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-family-member-history";
    public final static String PROFILE_URI_IMMUNIZATION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-immunization";
    public final static String PROFILE_URI_MEDICATION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication";
    public final static String PROFILE_URI_MEDICATION_AUTHORISATION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication-authorisation";
    public final static String PROFILE_URI_MEDICATION_ORDER = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication-order";
    public final static String PROFILE_URI_OBSERVATION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-observation";
    public final static String PROFILE_URI_PROBLEM = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-problem";
    public final static String PROFILE_URI_PROCEDURE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-procedure";
    public final static String PROFILE_URI_PROCEDURE_REQUEST = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-procedure-request";
    public final static String PROFILE_URI_REFERRAL_REQUEST = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-referral-request";
    public final static String PROFILE_URI_SPECIMIN = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-specimen";
    public final static String PROFILE_URI_ENCOUNTER = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-encounter";
    public final static String PROFILE_URI_PATIENT = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-patient";
    public final static String PROFILE_URI_EPISODE_OF_CARE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-episode-of-care";
    public final static String PROFILE_URI_TASK = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-task";
}
