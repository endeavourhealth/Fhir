package org.endeavourhealth.common.fhir.schema;

import org.endeavourhealth.common.fhir.FhirValueSetUri;

public enum RegistrationType {

    EMERGENCY("E", "Emergency"),
    IMMEDIATELY_NECESSARY("IN", "Immediately Necessary"),
    REGULAR_GMS("R", "Regular/GMS"),
    TEMPORARY("T", "Temporary"),
    PRIVATE("P", "Private"),
    OTHER("O", "Other"),
    //DUMMY("D", "Dummy/Synthetic"), - superseded with test-patient extension on patient
    COMMUNITY("C", "Community"),
    WALK_IN("W", "Walk-In"),
    MINOR_SURGERY("MS", "Minor Surgery"),
    CHILD_HEALTH_SURVEILLANCE("CHS", "Child Health Services"),
    CONTRACEPTIVE_SERVICES("N", "Contraceptive Services"),
    YELLOW_FEVER("Y", "Yellow Fever"),
    MATERNITY_SERVICES("M", "Maternity Services"),
    PRE_REGISTRATION("PR", "Pre-Registration"),
    SEXUAL_HEALTH("SH", "Sexual Health"),
    VASECTOMY("V", "Vasectomy"),
    OUT_OF_HOURS("OH", "Out of Hours"),
    REHABILITATION("B", "Rehabilitation"),
    EXTERNALLY_REGISTERED("X", "Externally registered");

    private String code = null;
    private String description = null;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getSystem() {
        return FhirValueSetUri.VALUE_SET_REGISTRATION_TYPE;
    }

    RegistrationType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static RegistrationType fromCode(String v) {
        for (RegistrationType c: RegistrationType.values()) {
            if (c.getCode().equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown code [" + v + "]");
    }
}
