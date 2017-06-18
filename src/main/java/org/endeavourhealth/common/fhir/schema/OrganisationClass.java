package org.endeavourhealth.common.fhir.schema;

import org.apache.commons.lang3.StringUtils;

public enum OrganisationClass {
    HSC_ORG("O", "HSCOrg"),
    HSC_SITE("S", "HSCSite");

    private String organisationClass;
    private String organisationClassName;

    OrganisationClass(String organisationClass, String organisationClassName) {
        this.organisationClass = organisationClass;
        this.organisationClassName = organisationClassName;
    }

    public static OrganisationClass fromOrganisationClass(String organisationClass) {
        for (OrganisationClass c: OrganisationClass.values())
            if (StringUtils.equalsIgnoreCase(c.organisationClass, StringUtils.trim(organisationClass)))
                return c;

        throw new IllegalArgumentException(organisationClass);
    }

    public static OrganisationClass fromOrganisationClassName(String organisationClassName) {
        for (OrganisationClass c: OrganisationClass.values())
            if (StringUtils.equalsIgnoreCase(c.organisationClassName, StringUtils.trim(organisationClassName)))
                return c;

        throw new IllegalArgumentException(organisationClassName);
    }

    public String getOrganisationClass() {
        return this.organisationClass;
    }

    public String getOrganisationClassName() {
        return this.organisationClassName;
    }
}