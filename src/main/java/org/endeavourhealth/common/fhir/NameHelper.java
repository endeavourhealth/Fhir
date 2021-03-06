package org.endeavourhealth.common.fhir;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.instance.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NameHelper {

    public static List<HumanName> convert(String title, String forenames, String surname, String callingName, String birthSurname, String previousSurname) {
        return convertCleaned(
                StringUtils.trimToNull(forenames),
                StringUtils.trimToNull(callingName),
                StringUtils.trimToNull(surname),
                StringUtils.trimToNull(birthSurname),
                StringUtils.trimToNull(previousSurname),
                StringUtils.trimToNull(title));
    }

    public static HumanName convert(String forenames, String surname, String title) {
        List<HumanName> results = convertCleaned(
                StringUtils.trimToNull(forenames),
                null,
                StringUtils.trimToNull(surname),
                null,
                null,
                StringUtils.trimToNull(title));

        if (results.size() != 1)
            throw new IllegalStateException("Expected list of size 1");
        else
            return results.get(0);
    }

    private static List<HumanName> convertCleaned(String forenames, String callingName, String surname, String birthSurname, String previousSurname, String title) {
        List<HumanName> list = new ArrayList<>();

        //scope to ensure that the usualName variable is not accidentally reused during any copy and paste
        list.add(createName(HumanName.NameUse.OFFICIAL, title, forenames, surname));

        if (birthSurname != null && !birthSurname.equalsIgnoreCase(surname))
            list.add(createName(HumanName.NameUse.OLD, title, forenames, birthSurname));

        if (previousSurname != null && !previousSurname.equalsIgnoreCase(surname))
            list.add(createName(HumanName.NameUse.OLD, title, forenames, previousSurname));

        if (callingName != null && !callingName.equalsIgnoreCase(forenames))
            list.add(createName(HumanName.NameUse.USUAL, title, callingName, surname));

        return list;
    }

    private static HumanName createName(HumanName.NameUse use, String title, String forenames, String surname) {
        HumanName name = new HumanName()
                .setUse(use);

        List<String> titleList = split(title);

        if (titleList != null)
            titleList.forEach(name::addPrefix);

        List<String> forenameList = split(forenames);

        if (forenameList != null)
            forenameList.forEach(name::addGiven);

        List<String> surnameList = split(surname);

        if (surnameList != null)
            surnameList.forEach(name::addFamily);

        String displayText = generateDisplayName(name);
        name.setText(displayText);

        return name;
    }


    /*private static String buildDisplayFormat(String title, String forenames, String surname)
    {
        StringBuilder sb = new StringBuilder();

        //got some bad practitioners in EmisOpen testing, so need to handle null surnames
        if (!Strings.isNullOrEmpty(surname)) {
            sb.append(surname.toUpperCase());
        }
        //sb.append(surname.toUpperCase());

        List<String> forenameList = split(forenames);

        if (forenameList != null && forenameList.size() > 0)
        {
            sb.append(", ");
            sb.append(forenameList.get(0));
        }

        if (StringUtils.isNotBlank(title))
        {
            sb.append(" (");
            sb.append(title);
            sb.append(")");
        }

        return sb.toString();
    }*/

    public static HumanName createHumanName(HumanName.NameUse use, String title, String firstName, String middleNames, String surname) {

        return createHumanName(use, title, firstName, middleNames, surname, "");
    }


    public static HumanName createHumanName(HumanName.NameUse use, String title, String firstName, String middleNames, String surname, String suffix) {
        HumanName ret = new HumanName();
        ret.setUse(use);

        if (!Strings.isNullOrEmpty(surname)) {
            List<String> v = split(surname);
            v.forEach(ret::addFamily);
        }

        if (!Strings.isNullOrEmpty(firstName)) {
            List<String> v = split(firstName);
            v.forEach(ret::addGiven);
        }

        if (!Strings.isNullOrEmpty(middleNames)) {
            List<String> v = split(middleNames);
            v.forEach(ret::addGiven);
        }

        if (!Strings.isNullOrEmpty(suffix)) {
            List<String> v = split(suffix);
            v.forEach(ret::addSuffix);
        }

        if (!Strings.isNullOrEmpty(title)) {
            List<String> v = split(title);
            v.forEach(ret::addPrefix);
        }

        String displayName = generateDisplayName(ret);
        ret.setText(displayName);

        return ret;
    }

    /**
     * format based on www.mscui.net/DesignGuide/QuickGuides/PatientName/checklist.aspx
     */
    public static String generateDisplayName(HumanName humanName) {

        //build up full name in standard format; SURNAME, firstname (title)
        StringBuilder displayName = new StringBuilder();

        if (humanName.hasFamily()) {
            for (StringType st : humanName.getFamily()) {

                if (displayName.length() > 0) {
                    displayName.append(" ");
                }

                String s = st.toString().toUpperCase();
                displayName.append(s);
            }
        }

        if (humanName.hasGiven()) {
            if (displayName.length() > 0) {
                displayName.append(",");
            }
            for (StringType st : humanName.getGiven()) {

                if (displayName.length() > 0) {
                    displayName.append(" ");
                }

                String s = st.toString();
                displayName.append(s);
            }
        }

        if (humanName.hasSuffix()) {
            for (StringType st : humanName.getSuffix()) {

                if (displayName.length() > 0) {
                    displayName.append(" ");
                }

                String s = st.toString();
                displayName.append(s);
            }
        }

        if (humanName.hasPrefix()) {
            displayName.append(" (");

            for (int i = 0; i < humanName.getPrefix().size(); i++) {

                //add a space for second etc. prefixes
                if (i > 0) {
                    displayName.append(" ");
                }

                StringType st = humanName.getPrefix().get(i);
                String s = st.getValue();
                displayName.append(s);
            }

            displayName.append(")");
        }

        return displayName.toString().trim();
    }

    private static List<String> split(String s) {
        if (Strings.isNullOrEmpty(s)) {
            return null;
        }

        //remove any double spaces
        while (s.indexOf("  ") > -1) {
            s = s.replaceAll("  ", " ");
        }

        return Arrays.asList(s.split(" "));
    }

    /**
     * in cases where we have a name as a single string, this takes a best-guess approach to
     * tokenise that name to fit the minimum requirements of the FHIR resource, based on the
     * NHS standard name format; SURNAME, forename (title)
     */
    public static HumanName convert(String name) {
        name = StringUtils.trimToNull(name);
        if (Strings.isNullOrEmpty(name)) {
            return null;
        }

        HumanName fhirName = new HumanName();
        fhirName.setUse(HumanName.NameUse.USUAL);
        fhirName.setText(name);

        String[] tokens = name.split(",");
        if (tokens.length != 2) {
            //if there's no comma, then just treat the name as a surname
            setSurname(fhirName, name);
            return fhirName;
        }

        String surname = tokens[0];
        setSurname(fhirName, surname);

        String firstAndTitle = tokens[1];
        tokens = firstAndTitle.split("\\(");
        if (tokens.length != 2) {
            //if there's no bracket, then treat what we have as first name
            setForename(fhirName, firstAndTitle);
            return fhirName;
        }

        String forenames = tokens[0];
        setForename(fhirName, forenames);

        String title = tokens[1];
        title = title.replaceAll("\\)", "");
        setTitle(fhirName, title);

        return fhirName;
    }

    private static void setTitle(HumanName fhirName, String s) {
        s = s.trim();
        String[] tokens = s.split(" ");
        for (String token : tokens) {
            fhirName.addPrefix(token);
        }
    }

    private static void setForename(HumanName fhirName, String s) {
        s = s.trim();
        String[] tokens = s.split(" ");
        for (String token : tokens) {
            fhirName.addGiven(token);
        }
    }

    private static void setSurname(HumanName fhirName, String s) {
        s = s.trim();
        String[] tokens = s.split(" ");
        for (String token : tokens) {
            fhirName.addFamily(token);
        }
    }

    /*private static boolean isTitle(String s) {
        return s.equalsIgnoreCase("mr.")
                || s.equalsIgnoreCase("master.")
                || s.equalsIgnoreCase("miss.")
                || s.equalsIgnoreCase("mrs.")
                || s.equalsIgnoreCase("dr.")
                || s.equalsIgnoreCase("rev.");
    }*/



    /**
     * returns the "best" HumanName to use from a Patient resource
     */
    public static HumanName findName(Patient fhirPatient) {

        List<HumanName> officialNames = new ArrayList<>();
        if (fhirPatient.hasName()) {
            for (HumanName fhirName : fhirPatient.getName()) {
                if (fhirName.getUse() == HumanName.NameUse.OFFICIAL) {
                    officialNames.add(fhirName);
                }
            }
        }

        //return first non-ended one
        for (HumanName name: officialNames) {
            if (!name.hasPeriod()
                    || PeriodHelper.isActive(name.getPeriod())) {
                return name;
            }
        }

        //if no non-ended one, then return the last one, as it was added most recently
        if (!officialNames.isEmpty()) {
            int size = officialNames.size();
            return officialNames.get(size-1);
        }

        return null;
    }

    public static String findPrefix(Patient fhirPatient) {
        List<String> l = new ArrayList<>();

        HumanName fhirName = findName(fhirPatient);
        if (fhirName != null) {
            for (StringType s: fhirName.getPrefix()) {
                l.add(s.getValue());
            }
        }
        return String.join(" ", l);
    }

    public static String findSuffix(Patient fhirPatient) {
        List<String> l = new ArrayList<>();

        HumanName fhirName = findName(fhirPatient);
        if (fhirName != null) {
            for (StringType s: fhirName.getSuffix()) {
                l.add(s.getValue());
            }
        }
        return String.join(" ", l);
    }

    public static String findForenames(Patient fhirPatient) {

        List<String> forenames = new ArrayList<>();

        HumanName fhirName = findName(fhirPatient);
        if (fhirName != null) {
            for (StringType given: fhirName.getGiven()) {
                forenames.add(given.getValue());
            }
        }
        return String.join(" ", forenames);
    }

    public static String findSurname(Patient fhirPatient) {
        List<String> surnames = new ArrayList<>();

        HumanName fhirName = findName(fhirPatient);
        if (fhirName != null) {
            for (StringType family: fhirName.getFamily()) {
                surnames.add(family.getValue());
            }
        }
        return String.join(" ", surnames);
    }



    public static boolean hasPrefix(HumanName humanName, String prefix) {
        if (!humanName.hasPrefix()) {
            return false;
        }

        for (StringType s: humanName.getPrefix()) {
            String str = s.getValue();
            if (prefix.equalsIgnoreCase(str)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasGivenName(HumanName humanName, String givenName) {
        if (!humanName.hasGiven()) {
            return false;
        }

        for (StringType s: humanName.getGiven()) {
            String str = s.getValue();
            if (givenName.equalsIgnoreCase(str)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasFamilyName(HumanName humanName, String familyName) {
        if (!humanName.hasFamily()) {
            return false;
        }

        for (StringType s: humanName.getFamily()) {
            String str = s.getValue();
            if (familyName.equalsIgnoreCase(str)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasSuffix(HumanName humanName, String suffix) {
        if (!humanName.hasSuffix()) {
            return false;
        }

        for (StringType s: humanName.getSuffix()) {
            String str = s.getValue();
            if (suffix.equalsIgnoreCase(str)) {
                return true;
            }
        }

        return false;
    }


    /**
     * finds all names that match the one supplied
     * Note: this function specifically doesn't compare periods or dates, and just looks for matches
     * on the name fields.
     */
    public static List<HumanName> findMatches(HumanName toCheck, List<HumanName> potentials) {

        List<HumanName> ret = new ArrayList<>();

        for (HumanName name: potentials) {

            boolean matches = true;

            if (name.hasPrefix()) {
                for (StringType prefix : name.getPrefix()) {
                    if (!hasPrefix(toCheck, prefix.toString())) {
                        matches = false;
                        break;
                    }
                }
            }

            if (name.hasGiven()) {
                for (StringType given : name.getGiven()) {
                    if (!hasGivenName(toCheck, given.toString())) {
                        matches = false;
                        break;
                    }
                }
            }

            if (name.hasFamily()) {
                for (StringType family : name.getFamily()) {
                    if (!hasFamilyName(toCheck, family.toString())) {
                        matches = false;
                        break;
                    }
                }
            }

            if (name.hasSuffix()) {
                for (StringType suffix : name.getSuffix()) {
                    if (!hasSuffix(toCheck, suffix.toString())) {
                        matches = false;
                        break;
                    }
                }
            }

            if (name.hasUse()) {
                HumanName.NameUse use = name.getUse();
                if (!hasUse(toCheck, use)) {
                    matches = false;
                }
            }

            if (matches) {
                ret.add(name);
            }
        }

        return ret;
    }

    private static boolean hasUse(HumanName toCheck, HumanName.NameUse use) {

        //when a name is ended it's use is changed to OLD, so compare use normally,
        //but also let the use pass if either one of them is OLD
        return toCheck.hasUse()
                && (toCheck.getUse() == use
                || toCheck.getUse() == HumanName.NameUse.OLD
                || use == HumanName.NameUse.OLD);
    }

}

