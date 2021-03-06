package org.endeavourhealth.common.fhir;

import java.util.HashSet;
import java.util.Set;

public class FhirIdentifierUri {

    public final static String IDENTIFIER_SYSTEM_NHSNUMBER = "http://fhir.nhs.net/Id/nhs-number";
    public final static String IDENTIFIER_SYSTEM_CHINUMBER = "http://www.endeavourhealth.org/fhir/Identifier/chinumber";
    public final static String IDENTIFIER_SYSTEM_ODS_CODE = "http://fhir.nhs.net/Id/ods-organization-code";
    public final static String IDENTIFIER_SYSTEM_GMC_NUMBER = "http://endeavourhealth.org/fhir/Identifier/gmc-number";
    public final static String IDENTIFIER_SYSTEM_NMC_NUMBER = "http://endeavourhealth.org/fhir/Identifier/nmc-number";
    public final static String IDENTIFIER_SYSTEM_CONSULTANT_CODE = "http://endeavourhealth.org/fhir/Identifier/consultant-code";
    public final static String IDENTIFIER_SYSTEM_DOCTOR_INDEX_NUMBER = "http://endeavourhealth.org/fhir/Identifier/doctor-index-number";
    public final static String IDENTIFIER_SYSTEM_GMP_PPD_CODE = "http://endeavourhealth.org/fhir/Identifier/gmp-ppd-code";
    public final static String IDENTIFIER_SYSTEM_GDP_NUMBER = "http://endeavourhealth.org/fhir/Identifier/gdp-number"; //general dental practitioner
    public final static String IDENTIFIER_SYSTEM_RPSGB_NUMBER = "http://endeavourhealth.org/fhir/Identifier/rpsgb-number"; //royal pharmaceutical society of Great Britain
    public final static String IDENTIFIER_SYSTEM_GPhC_NUMBER = "http://endeavourhealth.org/fhir/Identifier/gphc-number"; //general pharmaceutical council
    public final static String IDENTIFIER_SYSTEM_PRESCRIBING_ID = "http://endeavourhealth.org/fhir/Identifier/prescribing-id"; //AKA PPAID
    public final static String IDENTIFIER_SYSTEM_UBRN = "http://endeavourhealth.org/fhir/Identifier/ubrn";
    public final static String IDENTIFIER_SYSTEM_HPC = "http://endeavourhealth.org/fhir/Identifier/hpc";  // HCPC Health & Care Practitioner
    public final static String IDENTIFIER_SYSTEM_ADASTRA_CASENO = "http://oneadvanced.com/identifier/adastra-case-no"; //placeholder
    public final static String IDENTIFIER_SYSTEM_ADASTRA_CASETAG = "http://oneadvanced.com/identifier/adastra-case-tag"; //placeholder
    public final static String IDENTIFIER_SYSTEM_EMIS_PATIENT_GUID = "http://emishealth.com/identifier/patient-guid"; //placeholder
    public final static String IDENTIFIER_SYSTEM_EMIS_PATIENT_NUMBER = "http://emishealth.com/identifier/patient-number"; //placeholder
    public final static String IDENTIFIER_SYSTEM_EMIS_DOCUMENT_GUID = "http://emishealth.com/identifier/document-guid"; //placeholder
    public final static String IDENTIFIER_SYSTEM_EMIS_CDB_NUMBER = "http://emishealth.com/identifier/cdb-number"; //placeholder
    public final static String IDENTIFIER_SYSTEM_VISION_PATIENT_GUID = "http://visionhealth.co.uk/identifier/patient-guid"; //placeholder
    public final static String IDENTIFIER_SYSTEM_VISION_PATIENT_NUMBER = "http://visionhealth.co.uk/identifier/patient-number"; //placeholder
    public final static String IDENTIFIER_SYSTEM_VISION_DOCUMENT_GUID = "http://visionhealth.co.uk/identifier/document-guid"; //placeholder
    public final static String IDENTIFIER_SYSTEM_TPP_PATIENT_ID = "http://tpp-uk.com/identifier/patient-id"; //placeholder
    public final static String IDENTIFIER_SYSTEM_TPP_STAFF_USERNAME = "http://tpp-uk.com/identifier/staff-username"; //placeholder
    public final static String IDENTIFIER_SYSTEM_TPP_STAFF_SMARTCARD_ID = "http://tpp-uk.com/identifier/staff-smartcard-id"; //placeholder
    public final static String IDENTIFIER_SYSTEM_TPP_STAFF_GP_LOCAL_CODE = "http://tpp-uk.com/identifier/staff-gp-local-code"; //placeholder
    public final static String IDENTIFIER_SYSTEM_TPP_LOCATION_ID = "http://tpp-uk.com/identifier/location-id";
    public final static String IDENTIFIER_SYSTEM_TPP_ORGANIZATION_ID = "http://tpp-uk.com/identifier/organization-id";
    public final static String IDENTIFIER_SYSTEM_HOMERTON_CNN_PATIENT_ID = "http://endeavourhealth.org/fhir/id/v2-local-patient-id/homerton-cnn";
    public final static String IDENTIFIER_SYSTEM_HOMERTON_MRN_PATIENT_ID = "http://endeavourhealth.org/fhir/id/v2-local-patient-id/homerton-mrn";
    public final static String IDENTIFIER_SYSTEM_HOMERTON_PERSONID_PATIENT_ID = "http://endeavourhealth.org/fhir/id/v2-local-patient-id/homerton-personid";
    public final static String IDENTIFIER_SYSTEM_BARTS_MRN_PATIENT_ID = "http://endeavourhealth.org/fhir/id/v2-local-patient-id/barts-mrn";
    public final static String IDENTIFIER_SYSTEM_NEWHAM_CNN_PATIENT_ID = "http://endeavourhealth.org/fhir/id/v2-local-patient-id/newham-cnn";
    public final static String IDENTIFIER_SYSTEM_NEWHAM_MRN_PATIENT_ID = "http://endeavourhealth.org/fhir/id/v2-local-patient-id/newham-mrn";
    public final static String IDENTIFIER_SYSTEM_ROYAL_FREE_MRN_PATIENT_ID = "http://endeavourhealth.org/fhir/id/v2-local-patient-id/royal-free-mrn";
    public final static String IDENTIFIER_SYSTEM_HOMERTON_FIN_EPISODE_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/homerton-fin";
    public final static String IDENTIFIER_SYSTEM_HOMERTON_ATTENDANCE_NO_EPISODE_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/homerton-attendanceno";
    public final static String IDENTIFIER_SYSTEM_BARTS_FIN_EPISODE_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/barts-fin";
    public final static String IDENTIFIER_SYSTEM_BARTS_VISIT_NO_EPISODE_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/barts-visitno";
    public final static String IDENTIFIER_SYSTEM_BARTS_ENCOUNTER_ID = "http://endeavourhealth.org/fhir/id/v2-local-encounter-id/barts-encounter-id";
    public final static String IDENTIFIER_SYSTEM_HOMERTON_ENCOUNTER_ID = "http://endeavourhealth.org/fhir/id/v2-local-encounter-id/homerton-encounter-id";
    public final static String IDENTIFIER_SYSTEM_BARTS_LOCATION_ID = "http://endeavourhealth.org/fhir/id/v2-local-location-id/barts-location-id";
    public final static String IDENTIFIER_SYSTEM_HOMERTON_PRIMARY_PRACTITIONER_ID = "http://endeavourhealth.org/fhir/id/v2-local-practitioner-id/homerton-personnelprimaryid";
    public final static String IDENTIFIER_SYSTEM_BARTS_ORG_DR_NUMBER = "http://endeavourhealth.org/fhir/id/v2-local-practitioner-id/barts-org-dr-number";
    public final static String IDENTIFIER_SYSTEM_BARTS_PERSONNEL_ID = "http://endeavourhealth.org/fhir/id/v2-local-practitioner-id/barts-personnel-id";
    //NOTE - if an identifier should be searchable then make sure to add to the fn at the bottom of this class

    // New Cerner Aliases
    public final static String IDENTIFIER_SYSTEM_CERNER_INTERNAL_PERSON = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-internal-person-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_COMMUNITY_MEDICAL_RECORD = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-community-medical-record";
    public final static String IDENTIFIER_SYSTEM_CERNER_DONOR_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-donor-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_DONOR_NUMBER = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-donor-number";
    public final static String IDENTIFIER_SYSTEM_CERNER_DRIVING_LICENSE_NUMBER = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-driving-license-number";
    public final static String IDENTIFIER_SYSTEM_CERNER_FILLER_ORDER = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-filler-order";
    public final static String IDENTIFIER_SYSTEM_CERNER_HISTORICAL_CMRN = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-historical-cmrn";
    public final static String IDENTIFIER_SYSTEM_CERNER_HISTORICAL_MRN = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-historical-mrn";
    public final static String IDENTIFIER_SYSTEM_CERNER_HNA_PAT_SYS_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-hna-pat-sys-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_NMDP = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-nmdp";
    public final static String IDENTIFIER_SYSTEM_CERNER_PASSPORT_NUMBER = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-passport-number";
    public final static String IDENTIFIER_SYSTEM_CERNER_V400_OCF_PATIENT = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-v400-classic-patient-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_PERSON_NAME = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-person-name";
    public final static String IDENTIFIER_SYSTEM_CERNER_PLACER_ORDER = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-placer-order";
    public final static String IDENTIFIER_SYSTEM_CERNER_STATE_HEALTH_NUMBER = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-state-health-number";
    public final static String IDENTIFIER_SYSTEM_CERNER_UNOS = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-unos";
    public final static String IDENTIFIER_SYSTEM_CERNER_HNA_PERSON_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-hna-person-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_MILITARY_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-military-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_OTHER_PERSON_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-other-person-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_REFERRING_MRN = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-referring-mrn";
    public final static String IDENTIFIER_SYSTEM_CERNER_OUTREACH_PERSON = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-outreach-person-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_PBS_PATIENT = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-pbs-patient-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_PHYSICIAN_OFFICE = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-physician-office-system-person-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_ACCOUNT_NUMBER = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-account-number";
    public final static String IDENTIFIER_SYSTEM_CERNER_SECURE_MESSAGING = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-secure-messaging";
    public final static String IDENTIFIER_SYSTEM_CERNER_HIC_RECIPIENT = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-hic-recipient-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_INTERNATIONAL_DONOR = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-international-donor-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_NMDP_DONOR = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-nmdp-donor-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_NMDP_RECIPIENT = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-nmdp-recipient-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_OPO_DONOR = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-opo-donor-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_OPO_RECIPIENT = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-opo-recipient-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_PX_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-px-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_UNOS_DONOR = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-unos-donor-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_CEPHALOSPORINS = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-cephalosporins";
    public final static String IDENTIFIER_SYSTEM_CERNER_PENICILLINS = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-penicillins";
    public final static String IDENTIFIER_SYSTEM_CERNER_PASSWORD = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-password";
    public final static String IDENTIFIER_SYSTEM_CERNER_VERSION = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-version";
    public final static String IDENTIFIER_SYSTEM_CERNER_MEMBER_NUMBER = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-member-number";
    public final static String IDENTIFIER_SYSTEM_CERNER_CORTISOL_LEVEL_4HR = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-cortisol-level-4hr";
    public final static String IDENTIFIER_SYSTEM_CERNER_CORTISOL_LEVEL = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-cortisol-level";
    public final static String IDENTIFIER_SYSTEM_CERNER_PERSON_MEDIA_ALIAS = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-person-media-alias";
    public final static String IDENTIFIER_SYSTEM_CERNER_NATIONAL_PATIENT_ID = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-national-patient-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_BIOMETRIC = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-biometric-id";
    public final static String IDENTIFIER_SYSTEM_CERNER_CARD_NUMBER = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-card-number";
    public final static String IDENTIFIER_SYSTEM_CERNER_HIE_COMMUNITY = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-hie-community";
    public final static String IDENTIFIER_SYSTEM_CERNER_RNJ = "http://endeavourhealth.org/fhir/id/v2-local-episode-id/cerner-rnj";
    public static final String IDENTIFIER_SYSTEM_CERNER_NOMENCLATURE_ID = "http://cerner.com/fhir/nomenclature-id";
    public static final String IDENTIFIER_SYSTEM_CERNER_ENCOUNTER_SLICE_ID = "http://cerner.com/fhir/encounter-slice-id";
    public static final String IDENTIFIER_SYSTEM_CERNER_ENCOUNTER_ID = "http://cerner.com/fhir/encounter-id";
    public static final String IDENTIFIER_SYSTEM_CERNER_EPISODE_ID = "http://cerner.com/fhir/episode-id";
    public static final String IDENTIFIER_SYSTEM_CERNER_PROBLEM_ID = "http://cerner.com/fhir/problem-id";
    public static final String IDENTIFIER_SYSTEM_CERNER_CDS_UNIQUE_ID = "http://cerner.com/fhir/cds-unique-id";
    public static final String IDENTIFIER_SYSTEM_CERNER_DIAGNOSIS_ID = "http://cerner.com/fhir/diagnosis-id";
    public static final String IDENTIFIER_SYSTEM_CERNER_PROCEDURE_ID = "http://cerner.com/fhir/procedure-id";
    public static final String IDENTIFIER_SYSTEM_CERNER_OBSERVATION_ID = "http://cerner.com/fhir/observation-id";
    public static final String IDENTIFIER_SYSTEM_CERNER_PATHWAY_ID = "http://cerner.com/fhir/pathway-id";
    //NOTE - if an identifier should be searchable then make sure to add to the fn at the bottom of this class

    //Bhrut specific
    public static final String IDENTIFIER_SYSTEM_BHRUT_PAS_ID = "https://www.bhrhospitals.nhs.uk/fhir/pas-id";
    //NOTE - if an identifier should be searchable then make sure to add to the fn at the bottom of this class



    /**
     * returns the list of identifiers that are included in searching for patients in the Data Assurance app etc.
     * FHIR Patient resources often end up with many identifiers that aren't interesting or relevant for searching
     */
    public static Set<String> getSearchablePatientIdentifiers() {
        Set<String> ret = new HashSet<>();

        //common
        ret.add(IDENTIFIER_SYSTEM_NHSNUMBER);

        //Cerner
        ret.add(IDENTIFIER_SYSTEM_BARTS_MRN_PATIENT_ID);
        ret.add(IDENTIFIER_SYSTEM_HOMERTON_MRN_PATIENT_ID);
        ret.add(IDENTIFIER_SYSTEM_NEWHAM_MRN_PATIENT_ID);
        ret.add(IDENTIFIER_SYSTEM_ROYAL_FREE_MRN_PATIENT_ID);
        ret.add(IDENTIFIER_SYSTEM_HOMERTON_CNN_PATIENT_ID);

        //Emis
        ret.add(IDENTIFIER_SYSTEM_EMIS_PATIENT_NUMBER);

        //Vision
        ret.add(IDENTIFIER_SYSTEM_VISION_PATIENT_NUMBER);

        //Bhrut
        ret.add(IDENTIFIER_SYSTEM_BHRUT_PAS_ID);

        //TPP

        //Adastra

        return ret;
    }
}
