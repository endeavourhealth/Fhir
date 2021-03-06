package org.endeavourhealth.common.fhir;

import com.google.common.base.Strings;
import org.endeavourhealth.common.fhir.schema.NhsNumberVerificationStatus;
import org.hl7.fhir.instance.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IdentifierHelper {

    public static Identifier createNhsNumberIdentifier(String value) {
        return createIdentifier(Identifier.IdentifierUse.OFFICIAL, FhirIdentifierUri.IDENTIFIER_SYSTEM_NHSNUMBER, value);
    }
    public static Identifier createOdsOrganisationIdentifier(String value) {
        return createIdentifier(Identifier.IdentifierUse.OFFICIAL, FhirIdentifierUri.IDENTIFIER_SYSTEM_ODS_CODE, value);
    }
    public static Identifier createUbrnIdentifier(String value) {
        return createIdentifier(Identifier.IdentifierUse.OFFICIAL, FhirIdentifierUri.IDENTIFIER_SYSTEM_UBRN, value);
    }
    public static Identifier createGmcIdentifier(String value) {
        return createIdentifier(Identifier.IdentifierUse.OFFICIAL, FhirIdentifierUri.IDENTIFIER_SYSTEM_GMC_NUMBER, value);
    }

    public static Identifier createIdentifier(Identifier.IdentifierUse use, String system, String value) {

        if (Strings.isNullOrEmpty(value)) {
            return null;
        }

        return new Identifier()
                .setUse(use)
                .setValue(value)
                .setSystem(system);
    }

    public static String findIdentifierValue(List<Identifier> identifiers, String system) {
        for (Identifier id: identifiers) {
            if (id.getSystem().equals(system)) {
                return id.getValue();
            }
        }

        return null;
    }

    public static List<Identifier> findIdentifiersForSystem(List<Identifier> identifiers, String system) {
        List<Identifier> ret = new ArrayList<>();
        for (Identifier id: identifiers) {
            if (id.getSystem().equals(system)) {
                ret.add(id);
            }
        }
        return ret;
    }

    /**
     * returns the NHS number for the patient, handling multiple identifiers being present and validating
     * that the one returned is most likely a true NHS number
     */
    public static String findNhsNumber(Patient fhirPatient) {

        List<Identifier> identifiers = findIdentifiersForSystem(fhirPatient.getIdentifier(), FhirIdentifierUri.IDENTIFIER_SYSTEM_NHSNUMBER);

        //remove any that don't look like real NHS numbers
        for (int i=identifiers.size()-1; i>=0; i--) {
            Identifier identifier = identifiers.get(i);
            String value = identifier.getValue();

            //validate that it's 10 digits
            boolean keep = true;
            if (Strings.isNullOrEmpty(value)) {
                keep = false;

            } else {
                value = value.replace(" ", ""); //shouldn't be necessary, but needed because of ADT transform

                if (value.length() != 10) {
                    keep = false;
                } else {

                    for (char c: value.toCharArray()) {
                        if (!Character.isDigit(c)) {
                            keep = false;
                            break;
                        }
                    }
                }
            }

            if (!keep) {
                identifiers.remove(i);
            }
        }

        if (identifiers.isEmpty()) {
            return null;
        }

        //sort by end date, falling back on start date if end date is the same
        identifiers.sort((o1, o2) -> {

            Period period1 = null;
            if (o1.hasPeriod()) {
                period1 = o1.getPeriod();
            }
            Period period2 = null;
            if (o2.hasPeriod()) {
                period2 = o2.getPeriod();
            }

            return PeriodHelper.comparePeriods(period1, period2);
        });

        //the above sorting means the last one in the list is the one we want
        Identifier lastIdentifier = identifiers.get(identifiers.size()-1);
        String value = lastIdentifier.getValue();
        value = value.replace(" ", ""); //shouldn't be necessary, but needed because of ADT transform
        return value;

        /*if (fhirPatient.hasIdentifier()) {
            return findIdentifierValue(fhirPatient.getIdentifier(), FhirIdentifierUri.IDENTIFIER_SYSTEM_NHSNUMBER);
        }
        return null;*/
    }



    public static String findOdsCode(Organization organization) {
        if (organization.hasIdentifier()) {
            return findIdentifierValue(organization.getIdentifier(), FhirIdentifierUri.IDENTIFIER_SYSTEM_ODS_CODE);
        }
        return null;
    }


    /**
     * finds matches based on content, but specifically ignores ID and Period
     */
    public static List<Identifier> findMatches(Identifier toCheck, List<Identifier> potentials) {

        List<Identifier> ret = new ArrayList<>();

        for (Identifier identifier: potentials) {

            boolean matches = true;

            if (identifier.hasValue()) {
                if (!hasValue(toCheck, identifier.getValue())) {
                    matches = false;
                }
            }

            //the USE property doesn't get consistently set (or really used for anything) so don't compare it
            /*if (identifier.hasUse()) {
                if (!hasUse(toCheck, identifier.getUse())) {
                    matches = false;
                }
            }*/

            if (identifier.hasSystem()) {
                if (!hasSystem(toCheck, identifier.getSystem())) {
                    matches = false;
                }
            }

            //if we make it here, it's a duplicate and should be removed
            if (matches) {
                ret.add(identifier);
            }
        }

        return ret;
    }

    private static boolean hasValue(Identifier toCheck, String value) {
        return toCheck.hasValue()
                && toCheck.getValue().equalsIgnoreCase(value);
    }

    private static boolean hasSystem(Identifier toCheck, String system) {
        return toCheck.hasSystem()
                && toCheck.getSystem().equalsIgnoreCase(system);
    }


    /**
     * validates if a String is a valid NHS number
     * expects NHS numbers without any delimiters or spacers (NNNNNNNNNN)
     *
     * returns null if empty, true if valid, false if not valid
     */
    public static Boolean isValidNhsNumber(String toTest) {
        if (Strings.isNullOrEmpty(toTest)) {
            return null;
        }

        if (toTest.length() != 10) {
            return Boolean.FALSE;
        }

        int sum = 0;

        char[] chars = toTest.toCharArray();
        for (int i=0; i<9; i++) {
            char c = chars[i];

            if (!Character.isDigit(c)) {
                return Boolean.FALSE;
            }

            int val = Character.getNumericValue(c);
            int weight = 10 - i;
            int m = val * weight;
            sum += m;
        }

        int remainder = sum % 11;
        int check = 11 - remainder;
        if (check == 11) {
            check = 0;
        }
        if (check == 10) {
            return Boolean.FALSE;
        }

        char lastChar = chars[9];
        int actualCheck = Character.getNumericValue(lastChar);
        if (check != actualCheck) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public static NhsNumberVerificationStatus findNhsNumberVerificationStatus(Patient fhirPatient) {
        CodeableConcept codeableConcept = (CodeableConcept)ExtensionConverter.findExtensionValue(fhirPatient, FhirExtensionUri.PATIENT_NHS_NUMBER_VERIFICATION_STATUS);
        if (codeableConcept == null
                || !codeableConcept.hasCoding()) {
            return null;
        }

        Coding coding = codeableConcept.getCoding().get(0);
        if (!coding.hasCode()) {
            return null;
        }

        String code = coding.getCode();
        return NhsNumberVerificationStatus.fromCode(code);
    }
}
