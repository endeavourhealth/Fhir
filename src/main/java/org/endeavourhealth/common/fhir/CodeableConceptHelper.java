package org.endeavourhealth.common.fhir;

import org.apache.commons.lang3.StringUtils;
import org.endeavourhealth.common.fhir.schema.*;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DiagnosticOrder;

public class CodeableConceptHelper {

    public static CodeableConcept createCodeableConcept(String system, String term, String code) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(system, term, code));
    }

    public static CodeableConcept createCodeableConcept(OrganisationType organisationType) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(organisationType));
    }

    public static CodeableConcept createCodeableConcept(ContactRelationship contactRelationship) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(contactRelationship));
    }

    public static CodeableConcept createCodeableConcept(DiagnosticOrder.DiagnosticOrderPriority priority) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(priority));
    }

    public static CodeableConcept createCodeableConcept(ProblemSignificance significance) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(significance));
    }

    public static CodeableConcept createCodeableConcept(FamilyMember familyMember) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(familyMember));
    }

    public static CodeableConcept createCodeableConcept(EncounterParticipantType participantType) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(participantType));
    }

    public static CodeableConcept createCodeableConcept(NhsNumberVerificationStatus nhsNumberVerificationStatus) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(nhsNumberVerificationStatus));
    }

    public static CodeableConcept createCodeableConcept(ReferralRequestSendMode referralRequestSendMode) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(referralRequestSendMode));
    }

    public static CodeableConcept createCodeableConcept(EthnicCategory ethnicCategory) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(ethnicCategory));
    }

    public static CodeableConcept createCodeableConcept(MaritalStatus maritalStatus) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(maritalStatus));
    }

    public static CodeableConcept createCodeableConcept(ReferralPriority referralPriority) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(referralPriority));
    }

    public static CodeableConcept createCodeableConcept(ReferralType referralType) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(referralType));
    }


    public static CodeableConcept createCodeableConcept(String text) {
        return new CodeableConcept().setText(text);
    }

    public static Long findSnomedConceptId(CodeableConcept code) {
        for (Coding coding: code.getCoding()) {
            if (coding.getSystem().equals(FhirUri.CODE_SYSTEM_SNOMED_CT)
                    || coding.getSystem().equals(FhirUri.CODE_SYSTEM_EMISSNOMED)) {
                return Long.parseLong(coding.getCode());
            }
        }

        return null;
    }

    public static String findSnomedConceptText(CodeableConcept code) {
        for (Coding coding: code.getCoding()) {
            if (coding.getSystem().equals(FhirUri.CODE_SYSTEM_SNOMED_CT)
                    || coding.getSystem().equals(FhirUri.CODE_SYSTEM_EMISSNOMED)) {
                return coding.getDisplay();
            }
        }

        return null;
    }

    public static String findOriginalCode(CodeableConcept code) {
        for (Coding coding: code.getCoding()) {
            if (coding.getSystem().equals(FhirUri.CODE_SYSTEM_READ2)
                    || coding.getSystem().equals(FhirUri.CODE_SYSTEM_EMIS_CODE)) {
                return coding.getCode();
            }
        }

        return null;
    }

    public static Coding getFirstCoding(CodeableConcept code) {
        if (code != null)
            if (code.getCoding() != null)
                if (code.getCoding().size() > 0)
                    return code.getCoding().get(0);

        return null;
    }

    public static String getFirstDisplayTerm(CodeableConcept code) {
        if (code != null)
            if (code.getCoding() != null)
                if (code.getCoding().size() > 0)
                    if (code.getCoding().get(0) != null)
                        if (StringUtils.isNotBlank(code.getCoding().get(0).getDisplay()))
                            return code.getCoding().get(0).getDisplay();

        return null;
    }

    public static String findCodingCode(CodeableConcept codeableConcept, String systemUri) {
        for (Coding coding: codeableConcept.getCoding()) {
            if (coding.getSystem().equals(systemUri)) {
                return coding.getCode();
            }
        }

        return null;
    }
}
