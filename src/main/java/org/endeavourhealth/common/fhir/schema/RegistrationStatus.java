package org.endeavourhealth.common.fhir.schema;

import org.endeavourhealth.common.fhir.FhirValueSetUri;

public enum RegistrationStatus {

    REGISTERED_PRESENTED("1", "Patient has presented", false),
    REGISTERED_MEDICAL_CARD_RECEIVED("2", "Medical card received", false),
    REGISTERED_FP1_SUBMITTED("3", "Application Form FP1 submitted", false),
    REGISTERED("4", "Registered", true),
    REGISTERED_RECORD_SENT_FROM_FHSA("5", "Medical record sent by FHSA", true),
    REGISTERED_RECORD_RECEIVED_FROM_FHSA("6", "Record Received", true),
    REGISTERED_LEFT_PRACTICE_STILL_REGISTERED("7", "Left Practice. Still Registered", true),
    REGISTERED_CORRECTLY("8", "Correctly registered", true),
    REGISTERED_TEMPORARY_SHORT_STAY("9", "Short stay", true),
    REGISTERED_TEMPORARY_LONG_STAY("10", "Long stay", true),
    DEDUCTION_DEATH("11", "Death", false),
    DEDUCTION_DEATH_NOTIFICATION("12", "Dead (Practice notification)", false),
    DEDUCTION_RECORD_REQUESTED_BY_FHSA("13", "Record Requested by FHSA", false), //TODO is this active?
    REGISTERED_REMOVAL_TO_NEW_HA("14", "Removal to New HA/HB", true),
    REGISTERED_INTERNAL_TRANSFER("15", "Internal transfer", true),
    DEDUCTION_MENTAL_HOSPITAL("16", "Mental hospital", false),
    DEDUCTION_EMBARKATION("17", "Embarkation", false),
    REGISTERED_NEW_HA_SAME_GP("18", "New HA/HB - same GP", true),
    DEDUCTION_ADOPTED_CHILD("19", "Adopted child", false), //TODO - is this active?
    DEDUCTION_SERVICES("20", "Services", false), //TODO - is this active?
    DEDUCTION_AT_GP_REQUEST("21", "Deduction at GP's request", false),
    DEDUCTION_REGISTRATION_CANCELLED("22", "Registration cancelled", false),
    REGISTERED_SERVICES_DEPENDENT("23", "Service dependant", true),
    DEDUCTION_AT_PATIENT_REQUEST("24", "Deduction at patient's request", false),
    DEDUCTION_OTHER_REASON("25", "Other reason", false),
    DEDUCTION_MAIL_RETURNED_UNDELIVERED("26", "Returned undelivered", false),
    REGISTERED_INTERNAL_TRANSFER_ADDRESS_CHANGE("27", "Internal transfer - address change", true),
    REGISTERED_INTERNAL_TRANSFER_WITHIN_PARTNERSHIP("28", "Internal transfer within partnership", true),
    DEDUCTION_MAIL_STATES_GONE_AWAY("29", "Correspondence states 'gone away'", false),
    DEDUCTION_OUTSIDE_OF_AREA("30", "Practice advise outside of area", false),
    DEDUCTION_NO_LONGER_RESIDENT("31", "Practice advise patient no longer resident", false),
    DEDUCTION_VIA_SCREENING_SYSTEM("32", "Practice advise removal via screening system", false),
    DEDUCTION_VIA_VACCINATION_DATA("33", "Practice advise removal via vaccination data", false),
    REGISTERED_REMOVAL_FROM_RESIDENTIAL_INSITUTE("34", "Removal from Residential Institute", false), //TODO - is this active
    DEDUCTION_RECORDS_SENT_BACK_TO_FHSA("35", "Records sent back to FHSA", false),
    DEDUCTION_RECORDS_RECEIVED_BY_FHSA("36", "Records received by FHSA", false),
    DEDUCTION_REGISTRATION_EXPIRED("37", "Registration expired", false);


    /*
    NOTE: the above list is based on Emis Web, which also has the statuses (that have never been used)
    38	All records removed
    39	Untraced-outwith HB
    40	Multiple Transfer
    41	Intra-consortium transfer
    42	District birth
    43	Transfer in
    44	Transfer out
    45	Movement in
    46	Movement out
    47	Died
    48	Still birth
    49	Living out, treated in
    50	Living in, treated out

     */

    private String code;
    private String description;
    private boolean countsAsActive;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCountsAsActive() {
        return countsAsActive;
    }

    public String getSystem() {
        return FhirValueSetUri.VALUE_SET_REGISTRATION_STATUS;
    }

    RegistrationStatus(String code, String description, boolean countsAsActive) {
        this.code = code;
        this.description = description;
        this.countsAsActive = countsAsActive;
    }

    public static RegistrationStatus fromCode(String v) {
        for (RegistrationStatus c: RegistrationStatus.values()) {
            if (c.getCode().equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown code [" + v + "]");
    }
}
