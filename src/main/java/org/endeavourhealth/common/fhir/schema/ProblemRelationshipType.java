package org.endeavourhealth.common.fhir.schema;

import org.endeavourhealth.common.fhir.FhirValueSetUri;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;

public enum ProblemRelationshipType {

    COMBINED("combined", "Combined"), //This problem is combined/merged with the target problem to form a single logical problem
    GROUPED("grouped", "Grouped"), //This problem is part of a group that includes the target problem as a member of the group
    REPLACES("replaces", "Replaces"), //This problem replaces a previous problem (i.e. a revised diagnosis). The target problem is now obsolete
    EVOLVED_FROM("evolved-from", "Evolved From"); //This problem has evolved from the target problem

    private String code = null;
    private String description = null;

    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public String getSystem() {
        return FhirValueSetUri.VALUE_SET_PROBLEM_RELATIONSHIP_TYPE;
    }


    ProblemRelationshipType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ProblemRelationshipType fromCode(String v) {
        for (ProblemRelationshipType c: ProblemRelationshipType.values()) {
            if (c.code.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown code [" + v + "]");
    }

    public static ProblemRelationshipType fromCodeableConcept(CodeableConcept codeableConcept) {
        if (codeableConcept.hasCoding()) {
            for (Coding coding: codeableConcept.getCoding()) {
                if (coding.getSystem().equals(FhirValueSetUri.VALUE_SET_PROBLEM_RELATIONSHIP_TYPE)) {
                    return fromCode(coding.getCode());
                }
            }
        }
        return null;
    }
}
