package org.endeavourhealth.common.fhir;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.endeavourhealth.common.fhir.schema.*;
import org.hl7.fhir.instance.model.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CodeableConceptHelper {

    public static CodeableConcept createCodeableConcept(String system, String term, String code) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(system, term, code));
    }

    public static CodeableConcept createCodeableConcept(OrganisationType organisationType) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(organisationType));
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

    public static CodeableConcept createCodeableConcept(LocationPhysicalType locationPhysicalTypeType) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(locationPhysicalTypeType));
    }

    public static CodeableConcept createCodeableConcept(RegistrationStatus registrationStatus) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(registrationStatus));
    }

    public static CodeableConcept createCodeableConcept(Religion religion) {
        return new CodeableConcept().addCoding(CodingHelper.createCoding(religion));
    }

    public static CodeableConcept createCodeableConcept(String text) {
        return new CodeableConcept().setText(text);
    }

    public static Long findSnomedConceptId(CodeableConcept code) {
        for (Coding coding: code.getCoding()) {
            if (coding.getSystem().equals(FhirCodeUri.CODE_SYSTEM_SNOMED_CT)
                    || coding.getSystem().equals(FhirCodeUri.CODE_SYSTEM_EMISSNOMED)) {

                //need to handle there not being a coding
                String codeValue = coding.getCode();
                if (!Strings.isNullOrEmpty(codeValue)) {
                    return Long.parseLong(codeValue);
                }
                //return Long.parseLong(coding.getCode());
            }
        }

        return null;
    }

    public static String findSnomedConceptText(CodeableConcept code) {
        for (Coding coding: code.getCoding()) {
            if (coding.getSystem().equals(FhirCodeUri.CODE_SYSTEM_SNOMED_CT)
                    || coding.getSystem().equals(FhirCodeUri.CODE_SYSTEM_EMISSNOMED)) {
                return coding.getDisplay();
            }
        }

        return null;
    }

    public static String findOriginalCode(CodeableConcept code) {
        Coding coding = findOriginalCoding(code);
        if (coding != null) {
            return coding.getCode();
        } else {
            return null;
        }
    }

    public static Coding findOriginalCoding(CodeableConcept code) {

        for (Coding coding: code.getCoding()) {

            //essentially we count it as the "original" code if it's not Snomed
            String system = coding.getSystem();
            if (!system.equalsIgnoreCase(FhirCodeUri.CODE_SYSTEM_SNOMED_CT)
                    && !system.equalsIgnoreCase(FhirCodeUri.CODE_SYSTEM_EMISSNOMED) //this value is no longer used, but
                    && !system.equalsIgnoreCase(FhirCodeUri.CODE_SYSTEM_SNOMED_DESCRIPTION_ID)) { //Emis send us Read2, Snomed Concept and Snomed Description, so ignore the Desceiptions

                return coding;
            }
        }

        //if nothing above matched, then look for a snomed code, since the third party may actually have sent us native Snomed
        for (Coding coding: code.getCoding()) {
            String system = coding.getSystem();
            if (system.equalsIgnoreCase(FhirCodeUri.CODE_SYSTEM_SNOMED_CT)
                    || system.equalsIgnoreCase(FhirCodeUri.CODE_SYSTEM_EMISSNOMED)) {
                return coding;
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
        Coding coding = findCoding(codeableConcept, systemUri);
        if (coding != null) {
            return coding.getCode();
        }

        return null;
    }

    public static String findCodingDisplay(CodeableConcept codeableConcept, String systemUri) {
        Coding coding = findCoding(codeableConcept, systemUri);
        if (coding != null) {
            return coding.getDisplay();
        }

        return null;
    }

    public static Coding findCoding(CodeableConcept codeableConcept, String systemUri) {
        for (Coding coding: codeableConcept.getCoding()) {
            if (coding.getSystem() != null
                && coding.getSystem().equals(systemUri)) {
                return coding;
            }
        }

        return null;
    }

    /**
     * finds the "main" clinical CodeableConcept for a resource - handling all the different
     * resource types used in DDS
     */
    public static CodeableConcept findMainCodeableConcept(Resource resource) throws IllegalArgumentException {
        if (resource instanceof Observation) {
            Observation o = (Observation)resource;
            if (o.hasCode()) {
                return o.getCode();
            }

        } else if (resource instanceof Condition) {
            Condition o = (Condition)resource;
            if (o.hasCode()) {
                return o.getCode();
            }

        } else if (resource instanceof Procedure) {
            Procedure o = (Procedure)resource;
            if (o.hasCode()) {
                return o.getCode();
            }

        } else if (resource instanceof AllergyIntolerance) {
            AllergyIntolerance o = (AllergyIntolerance)resource;
            if (o.hasSubstance()) {
                return o.getSubstance();
            }

        } else if (resource instanceof FamilyMemberHistory) {
            FamilyMemberHistory o = (FamilyMemberHistory)resource;
            if (o.hasCondition()) {
                FamilyMemberHistory.FamilyMemberHistoryConditionComponent condition = o.getCondition().get(0);
                if (condition.hasCode()) {
                    return condition.getCode();
                }
            }

        } else if (resource instanceof Immunization) {
            Immunization o = (Immunization)resource;
            if (o.hasVaccineCode()) {
                return o.getVaccineCode();
            }

        } else if (resource instanceof DiagnosticOrder) {
            DiagnosticOrder o = (DiagnosticOrder)resource;
            if (o.hasItem()) {
                DiagnosticOrder.DiagnosticOrderItemComponent item = o.getItem().get(0);
                if (item.hasCode()) {
                    return item.getCode();
                }
            }

        } else if (resource instanceof DiagnosticReport) {
            DiagnosticReport o = (DiagnosticReport)resource;
            if (o.hasCode()) {
                return o.getCode();
            }

        } else if (resource instanceof Specimen) {
            Specimen o = (Specimen)resource;
            if (o.hasType()) {
                return o.getType();
            }

        } else if (resource instanceof ReferralRequest) {
            ReferralRequest o = (ReferralRequest)resource;
            if (o.hasServiceRequested()) {
                return o.getServiceRequested().get(0); //technically supports multiple, but we only ever use one
            }

        } else if (resource instanceof Flag) {
            Flag o = (Flag)resource;
            if (o.hasCode()) {
                return o.getCode();
            }

        } else if (resource instanceof MedicationStatement) {
            MedicationStatement o = (MedicationStatement)resource;
            if (o.hasMedication()) {
                Type t = o.getMedication();
                if (t instanceof CodeableConcept) {
                    return (CodeableConcept)t;
                }
            }

        } else if (resource instanceof MedicationOrder) {
            MedicationOrder o = (MedicationOrder)resource;
            if (o.hasMedication()) {
                Type t = o.getMedication();
                if (t instanceof CodeableConcept) {
                    return (CodeableConcept)t;
                }
            }

        } else if (resource instanceof ProcedureRequest) {
            ProcedureRequest o = (ProcedureRequest)resource;
            if (o.hasCode()) {
                return o.getCode();
            }

        } else {
            throw new IllegalArgumentException("Unexpected resource type " + resource.getResourceType());
        }

        return null;
    }


    /**
     * finds the "main" effective date for a resource - handling all the different
     * resource types used in DDS
     */
    public static Date findMainEffectiveDate(Resource resource) throws IllegalArgumentException {
        if (resource instanceof Observation) {
            Observation o = (Observation)resource;
            if (o.hasEffective()) {
                Type t = o.getEffective();
                if (t instanceof DateTimeType) {
                    return ((DateTimeType)t).getValue();
                } else if (t instanceof Period) {
                    return ((Period)t).getStart();
                }
            }

        } else if (resource instanceof Condition) {
            Condition o = (Condition)resource;
            if (o.hasOnset()) {
                Type t = o.getOnset();
                if (t instanceof DateTimeType) {
                    return ((DateTimeType)t).getValue();
                } else if (t instanceof Period) {
                    return ((Period)t).getStart();
                }
            }

        } else if (resource instanceof Procedure) {
            Procedure o = (Procedure)resource;
            if (o.hasPerformed()) {
                Type t = o.getPerformed();
                if (t instanceof DateTimeType) {
                    return ((DateTimeType)t).getValue();
                } else if (t instanceof Period) {
                    return ((Period)t).getStart();
                }
            }

        } else if (resource instanceof AllergyIntolerance) {
            AllergyIntolerance o = (AllergyIntolerance)resource;
            if (o.hasOnset()) {
                return o.getOnset();
            }

        } else if (resource instanceof FamilyMemberHistory) {
            FamilyMemberHistory o = (FamilyMemberHistory)resource;
            if (o.hasDate()) {
                return o.getDate();
            }

        } else if (resource instanceof Immunization) {
            Immunization o = (Immunization)resource;
            if (o.hasDate()) {
                return o.getDate();
            }

        } else if (resource instanceof DiagnosticOrder) {
            DiagnosticOrder o = (DiagnosticOrder)resource;
            if (o.hasEvent()) {
                DiagnosticOrder.DiagnosticOrderEventComponent e = o.getEvent().get(0);
                if (e.hasDateTime()) {
                    return e.getDateTime();
                }
            }

        } else if (resource instanceof DiagnosticReport) {
            DiagnosticReport o = (DiagnosticReport)resource;
            if (o.hasEffective()) {
                Type t = o.getEffective();
                if (t instanceof DateTimeType) {
                    return ((DateTimeType)t).getValue();
                } else if (t instanceof Period) {
                    return ((Period)t).getStart();
                }
            }

        } else if (resource instanceof Specimen) {
            Specimen o = (Specimen)resource;
            if (o.hasCollection()) {
                Specimen.SpecimenCollectionComponent c = o.getCollection();
                if (c.hasCollected()) {
                    Type t = c.getCollected();
                    if (t instanceof DateTimeType) {
                        return ((DateTimeType)t).getValue();
                    } else if (t instanceof Period) {
                        return ((Period)t).getStart();
                    }
                }
            }

        } else if (resource instanceof ReferralRequest) {
            ReferralRequest o = (ReferralRequest)resource;
            if (o.hasDate()) {
                return o.getDate();
            }

        } else if (resource instanceof Flag) {
            Flag o = (Flag)resource;
            if (o.hasPeriod()) {
                Period p = o.getPeriod();
                return p.getStart();
            }

        } else if (resource instanceof MedicationStatement) {
            MedicationStatement o = (MedicationStatement)resource;
            if (o.hasDateAsserted()) {
                return o.getDateAsserted();
            }

        } else if (resource instanceof MedicationOrder) {
            MedicationOrder o = (MedicationOrder)resource;
            if (o.hasDateWritten()) {
                return o.getDateWritten();
            }

        } else if (resource instanceof ProcedureRequest) {
            ProcedureRequest o = (ProcedureRequest)resource;
            if (o.hasScheduled()) {
                Type t = o.getScheduled();
                if (t instanceof DateTimeType) {
                    return ((DateTimeType)t).getValue();
                } else if (t instanceof Period) {
                    return ((Period)t).getStart();
                }
            }

        } else {
            throw new IllegalArgumentException("Unexpected resource type " + resource.getResourceType());
        }

        return null;
    }
}
