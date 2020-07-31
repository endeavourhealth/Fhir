package org.endeavourhealth.common.ods;

import org.endeavourhealth.common.fhir.schema.OrganisationClass;
import org.endeavourhealth.common.fhir.schema.OrganisationType;

import java.util.*;

public class OdsOrganisation {
    private String odsCode;
    private String organisationName;
    private OrganisationClass organisationClass;
    private Set<OrganisationType> organisationTypes = new HashSet<>();
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String county;
    private String postcode;
    private boolean isActive;
    private Map<String, OdsOrganisation> parents = new HashMap<>(); //map of parent ODS Code and name

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

    public Set<OrganisationType> getOrganisationTypes() {
        return organisationTypes;
    }

    public void setOrganisationTypes(Set<OrganisationType> organisationTypes) {
        this.organisationTypes = organisationTypes;
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

    public Map<String, OdsOrganisation> getParents() {
        return parents;
    }

    public void setParents(Map<String, OdsOrganisation> parents) {
        this.parents = parents;
    }

    @Override
    public String toString() {
        return "OdsOrganisation{\r\n" +
                "odsCode=" + odsCode + "\r\n" +
                "organisationName=" + organisationName + "\r\n" +
                "organisationClass=" + organisationClass + "\r\n" +
                "organisationTypes=" + organisationTypes + "\r\n" +
                "addressLine1=" + addressLine1 + "\r\n" +
                "addressLine2=" + addressLine2 + "\r\n" +
                "town=" + town + "\r\n" +
                "county=" + county + "\r\n" +
                "postcode=" + postcode + "\r\n" +
                "isActive=" + isActive + "\r\n" +
                "parents=" + parents.keySet() + "\r\n" +
                '}';
    }

    /**
     * some orgs have multiple parents, and the simplest way to choose just one seems to be
     * to ignore the old SHA and Genomic hierarchy and select the first one otherwise
     */
    /*public static String getBestParentCode(OdsOrganisation odsRecord) {

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
    }*/
}

