package org.endeavourhealth.common.fhir.schema;

import org.endeavourhealth.common.fhir.FhirValueSetUri;

public enum ReferralRequestSendMode {

    //defined at http://endeavourhealth.org/fhir/ValueSet/primarycare-referral-request-mode
    ACUTE("T", "Telephone"),
    REPEAT("W", "Written"),
    REPEAT_DISPENSING("V", "Verbal"),
    ERS("E", "NHS e-Referral"),
    MANAGED("M", "Managed");

    private String code = null;
    private String description = null;

    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public String getSystem() {
        return FhirValueSetUri.VALUE_SET_REFERRAL_REQUEST_SEND_MODE;
    }


    ReferralRequestSendMode(String code, String description) {
        this.code = code;
        this.description = description;
    }


    public static ReferralRequestSendMode fromCode(String v) {
        for (ReferralRequestSendMode c: ReferralRequestSendMode.values()) {
            if (c.getCode().equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown code [" + v + "]");
    }

    public static ReferralRequestSendMode fromDescription(String v) {
        for (ReferralRequestSendMode c: ReferralRequestSendMode.values()) {
            if (c.getDescription().equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown description [" + v + "]");
    }
}
