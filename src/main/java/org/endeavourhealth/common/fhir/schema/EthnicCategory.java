package org.endeavourhealth.common.fhir.schema;

import org.endeavourhealth.common.fhir.FhirValueSetUri;

public enum EthnicCategory {

    //note the below list is from https://www.datadictionary.nhs.uk/data_dictionary/attributes/e/end/ethnic_category_code_de.asp
    //and seems to miss out Arab and Gypsy/Irish Travellor, which more modern lists have
    WHITE_BRITISH("A", "British"),
    WHITE_IRISH("B", "Irish"),
    OTHER_WHITE("C", "Any other White background"),
    MIXED_CARIBBEAN("D", "White and Black Caribbean"),
    MIXED_AFRICAN("E", "White and Black African"),
    MIXED_ASIAN("F", "White and Asian"),
    OTHER_MIXED("G", "Any other mixed background"),
    ASIAN_INDIAN("H", "Indian"),
    ASIAN_PAKISTANI("J", "Pakistani"),
    ASIAN_BANGLADESHI("K", "Bangladeshi"),
    CHINESE("R", "Chinese"),
    OTHER_ASIAN("L", "Any other Asian background"),
    BLACK_AFRICAN("N", "African"),
    BLACK_CARIBBEAN("M", "Caribbean"),
    OTHER_BLACK("P", "Any other Black background"),
    OTHER("S", "Any other ethnic group"),
    NOT_STATED("Z", "Not stated");

    private String code = null;
    private String description = null;

    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public String getSystem() {
        return FhirValueSetUri.VALUE_SET_ETHNIC_CATEGORY;
    }

    EthnicCategory(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static EthnicCategory fromCode(String v) {
        for (EthnicCategory c: EthnicCategory.values()) {
            if (c.code.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown code [" + v + "]");
    }
}
