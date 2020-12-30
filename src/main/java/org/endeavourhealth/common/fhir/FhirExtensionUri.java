package org.endeavourhealth.common.fhir;

public class FhirExtensionUri {

    //extensions specific to Patient resource
    public final static String PATIENT_RESIDENTIAL_INSTITUTE_CODE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-patient-residential-institute-code-extension";
    public final static String PATIENT_NHS_NUMBER_VERIFICATION_STATUS = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-nhs-number-verification-status-extension";
    public final static String PATIENT_SPINE_SENSITIVE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-patient-spine-sensitive-extension";
    public final static String PATIENT_ETHNICITY = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-ethnic-category-extension";
    public final static String PATIENT_RELIGION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-religion-category-extension";
    public final static String PATIENT_CONTACT_DOB = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-contact-dob-category-extension";
    public final static String PATIENT_CONTACT_MAIN_LANGUAGE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-contact-main-language-category-extension";
    public final static String PATIENT_BIRTH_DATE_TIME = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-contact-birth-date-time-category-extension"; //ignore the word "contact" in here, it's just the patient's datetime of birth
    //public final static String PATIENT_INTERPRETER_REQUIRED = "http://hl7.org/fhir/StructureDefinition/patient-interpreter-required-extension"; duplicate of Speaks English
    public final static String PATIENT_SPEAKS_ENGLISH = "http://hl7.org/fhir/StructureDefinition/patient-speaks-english-extension";
    public final static String PATIENT_IS_TEST_PATIENT = "http://endeavourhealth.org/fhir/StructureDefinition/patient-is-test-patient-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to EpisodeOfCare resources
    public final static String EPISODE_OF_CARE_REGISTRATION_TYPE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-patient-registration-type-extension";
    public final static String EPISODE_OF_CARE_REGISTRATION_STATUS = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-episode-registration-status-extension";
    public final static String EPISODE_OF_CARE_OUTCOME = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-episode-outcome";
    public final static String EPISODE_OF_CARE_PRIORITY = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-episode-priority";
    public final static String EPISODE_OF_CARE_PCCARRIVAL = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-episode-pccarrival";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to Observation resources
    public final static String OBSERVATION_PATIENT_DELAY_DAYS = "http://endeavourhealth.org/fhir/StructureDefinition/acute-patient-delay-days-extension";

    //extensions specific to Appointment resource
    public final static String APPOINTMENT_PATIENT_WAIT = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-appointment-wait-extension";
    public final static String APPOINTMENT_PATIENT_DELAY = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-appointment-delay-extension";
    public final static String APPOINTMENT_DNA_REASON_CODE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-appointment-dna-reason-extension";
    public final static String APPOINTMENT_SENT_IN = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-appointment-sent-in-extension";
    public final static String APPOINTMENT_LEFT = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-appointment-left-extension";
    public final static String APPOINTMENT_CANCELLATION_DATE = "http://endeavourhealth.org/fhir/StructureDefinition/appointment-cancellation-date-extension";
    public final static String APPOINTMENT_BOOKING_DATE = "http://endeavourhealth.org/fhir/StructureDefinition/appointment-booking-date-extension";
    public final static String APPOINTMENT_ORIGINAL_IDENTIFIER = "http://endeavourhealth.org/fhir/StructureDefinition/appointment-original-identifier";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to Location resource
    public final static String LOCATION_MAIN_CONTACT = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-location-main-contact-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to Organisation resource
    public final static String ORGANISATION_MAIN_LOCATION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-mainlocation-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to Problem resource
    public final static String PROBLEM_EXPECTED_DURATION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-problem-expectedduration-extension";
    public final static String PROBLEM_LAST_REVIEWED = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-problem-lastreviewed-extension";
    public final static String _PROBLEM_LAST_REVIEWED__DATE = "date";
    public final static String _PROBLEM_LAST_REVIEWED__PERFORMER = "performer";
    public final static String PROBLEM_SIGNIFICANCE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-problem-significance-extension";
    public final static String PROBLEM_ASSOCIATED_RESOURCE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-problem-associated-extension";
    public final static String PROBLEM_RELATED = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-problem-related-extension";
    public final static String _PROBLEM_RELATED__TARGET = "target";
    public final static String _PROBLEM_RELATED__TYPE = "type";
    public final static String PROBLEM_EPISODICITY = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-problem-episodicity";
    //public final static String PROBLEM_ADDITIONAL_NOTES = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-problem-additional-notes";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to Condition resources
    public final static String CONDITION_PART_OF_PROBLEM = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-condition-partofproblemepisode-extension";
    public final static String CONDITION_SEQUENCE_NUMBER = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-condition-sequence-number-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to FamilyMemberHistory resources
    public final static String FAMILY_MEMBER_HISTORY_REPORTED_BY = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-family-member-history-reporter-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to ReferralRequest resources
    public final static String REFERRAL_REQUEST_SEND_MODE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-referral-request-send-mode-extension";
    public final static String REFERRAL_REQUEST_RECIPIENT_SERVICE_TYPE = "http://endeavourhealth.org/fhir/StructureDefinition/referral-request-recipient-service-type-extension";
    public final static String REFERRAL_REQUEST_RECIPIENT_FREE_TEXT = "http://endeavourhealth.org/fhir/StructureDefinition/referral-request-recipient-free-text-extension";
    public final static String REFERRAL_REQUEST_STATUS_HISTORY = "http://endeavourhealth.org/fhir/StructureDefinition/referral-request-status-history-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to DiagnosticReport resources
    public final static String DIAGNOSTIC_REPORT_FILED_BY = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-diagnostic-report-filed-by-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to Task resource
    public final static String TASK_TYPE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-task-type-extension";
    public final static String TASK_STATUS = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-task-status-extension";
    public final static String TASK_PRIORITY = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-task-priority-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to Schedule resource
    public final static String SCHEDULE_LOCATION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-location-extension";
    public final static String SCHEDULE_ADDITIONAL_ACTOR = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-additional-actor-extension";
    public final static String SCHEDULE_LOCATION_TYPE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-location-type-extension";
    public final static String SCHEDULE_NAME = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-schedule-name-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to Encounter resource
    public final static String ENCOUNTER_SOURCE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-encounter-source";
    public final static String ENCOUNTER_INCOMPLETE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-encounter-incomplete";
    public final static String ENCOUNTER_COMPONENTS = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-encounter-components-extension";
    public final static String ENCOUNTER_PATIENT_CLASS_OTHER = "http://hl7.org/fhir/StructureDefinition/encounter-patient-class-other-extension";
    public final static String ENCOUNTER_LOCATION_TYPE = "http://hl7.org/fhir/StructureDefinition/encounter-location-type-extension";
    public final static String ENCOUNTER_ADMISSION_TYPE = "http://hl7.org/fhir/StructureDefinition/encounter-admission-type";
    public static final String ENCOUNTER_CLASS = "http://hl7.org/fhir/StructureDefinition/encounter-patient-class-other-extension";
    public final static String ENCOUNTER_SPECIALTY = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-encounter-specialty-extension";
    public final static String ENCOUNTER_TREATMENT_FUNCTION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-encounter-treatment-function-extension";
    public final static String ENCOUNTER_LOCATION_REFERENCE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-encounter-location-reference-extension";

    public final static String ENCOUNTER_AE_ATTENDANCE_CATEGORY = "http://hl7.org/fhir/StructureDefinition/encounter-ae-attendance-category-extension";
    public final static String ENCOUNTER_AE_ATTENDANCE_SOURCE = "http://hl7.org/fhir/StructureDefinition/encounter-ae-attendance-source-extension";
    public final static String ENCOUNTER_AE_ARRIVAL_MODE = "http://hl7.org/fhir/StructureDefinition/encounter-ae-arrival-mode-extension";
    public final static String ENCOUNTER_AE_DEPARTMENT_TYPE = "http://hl7.org/fhir/StructureDefinition/encounter-ae-department-type-extension";
    public final static String ENCOUNTER_AE_DISCHARGE_STATUS = "http://hl7.org/fhir/StructureDefinition/encounter-ae-discharge-status-extension";
    public final static String ENCOUNTER_AE_DISCHARGE_DESTINATION = "http://hl7.org/fhir/StructureDefinition/encounter-ae-discharge-destination-extension";

    public final static String ENCOUNTER_ADMISSION_METHOD = "http://hl7.org/fhir/StructureDefinition/encounter-admission-method-extension";
    public final static String ENCOUNTER_ADMISSION_SOURCE = "http://hl7.org/fhir/StructureDefinition/encounter-admission-source-extension";
    public final static String ENCOUNTER_ADMISSION_WARD = "http://hl7.org/fhir/StructureDefinition/encounter-admission-ward-extension";
    public final static String ENCOUNTER_DISCHARGE_METHOD = "http://hl7.org/fhir/StructureDefinition/encounter-discharge-method-extension";
    public final static String ENCOUNTER_DISCHARGE_WARD = "http://hl7.org/fhir/StructureDefinition/encounter-discharge-ward-extension";
    public final static String ENCOUNTER_DISCHARGE_DESTINATION = "http://hl7.org/fhir/StructureDefinition/encounter-discharge-destination-extension";
    public final static String ENCOUNTER_ADMIN_CATEGORY = "http://hl7.org/fhir/StructureDefinition/encounter-admin-category-extension";
    public final static String ENCOUNTER_APPOINTMENT_ATTENDED = "http://hl7.org/fhir/StructureDefinition/encounter-appointment-attended-extension";
    public final static String ENCOUNTER_APPOINTMENT_OUTCOME = "http://hl7.org/fhir/StructureDefinition/encounter-appointment-outcome-extension";
    public final static String ENCOUNTER_APPOINTMENT_REFERRAL_SOURCE = "http://hl7.org/fhir/StructureDefinition/encounter-appointment-referral-source-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to MedicationStatement (profile is MedicationAuthorisation) resource
    public final static String MEDICATION_AUTHORISATION_PRIVATE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-privateprescription-extension";
    public final static String MEDICATION_AUTHORISATION_FIRST_ISSUE_DATE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication-authorisation-firstissuedate-extension";
    public final static String MEDICATION_AUTHORISATION_MOST_RECENT_ISSUE_DATE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication-authorisation-mostrecentissuedate-extension";
    public final static String MEDICATION_AUTHORISATION_NUMBER_OF_REPEATS_ALLOWED = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication-authorisation-numberofrepeatsallowed-extension";
    public final static String MEDICATION_AUTHORISATION_NUMBER_OF_REPEATS_ISSUED = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication-authorisation-numberofrepeatsissued-extension";
    public final static String MEDICATION_AUTHORISATION_CANCELLATION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication-authorisation-cancellation-extension";
    public final static String MEDICATION_AUTHORISATION_EXPECTED_SUPPLY_DURATION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication-authorisation-expectedsupplyduration-extension";
    public final static String MEDICATION_AUTHORISATION_QUANTITY = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication-authorisation-quantity-extension";
    public final static String MEDICATION_AUTHORISATION_TYPE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication-authorisation-type-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to MedicationOrder resource
    public final static String MEDICATION_ORDER_ESTIMATED_COST = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication-order-estimatednhscost-extension";
    public final static String MEDICATION_ORDER_AUTHORISATION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication-order-authorisation-extension";
    public final static String MEDICATION_ORDER_SUPPLY_TYPE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication-supply-type-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    // extensions specific to the MessageHeader resource
    public static final String EXTENSION_HL7V2_DESTINATION_SOFTWARE = "http://endeavourhealth.org/fhir/StructureDefinition/message-header-destination-software-extension";
    public static final String EXTENSION_HL7V2_MESSAGE_CONTROL_ID = "http://endeavourhealth.org/fhir/StructureDefinition/message-header-message-control-id-extension";
    public static final String EXTENSION_HL7V2_SEQUENCE_NUMBER = "http://endeavourhealth.org/fhir/StructureDefinition/message-header-sequence-number-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to Procedure resources
    //public final static String PROCEDURE_IS_PRIMARY = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-procedure-is-primary-extension";
    public final static String PROCEDURE_SEQUENCE_NUMBER = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-procedure-sequence-number-extension";
    public final static String PROCEDURE_SPECIALITY_GROUP = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-procedure-speciality-group-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions specific to ProcedureRequest resource
    public final static String PROCEDURE_REQUEST_LOCATION_DESCRIPTION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-procedure-request-location-extension";
    public final static String PROCEDURE_REQUEST_SCHEDULE_TEXT = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-procedure-request-schedule-text-extension";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)

    //extensions used by multiple resources
    public final static String RECORDED_BY = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-recorded-by-extension";
    public final static String RECORDED_DATE = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-recorded-date-extension";
    public final static String EXTERNAL_DOCUMENT = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-external-document-extension";
    public final static String ACTIVE_PERIOD = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-activeperiod-extension";
    public final static String ASSOCIATED_ENCOUNTER = "http://hl7.org/fhir/StructureDefinition/encounter-associatedEncounter";
    public final static String PHARMACY_TEXT = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-pharmacytext-extension";
    public final static String PATIENT_TEXT = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-patienttext-extension";
    public final static String PRESCRIBED_AS_CONTRACEPTION = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-prescribedascontraception-extension";
    public final static String QUANTITY_FREE_TEXT = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-medication-quantity-text-extension";
    public final static String IS_REVIEW = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-problem-review";
    public final static String IS_CONFIDENTIAL = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-confidential";
    public final static String IS_CONSENT = "http://endeavourhealth.org/fhir/StructureDefinition/primarycare-consent"; //
    public final static String HL7_MESSAGE_TYPE = "http://endeavourhealth.org/fhir/v2-message-type-extension";
    public final static String RESOURCE_CONTEXT = "http://endeavourhealth.org/fhir/StructureDefinition/context-extension";
    public final static String PARENT_RESOURCE = "http://endeavourhealth.org/fhir/StructureDefinition/parent-resource";
    public final static String IS_PRIMARY = "http://endeavourhealth.org/fhir/StructureDefinition/is-primary-extension";
    public final static String ADDITIONAL = "http://discoverydataservice.org/fhir/StructureDefinition/additional-extension";
    public final static String RESOURCE_CHANGED = "http://discoverydataservice.org/fhir/StructureDefinition/resource_changed";
    //DON'T FORGET TO ADD YOUR NEW EXTENSION TO THE FHIR PROFILES (ask Drew if not sure)
}
