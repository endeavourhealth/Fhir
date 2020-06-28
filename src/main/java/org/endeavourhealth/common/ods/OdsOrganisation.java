package org.endeavourhealth.common.ods;

import org.endeavourhealth.common.fhir.schema.OrganisationClass;
import org.endeavourhealth.common.fhir.schema.OrganisationType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OdsOrganisation {
    private String odsCode;
    private String organisationName;
    private OrganisationClass organisationClass;
    private OrganisationType organisationType;
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String county;
    private String postcode;
    private boolean isActive;
    private Map<String, String> parents = new HashMap<>(); //map of parent ODS Code and name

    public String getOdsCode() {
        return odsCode;
    }

    public void setOdsCode(String odsCode) {
        this.odsCode = odsCode;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public OrganisationClass getOrganisationClass() {
        return organisationClass;
    }

    public void setOrganisationClass(OrganisationClass organisationClass) {
        this.organisationClass = organisationClass;
    }

    public OrganisationType getOrganisationType() {
        return organisationType;
    }

    public void setOrganisationType(OrganisationType organisationType) {
        this.organisationType = organisationType;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Map<String, String> getParents() {
        return parents;
    }

    public void setParents(Map<String, String> parents) {
        this.parents = parents;
    }

    @Override
    public String toString() {
        return "OdsOrganisation{" +
                "odsCode='" + odsCode + '\'' +
                ", organisationName='" + organisationName + '\'' +
                ", organisationClass=" + organisationClass +
                ", organisationType=" + organisationType +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", town='" + town + '\'' +
                ", county='" + county + '\'' +
                ", postcode='" + postcode + '\'' +
                ", isActive=" + isActive +
                ", parents=" + parents +
                '}';
    }

    /**
     * some orgs have multiple parents, and the simplest way to choose just one seems to be
     * to ignore the old SHA and Genomic hierarchy and select the first one otherwise
     */
    public static String getBestParentCode(OdsOrganisation odsRecord) {

        Map<String, String> parents = odsRecord.getParents();
        if (parents.size() == 1) {
            return parents.keySet().iterator().next();

        }

        for (String code: parents.keySet()) {
            String parentName = parents.get(code);
            parentName =  parentName.toUpperCase();
            if (!parentName.contains("STRATEGIC HEALTH AUTHORITY")
                    && !parentName.contains("GENOMIC")) {

                return code;
            }
        }

        return null;
    }
}

