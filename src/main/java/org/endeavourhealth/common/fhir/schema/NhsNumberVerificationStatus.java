package org.endeavourhealth.common.fhir.schema;

import org.endeavourhealth.common.fhir.FhirValueSetUri;

public enum NhsNumberVerificationStatus {

    //note these options are official (see https://www.datadictionary.nhs.uk/version2/data_dictionary/data_field_notes/n/nhs_number_status_indicator_de.asp)
    PRESENT_AND_VERIFIED("01", "Number present and verified"),
    PRESENT_BUT_NOT_TRACED("02", "Number present but not traced"),
    TRACE_REQUIRED("03", "Trace required"),
    TRACE_ATTEMPTED_NO_MATCH("04", "Trace attempted - No match or multiple match found"),
    TRACE_NEEDS_TO_BE_RESOLVED("05", "Trace needs to be resolved - (NHS Number or PATIENT detail conflict)"),
    TRACE_IN_PROGRESS("06", "Trace in progress"),
    NUMBER_NOT_PRESENT_NO_TRACE_REQUIRED("07", "Number not present and trace not required"),
    TRACE_POSTPONED("08", "Trace postponed (baby under six weeks old)");


    private String code = null;
    private String description = null;

    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public String getSystem() {
        return FhirValueSetUri.VALUE_SET_NHS_NUMBER_VERIFICATION_STATUS;
    }

    NhsNumberVerificationStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static NhsNumberVerificationStatus fromCode(String v) {
        for (NhsNumberVerificationStatus c: NhsNumberVerificationStatus.values()) {
            if (c.getCode().equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown code [" + v + "]");
    }
}
