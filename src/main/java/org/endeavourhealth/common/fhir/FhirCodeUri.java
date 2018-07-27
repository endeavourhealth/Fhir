package org.endeavourhealth.common.fhir;

public class FhirCodeUri {

    public static final String CODE_SYSTEM_READ2 = "http://read.info/readv2"; //see https://github.com/HL7-UK/System-Identifiers
    public static final String CODE_SYSTEM_SNOMED_CT = "http://snomed.info/sct"; //see https://github.com/HL7-UK/System-Identifiers
    public static final String CODE_SYSTEM_CTV3 = "http://read.info/ctv3"; //see https://github.com/HL7-UK/System-Identifiers
    public static final String CODE_SYSTEM_EMISSNOMED = "http://www.endeavourhealth.org/fhir/emis-snomed";
    public static final String CODE_SYSTEM_EMISPREPARATION = "http://www.endeavourhealth.org/fhir/emis-prepration";
    public static final String CODE_SYSTEM_EMIS_CODE = "http://www.endeavourhealth.org/fhir/emis-code";
    public static final String CODE_SYSTEM_SNOMED_DESCRIPTION_ID = "http://www.endeavourhealth.org/fhir/snomed-description";
    public static final String CODE_SYSTEM_HL7V2_MESSAGE_TYPE = "http://endeavourhealth.org/fhir/v2-message-type";
    public static final String CODE_SYSTEM_ICD10 = "http://hl7.org/fhir/sid/icd-10"; // see https://www.hl7.org/fhir/icd.html
    public static final String CODE_SYSTEM_OPCS4 = "http://www.endeavourhealth.org/fhir/opcs4"; //placeholder - if there is an official OPCS4 code system then please add it here and remove this comment
    public static final String CODE_SYSTEM_CERNER_CODE_ID = "http://www.endeavourhealth.org/fhir/cerner-code-id";
    public static final String CODE_SYSTEM_UK_ED_CODE = "http://www.endeavourhealth.org/fhir/uk-ed-code";
    public static final String CODE_SYSTEM_CERNER_MULTUM_DRUG_ID = "http://www.endeavourhealth.org/fhir/cerner-multum-drug-id";
    public static final String CODE_SYSTEM_CERNER_MULTUM_ALLERGY_CATEGORY_ID = "http://www.endeavourhealth.org/fhir/cerner-multum-allergy-category-id";
    //NOTE: Only add CODE systems to this class (i.e. systems used in CodeableConcepts and Coding structures)
    //NOTE: Consider adding your new coding system to CodeableConceptHelper.findOriginalCode(..)




}
