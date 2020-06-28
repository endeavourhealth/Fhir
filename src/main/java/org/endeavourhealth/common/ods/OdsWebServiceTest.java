package org.endeavourhealth.common.ods;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OdsWebServiceTest {

    @Test
    void lookupOrganisationViaRestOld() throws Exception {
        OdsOrganisation org  = OdsWebService.lookupOrganisationViaRest("E85636", null);
        System.out.println("Name: " + org.getOrganisationName());
        System.out.println("Type: " + org.getOrganisationType());
        System.out.println("Class: " + org.getOrganisationClass());
        System.out.println("" + org.getAddressLine1());
        System.out.println("" + org.getAddressLine2());
        System.out.println("" + org.getTown());
        System.out.println("" + org.getCounty());
        System.out.println("" + org.getPostcode());
        System.out.println("Active: " + org.isActive());

    }
    @Test
    void lookupOrganisationViaRest() throws Exception {
        OdsOrganisation org  = OdsWebService.lookupOrganisationViaRest_2("E85636", null);
        System.out.println("Name: " + org.getOrganisationName());
        System.out.println("Type: " + org.getOrganisationType());
        System.out.println("Class: " + org.getOrganisationClass());
        System.out.println("" + org.getAddressLine1());
        System.out.println("" + org.getAddressLine2());
        System.out.println("" + org.getTown());
        System.out.println("" + org.getCounty());
        System.out.println("" + org.getPostcode());
        System.out.println("Active: " + org.isActive());

    }

    @Test
    void lookupOrganisationViaRestOds() throws Exception {
        OdsOrganisation org  = OdsWebService.lookUpOrganisationViaRestOds("E85636", null);
        System.out.println("Name: " + org.getOrganisationName());
        System.out.println("Type: " + org.getOrganisationType());
        System.out.println("Class: " + org.getOrganisationClass());
        System.out.println("" + org.getAddressLine1());
        System.out.println("" + org.getAddressLine2());
        System.out.println("" + org.getTown());
        System.out.println("" + org.getCounty());
        System.out.println("" + org.getPostcode());
        System.out.println("Active: " + org.isActive());

    }
}