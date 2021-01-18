package org.endeavourhealth.common.fhir.schema;

import org.apache.commons.lang3.StringUtils;
import org.endeavourhealth.common.fhir.FhirValueSetUri;

public enum OrganisationType {

    //roles can be looked up on the ODS API using
    // https://directory.spineservices.nhs.uk/ORD/2-0-0/roles/<rolecode>
    // e.g.
    // https://directory.spineservices.nhs.uk/ORD/2-0-0/roles/RO261

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
    IMMIGRATION_REMOVAL_CENTRE_LEGACY("JD", "Immigration Removal Centre", "RO69"),
    ISHP("PH", "Independent Sector Healthcare Provider (ISHP)", "RO172"),
    ISHPS("PS", "Independent Sector Healthcare Provider Site (ISHPS)", "RO176"),
    LOCAL_AUTHORITY("EL", "Local Authority", "RO141"),
    // LOCAL_HEALTH_BOARD_WALES("LB", "Local Health Board (Wales)", "RO144"), duplicate
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
    YOUNG_OFFENDERS_INSTITUTE("JA", "Young Offenders Institute", "RO228"),
    COMMUNITY("CO", "Community", null),
    COMMUNITY_MENTAL_HEALTH_TEAM("CM", "Community Mental Health Team", null),
    HOSPICE("HP", "Hospice", "RO7"),
    HOSPITAL("HO", "Hospital", null),
    HOSPITAL_DEPARTMENT("HD", "Hospital Department", null),
    CLINIC("CI", "Clinic", null),
    PRIVATE_CLINIC("PV", "Private Clinic", null),
    STRATEGIC_HEALTH_AUTHORITY("SH", "Strategic Health Authority", null),
    HEALTH_AUTHORITY("HU", "Health Authority (HA)", "RO132"),
    PRIMARY_CARE_ORGANISATION("PC", "Primary Care Organisation", null),
    GP_BRANCH_SURGERY("BR", "Branch Surgery", null),
    OUT_OF_HOURS("OO", "Out of Hours", null),
    NURSING_HOME("NH", "Nursing Home", null),
    ACCIDENT_AND_EMERGENCY("AE", "Accident & Emergency", null),
    PRIMARY_CARE_NETWORK("PCN", "Primary Care Network", "RO272"),
    NHS_ENGLAND("RO209", "NHS England", "RO209"),
    SUSTAINABILITY_TRANSFORMATION_PARTNERSHIP("RO262", "Sustainability Transformation Partnership", "RO262"),
    STRATEGIC_PARTNERSHIP("RO261", "Strategic Partnership", "RO261"),
    PRESCRIBING_COST_CENTRE("RO177", "Prescribing Cost Centre", "RO177"),
    FOUNDATION_TRUST("RO57", "Foundation Trust", "RO57"),
    COMMISSIONING_HUB("RO218", "Commissioning Hub", "RO218"),
    OTHER_PRESCRIBING_COST_CENTRE("RO72", "Other Prescribing Cost Centre", "RO72"),

    PRIMARY_CARE_TRUST_SITE("RO180", "Primary Care Trust Site", "RO180"),
    DMU_SITE("RO114", "DMU Site", "RO114"),
    SPECIAL_HEALTH_AUTHORITY_SITE("RO191", "Special Health Authority Site", "RO191"),
    LOCAL_AUTHORITY_SITE_LEGACY("RO122", "Local Authority Site - Legacy", "RO122"),
    PPA_EPACT_SYSTEM("RO31", "PPA Epact System", "RO31"),
    TREATMENT_CENTRE("RO29", "Treatment Centre", "RO29"),
    LEVEL_04_PCT("RO22", "Level 04 Pct", "RO22"),
    NHS_TRUST_DERIVED("RO24", "Nhs Trust Derived", "RO24"),
    LOCAL_AUTHORITY_DEPARTMENT("RO123", "Local Authority Department", "RO123"),
    ISLE_OF_MAN_GOVERNMENT_DEPARTMENT("RO138", "Isle Of Man Government Department", "RO138"),
    COMMON_SERVICE_AGENCY("RO106", "Common Service Agency (CSA)", "RO106"),
    ISLE_OF_MAN_GOVERNMENT_DIRECTORATE_SITE("RO140", "Isle Of Man Government Directorate Site", "RO140"),
    PCT_DERIVED("RO25", "PCT Derived", "RO25"),
    EXECUTIVE_AGENCY_SITE("RO90", "Executive Agency Site", "RO90"),
    APPLICATION_SERVICE_PROVIDER_LEGACY("RO93", "Application Service Provider - Legacy", "RO93"),
    WELSH_LOCAL_HEALTH_BOARD_SITE("RO149", "Welsh Local Health Board Site", "RO149"),
    LEVEL_03_PCT("RO21", "Level 03 PCT", "RO21"),
    SPECIALISED_COMMISSIONING_GROUP("RO67", "Specialised Commissioning Group", "RO67"),
    RESEARCH_AND_DEVELOPMENT("RO30", "Research and Development", "RO30"),
    PRIVATELY_OWNED_ENTITY("RO71", "Privately Owned Entity", "RO71"),
    METROPOLITAN_DISTRICT_COUNCIL("RO39", "Metropolitan District Council", "RO39"),
    HEALTH_OBSERVATORY("RO134", "Health Observatory", "RO134"),
    NON_STATUTORY_NHS_ORGANISATION("RO158", "Non Statutory NHS Organisation", "RO158"),
    COUNCIL("RO38", "Council", "RO38"),
    EXECUTIVE_AGENCY_PROGRAMME_DEPARTMENT("RO89", "Executive Agency Programme - Department", "RO89"),
    OTHER_STATUTORY_AUTHORITY_SITE("RO168", "Other Statutory Authority Site", "RO168"),
    HOSTS_DATA_MANAGMENT_INTEGRATION_CENTRE("RO215", "Hosts Data Managment Integration Centre (DMIC)", "RO215"),
    NHS_ENGLAND_REGION_LOCAL_OFFICE_SITE("RO212", "Nhs England (region, Local Office) Site", "RO212"),
    SECURE_TRAINING_CENTRE("RO220", "Secure Training Centre", "RO220"),
    COMMISSIONING_SUPPORT_UNIT_SITE("RO214", "Commissioning Support Unit Site", "RO214"),
    DATA_SERVICES_FOR_COMMISSIONERS_REGIONAL_OFFICE("RO216", "Data Services For Commissioners Regional Office (DSCRO)", "RO216"),
    STRATEGIC_HEALTH_AUTHORITY_SITE("RO136", "Strategic Health Authority Site", "RO136"),
    DSCRO_SITE("RO217", "Dscro Site", "RO217"),
    YOUNG_OFFENDER_INSTITUTION_PRESCRIBING_COST_CENTRE("RO260", "Young Offender Institution Prescribing Cost Centre", "RO260"),
    IMMIGRATION_REMOVAL_CENTRE_PRESCRIBING_COST_CENTRE("RO251", "Immigration Removal Centre Prescribing Cost Centre", "RO251"),
    SECURE_CHILDRENS_HOME_PRESCRIBING_COST_CENTRE("RO256", "Secure Children's Home Prescribing Cost Centre", "RO256"),
    SECURE_TRAINING_CENTRE_PRESCRIBING_COST_CENTRE("RO257", "Secure Training Centre Prescribing Cost Centre", "RO257"),
    COMBINED_AUTHORITY("RO273", "Combined Authority", "RO273"),
    RESIDENTIAL_INSTITUTION("RO83", "Residential Institution", "RO83"),
    SPECIALISED_COMMISSIONING_HUB("RO211", "Specialised Commissioning Hub", "RO211"),
    APPLIANCE_CONTRACTOR("RO94", "Appliance Contractor", "RO94"),
    IMMIGRATION_REMOVAL_CENTRE("RO232", "Immigration Removal Centre", "RO232"),
    LSP_SITE("RO147", "LSP Site", "RO147"),
    LOCAL_HEALTH_AND_CARE_RECORD_EXEMPLAR("RO274", "Local Health and Care Record Exemplar", "RO274"),
    HOSPICE_PRESCRIBING_COST_CENTRE("RO249", "Hospice Prescribing Cost Centre", "RO249"),
    LOCAL_HEALTH_BOARD_SITE("RO148", "Local Health Board Site", "RO148"),
    CITY_COUNCIL("RO35", "City Council", "RO35"),
    NON_RESIDENTIAL_INSTITUTION("RO229", "Non Residential Institution", "RO229"),
    REGISTERED_UNDER_PART_2_CARE_STDS_ACT_2000("RO15", "Registered Under Part 2 Care Standards Act 2000", "RO15"),
    WALK_IN_CENTRE("RO87", "Walk In Centre", "RO87"),
    BOROUGH_COUNCIL("RO34", "Borough Council", "RO34"),
    DISTRICT_COUNCIL("RO36", "District Council", "RO36"),
    LA_INNER_LONDON("RO12", "LA - Inner London", "RO12"),
    OUT_OF_HOURS_COST_CENTRE("RO80", "Out of Hours Cost Centre", "RO80"),
    PUBLIC_HEALTH_SERVICE_PRESCRIBING_COST_CENTRE("RO255", "Public Health Service Prescribing Cost Centre", "RO255"),
    PRISON_PRESCRIBING_COST_CENTRE("RO82", "Prison Prescribing Cost Centre", "RO82"),
    MEDICINE_SUPPLIER("RO268", "Medicine Supplier", "RO268"),
    EXTENDED_ACCESS_PROVIDER("RO266", "Extended Access Provider", "RO266"),
    CARE_HOME_NURSING_HOME_PRESCRIBING_COST_CENTRE("RO246", "Care Home/Nursing Home Prescribing Cost Centre", "RO246"),
    LOCAL_AUTHORITY_SITE("RO222", "Local Authority Site", "RO222"),
    CARE_TRUST_SITE("RO108", "Care Trust Site", "RO108"),
    REGIONAL_OFFICE("RO185", "Regional Office (RO)", "RO185"),
    EXTENDED_ACCESS_HUB("RO267", "Extended Access Hub", "RO267"),
    LOCAL_AUTHORITY_LEGACY("RO119", "Local Authority - Legacy", "RO119"),
    LA_METROPOLITAN_DISTRICT("RO11", "LA - Metropolitan District", "RO11"),
    METROPOLITAN_DISTRICT("RO37", "Metropolitan District", "RO37"),
    UNITARY_AUTHORITY("RO40", "Unitary Authority", "RO40"),
    LONDON_BOROUGH("RO223", "London Borough", "RO223"),
    COUNTY_COUNCIL("RO33", "County Council", "RO33"),
    GOVERNMENT_DEPARTMENT_SITE("RO131", "Government Department Site", "RO131"),
    SEXUAL_ASSAULT_REFERRAL_CENTRE_PRESCRIBING_COST_CENTRE("RO258", "Sexual Assault Referral Centre Prescribing Cost Centre", "RO258"),
    COMMUNITY_HEALTH_SERVICE_PRESCRIBING_COST_CENTRE("RO247", "Community Health Service Prescribing Cost Centre", "RO247"),
    OPTOMETRY_SERVICE_PRESCRIBING_COST_CENTRE("RO252", "Optometry Service Prescribing Cost Centre", "RO252"),
    DOMICILIARY_CARE("RO270", "Domiciliary Care", "RO270"),
    CLINICAL_COMMISSIONING_GROUP_SITE("RO99", "Clinical Commissioning Group Site", "RO99"),
    LOCAL_HEALTH_BOARD("RO142", "Local Health Board", "RO142"),
    DISTRICT_HEALTH_AUTHORITY("RO109", "District Health Authority (DHA)", "RO109"),
    DIRECTLY_MANAGED_UNIT("RO111", "Directly Managed Unit (DMU)", "RO111"),
    PRIMARY_CARE_GROUP("RO171", "Primary Care Group", "RO171"),
    HOSPITAL_SERVICE_PRESCRIBING_COST_CENTRE("RO250", "Hospital Service Prescribing Cost Centre", "RO250"),
    PATHOLOGY_LAB("RO173", "Pathology Lab", "RO173"),
    BRANCH_SURGERY("RO96", "Branch Surgery", "RO96"),
    SOCIAL_CARE_SITE("RO101", "Social Care Site", "RO101"),
    CARE_HOME("RO269", "Care Home", "RO269"),
    URGENT_AND_EMERGENCY_CARE_PRESCRIBING_COST_CENTRE("RO259", "Urgent & Emergency Care Prescribing Cost Centre", "RO259"),
    NHS_ENGLAND_REGION_LOCAL_OFFICE("RO210", "NHS England (region, Local Office)", "RO210"),
    OPTICAL_SITE("RO167", "Optical Site", "RO167"),
    PRIVATE_DENTAL_PRACTICE("RO65", "Private Dental Practice", "RO65"),
    COVID_VACCINATION_CENTRE("RO279", "COVID Vaccination Centre", "RO279")
    ;

    private String code = null;
    private String description = null;
    private String roleCode = null;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getRoleCode() {
        return roleCode;
    }

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

        for (OrganisationType c : OrganisationType.values()) {
            if (c.code.equalsIgnoreCase(v)) {
                return c;
            }
        }

        throw new IllegalArgumentException("Unknown organisation type code [" + v + "]");
    }

    public static OrganisationType fromRoleCode(String roleCode) {
        if (StringUtils.isBlank(roleCode))
            return null;

        for (OrganisationType organisationType : OrganisationType.values())
            if (organisationType.roleCode != null)
                if (organisationType.roleCode.equalsIgnoreCase(roleCode))
                    return organisationType;

        throw new IllegalArgumentException("Unknown organisation role code [" + roleCode + "]");
    }

    public static OrganisationType fromDescription(String v) {
        if (StringUtils.isBlank(v))
            return null;

        for (OrganisationType c : OrganisationType.values())
            if (c.description.equalsIgnoreCase(v))
                return c;

        throw new IllegalArgumentException(v);
    }
}
