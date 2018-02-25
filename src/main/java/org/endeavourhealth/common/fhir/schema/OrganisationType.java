package org.endeavourhealth.common.fhir.schema;

import org.apache.commons.lang3.StringUtils;
import org.endeavourhealth.common.fhir.FhirValueSetUri;

public enum OrganisationType {

    APPLICATION_SERVICE_PROVIDER("AR", "Application Service Provider", "RO92"),
    BOOKING_MANAGEMENT_SYSTEM("BM", "Booking Management System (BMS) Call Centre Establishment", null),
    CANCER_NETWORK("CN", "Cancer Network", "RO103"),
    CANCER_REGISTRY("CR", "Cancer Registry", "RO105"),
    CARE_HOME_HQ("CQ", "Care Home Headquarters", "RO104"),
    CARE_TRUST("CT", "Care Trust", "RO107"),
    CCG("CC", "Clinical Commissioning Group (CCG)", "RO98"),
    CLINCIAL_NETWORK("CL", "Clinical Network", "RO102"),
    CSU("CA", "Commissioning Support Unit (CSU)", "RO213"),
    COURT("JG", "Court", "RO235"),
    DENTAL_PRACTICE("DD", "Dental Practice", "RO110"),
    EDUCATION_ESTABLISHMENT("ED", "Education Establishment", "RO117"),
    EXECUTIVE_AGENCY("EA", "Executive Agency", "RO116"),
    EXECUTIVE_AGENCY_PROGRAMME("AP", "Executive Agency Programme", "RO91"),
    GOVERNMENT_DEPARTMENT("GD", "Government Department", "RO126"),
    GOVERNMENT_OFFICE_REGION("GO", "Government Office Region (GOR)", "RO128"),
    ABEYANCE_AND_DISPERSALE_GP_PRACTICE("AA", "Abeyance and Dispersal GP Practice", null),
    GP_PRACTICE("PR", "GP Practices in England and Wales", "RO76"),
    HIGH_LEVEL_HEALTH_GEOGRAPHY("HA", "High Level Health Geography", null),
    IMMIGRATION_REMOVAL_CENTRE("JD", "Immigration Removal Centre", "RO69"),
    ISHP("PH", "Independent Sector Healthcare Provider (ISHP)", "RO172"),
    LOCAL_AUTHORITY("EL", "Local Authority", "RO141"),
    LOCAL_HEALTH_BOARD_WALES("LB", "Local Health Board (Wales)", "RO144"),
    LSP("LO", "Local Service Provider (LSP)", "RO146"),
    MILITARY_HOSPITAL("MH", "Military Hospital", "RO150"),
    NATIONAL_APPLICATION_SERVICE_PROVIDER("NP", "National Application Service Provider", "RO159"),
    NATIONAL_GROUPINGS("RO", "National Groupings", null),
    NHS_SUPPORT_AGENCY("NS", "NHS Support Agency", "RO161"),
    NHS_TRUST("TR", "NHS Trust", "RO197"),
    NHS_TRUST_SITE("SI", "NHS Trust Site", "RO198"),
    NHS_TRUST_SERVICE("TS", "NHS Trust Service", null),
    NON_NHS_ORGANISATION("NN", "Non-NHS Organisation", "RO157"),
    NI_HEALTH_AND_SOCIAL_CARE_BOARD("NA", "Northern Ireland Health & Social Care Board", null),
    NI_HEALTH_AND_SOCIAL_CARE_TRUST("NB", "Northern Ireland Health & Social Care Trust", null),
    NI_LOCAL_COMMISSIONING_GROUP("NC", "Northern Ireland Local Commissioning Group", null),
    OPTICAL_HQ("OH", "Optical Headquarters", "RO166"),
    OSX("OA", "Other Statutory Authority (OSA)", "RO162"),
    PHARMACY("PY", "Pharmacy", "RO182"),
    PHARMACY_HQ("PX", "Pharmacy Headquarters", "RO181"),
    POLICE_CONSTABULARY("JE", "Police Constabulary", "RO233"),
    POLICE_CUSTODY_SUITE("JF", "Police Custody Suite", "RO234"),
    PCT("PT", "Primary Care Trust", "RO179"),
    PRIMARY_HEALTHCARE_DIRECTORATE("ID", "Primary Healthcare Directorate (Isle of Man)", "RO137"),
    PRISON_HEALTH_SERVICE("PN", "Prison Health Service", "RO175"),
    SCHOOL("EE", "School", "RO117"),
    SECURE_CHILDRENS_HOME("JC", "Secure Children's Home", "RO231"),
    SECTURE_TRAINING_CENTRE("JB", "Secure Training Centre (STC)", "RO230"),
    SEXUAL_ASSUALT_REFERRAL_CENTRE("JH", "Sexual Assault Referral Centre (SARC)", "RO236"),
    SPECIAL_HEALTH_AUTHORITY("SA", "Special Health Authority (SpHA)", "RO189"),
    WELSH_ASSEMBLY("WA", "Welsh Assembly", "RO200"),
    WELSH_HEALTH_COMMISSION("WH", "Welsh Health Commission", null),
    WELSH_LOCAL_HEALTH_BOARD("LH", "Welsh Local Health Board", "RO144"),
    YOUNG_OFFENDERS_INSTITUTE("JA", "Young Offenders Institute", "RO228");

    private String code = null;
    private String description = null;
    private String roleCode = null;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getRoleCode() { return roleCode; }

    public String getSystem() {
        return FhirValueSetUri.VALUE_SET_ORGANISATION_TYPE;
    }

    OrganisationType(String code, String description, String roleCode) {
        this.code = code;
        this.description = description;
        this.roleCode = roleCode;
    }

    public static OrganisationType fromCode(String v) {
        if (StringUtils.isBlank(v))
            return null;

        for (OrganisationType c: OrganisationType.values()) {
            if (c.code.equalsIgnoreCase(v)) {
                return c;
            }
        }

        throw new IllegalArgumentException("Unknown code [" + v + "]");
    }

    public static OrganisationType fromRoleCode(String roleCode) {
        if (StringUtils.isBlank(roleCode))
            return null;

        for (OrganisationType organisationType : OrganisationType.values())
            if (organisationType.roleCode != null)
                if (organisationType.roleCode.equalsIgnoreCase(roleCode))
                    return organisationType;

        throw new IllegalArgumentException("Unknown code [" + roleCode + "]");
    }

    public static OrganisationType fromDescription(String v) {
        if (StringUtils.isBlank(v))
            return null;

        for (OrganisationType c: OrganisationType.values())
            if (c.description.equalsIgnoreCase(v))
                return c;

        throw new IllegalArgumentException(v);
    }
}
