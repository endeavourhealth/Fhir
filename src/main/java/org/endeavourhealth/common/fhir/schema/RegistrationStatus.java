package org.endeavourhealth.common.fhir.schema;

import org.endeavourhealth.common.fhir.FhirValueSetUri;

public enum RegistrationStatus {

    PRE_REGISTERED_PRESENTED("PR1", "Patient has presented", false),
    PRE_REGISTERED_MEDICAL_CARD_RECEIVED("PR2", "Medical card received", false),
    PRE_REGISTERED_FP1_SUBMITTED("PR3", "Application Form FP1 submitted", false),
    REGISTERED("R1", "Registered", true),
    REGISTERED_RECORD_SENT_FROM_FHSA("R2", "Medical record sent by FHSA", true),
    REGISTERED_RECORD_RECEIVED_FROM_FHSA("R3", "Record Received", true),
    REGISTERED_LEFT_PRACTICE_STILL_REGISTERED("R4", "Left Practice. Still Registered", true),
    REGISTERED_CORRECTLY("R5", "Correctly registered", true),
    REGISTERED_TEMPORARY_SHORT_STAY("R6", "Short stay", true),
    REGISTERED_TEMPORARY_LONG_STAY("R7", "Long stay", true),
    DEDUCTED_DEATH("D1", "Death", false),
    DEDUCTED_DEATH_NOTIFICATION("D2", "Dead (Practice notification)", false),
    DEDUCTED_RECORD_REQUESTED_BY_FHSA("D3", "Record Requested by FHSA", false),
    DEDUCTED_REMOVAL_TO_NEW_HA("D4", "Removal to New HA/HB", false),
    DEDUCTED_INTERNAL_TRANSFER("D5", "Internal transfer", false),
    DEDUCTED_MENTAL_HOSPITAL("D6", "Mental hospital", false),
    DEDUCTED_EMBARKATION("D7", "Embarkation", false),
    DEDUCTED_NEW_HA_SAME_GP("D8", "New HA/HB - same GP", false),
    DEDUCTED_ADOPTED_CHILD("D9", "Adopted child", false),
    REGISTERED_SERVICES("R8", "Services", true),
    DEDUCTED_AT_GP_REQUEST("D10", "Deduction at GP's request", false),
    DEDUCTED_REGISTRATION_CANCELLED("D11", "Registration cancelled", false),
    REGISTERED_SERVICES_DEPENDENT("R9", "Service dependant", true),
    DEDUCTED_AT_PATIENT_REQUEST("D12", "Deduction at patient's request", false),
    DEDUCTED_OTHER_REASON("D13", "Other reason", false),
    DEDUCTED_MAIL_RETURNED_UNDELIVERED("D14", "Returned undelivered", false),
    DEDUCTED_INTERNAL_TRANSFER_ADDRESS_CHANGE("D15", "Internal transfer - address change", false),
    DEDUCTED_INTERNAL_TRANSFER_WITHIN_PARTNERSHIP("D16", "Internal transfer within partnership", false),
    DEDUCTED_MAIL_STATES_GONE_AWAY("D17", "Correspondence states 'gone away'", false),
    DEDUCTED_OUTSIDE_OF_AREA("D18", "Practice advise outside of area", false),
    DEDUCTED_NO_LONGER_RESIDENT("D19", "Practice advise patient no longer resident", false),
    DEDUCTED_VIA_SCREENING_SYSTEM("D20", "Practice advise removal via screening system", false),
    DEDUCTED_VIA_VACCINATION_DATA("D21", "Practice advise removal via vaccination data", false),
    REGISTERED_REMOVAL_FROM_RESIDENTIAL_INSITUTE("R10", "Removal from Residential Institute", true),
    DEDUCTED_RECORDS_SENT_BACK_TO_FHSA("D22", "Records sent back to FHSA", false),
    DEDUCTED_RECORDS_RECEIVED_BY_FHSA("D23", "Records received by FHSA", false),
    DEDUCTED_REGISTRATION_EXPIRED("D24", "Registration expired", false);


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
