package org.endeavourhealth.common.fhir;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.instance.model.Address;
import org.hl7.fhir.instance.model.ContactPoint;
import org.hl7.fhir.instance.model.StringType;

import java.util.ArrayList;
import java.util.List;

public class ContactPointHelper {


    public static ContactPoint create(ContactPoint.ContactPointSystem system, ContactPoint.ContactPointUse use, String value) {

        if (Strings.isNullOrEmpty(value)) {
            return null;
        }

        return new ContactPoint()
                .setSystem(system)
                .setUse(use)
                .setValue(value);
    }

    public static List<ContactPoint> createWorkContactPoints(String telephone1, String telephone2, String fax, String email)
    {
        List<ContactPoint> contactPoints = new ArrayList<>();

        if (StringUtils.isNotBlank(telephone1))
            contactPoints.add(create(ContactPoint.ContactPointSystem.PHONE, ContactPoint.ContactPointUse.WORK, telephone1));

        if (StringUtils.isNotBlank(telephone2))
            contactPoints.add(create(ContactPoint.ContactPointSystem.PHONE, ContactPoint.ContactPointUse.WORK, telephone2));

        if (StringUtils.isNotBlank(email))
            contactPoints.add(create(ContactPoint.ContactPointSystem.EMAIL, ContactPoint.ContactPointUse.WORK, email));

        if (StringUtils.isNotBlank(fax))
            contactPoints.add(create(ContactPoint.ContactPointSystem.FAX, ContactPoint.ContactPointUse.WORK, fax));

        return contactPoints;
    }

    /**
     * finds matches based on content, but specifically ignores ID and Period
     */
    public static List<ContactPoint> findMatches(ContactPoint toCheck, List<ContactPoint> potentials) {

        List<ContactPoint> ret = new ArrayList<>();

        for (ContactPoint contactPoint: potentials) {

            boolean matches = true;

            if (contactPoint.hasValue()) {
                String value = contactPoint.getValue();
                if (!hasValue(toCheck, value)) {
                    matches = false;
                }
            }

            if (contactPoint.hasUse()) {
                ContactPoint.ContactPointUse use = contactPoint.getUse();
                if (!hasUse(toCheck, use)) {
                    matches = false;
                }
            }

            //only compare system if they both have one, because some feeds don't set it (e.g. ADT feed)
            if (contactPoint.hasSystem()
                    && toCheck.hasSystem()) {
                ContactPoint.ContactPointSystem system = contactPoint.getSystem();
                if (!hasSystem(toCheck, system)) {
                    matches = false;
                }
            }

            if (matches) {
                ret.add(contactPoint);
            }
        }

        return ret;
    }

    public static boolean hasSystem(ContactPoint toCheck, ContactPoint.ContactPointSystem system) {
        return toCheck.hasSystem()
                && toCheck.getSystem() == system;
    }

    public static boolean hasValue(ContactPoint toCheck, String value) {
        if (toCheck.hasValue()) {

            //I think we have some feeds that are inconsistent with numbers containing spaces, so just remove any
            value = value.replace(" ", "");

            String s = toCheck.getValue();
            s = s.replace(" ", "");

            if (s.equalsIgnoreCase(value)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasUse(ContactPoint telecom, ContactPoint.ContactPointUse use) {

        //when a telecom is ended it's use is changed to OLD, so compare use normally,
        //but also let the use pass if either one of them is OLD
        return telecom.hasUse()
                && (telecom.getUse() == use
                    || telecom.getUse() == ContactPoint.ContactPointUse.OLD
                    || use == ContactPoint.ContactPointUse.OLD);
    }
}

