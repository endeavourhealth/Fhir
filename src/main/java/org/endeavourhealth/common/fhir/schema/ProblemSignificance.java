package org.endeavourhealth.common.fhir.schema;

import org.endeavourhealth.common.fhir.FhirValueSetUri;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;

public enum ProblemSignificance {

    SIGNIFICANT("386134007", "Significant"),
    NOT_SIGNIFICANT("371928007", "Not significant"),
    UNSPECIIED("394847000", "Unspecified significance");

    private String code = null;
    private String description = null;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getSystem() {
        return FhirValueSetUri.VALUE_SET_PROBLEM_SIGNIFICANCE;
    }

    ProblemSignificance(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ProblemSignificance fromCode(String v) {
        for (ProblemSignificance c: ProblemSignificance.values()) {
            if (c.code.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown code [" + v + "]");
    }

    public static ProblemSignificance fromCodeableConcept(CodeableConcept codeableConcept) {
        if (codeableConcept.hasCoding()) {
            for (Coding coding: codeableConcept.getCoding()) {
                if (coding.getSystem().equals(FhirValueSetUri.VALUE_SET_PROBLEM_SIGNIFICANCE)) {
                    return fromCode(coding.getCode());
                }
            }
        }
        return null;
    }
}
