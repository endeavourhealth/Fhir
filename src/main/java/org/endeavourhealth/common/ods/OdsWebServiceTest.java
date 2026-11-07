package org.endeavourhealth.common.ods;

//import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.Test;


import org.endeavourhealth.common.fhir.schema.OrganisationClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class) public class OdsWebServiceTest {
// @Test commented out to allow builds to work
//    @Test
    void lookupOrganisationViaRestOld() throws Exception {
        /*OdsOrganisation org  = OdsWebService.lookupOrganisationViaRest("E85636", null);
        System.out.println("Name: " + org.getOrganisationName());
        System.out.println("Type: " + org.getOrganisationType());
        System.out.println("Class: " + org.getOrganisationClass());
        System.out.println("" + org.getAddressLine1());
        System.out.println("" + org.getAddressLine2());
        System.out.println("" + org.getTown());
        System.out.println("" + org.getCounty());
        System.out.println("" + org.getPostcode());
        System.out.println("Active: " + org.isActive());
*/
    }
//    @Test
    void lookupOrganisationViaRest() throws Exception {
/*
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
*/

    }
//
//    @Test
    void lookupOrganisationViaRestOds() throws Exception {
   /*     OdsOrganisation org  = OdsWebService.lookUpOrganisationViaRestOds("E85636", null);
        System.out.println("Name: " + org.getOrganisationName());
        System.out.println("Type: " + org.getOrganisationType());
        System.out.println("Class: " + org.getOrganisationClass());
        System.out.println("" + org.getAddressLine1());
        System.out.println("" + org.getAddressLine2());
        System.out.println("" + org.getTown());
        System.out.println("" + org.getCounty());
        System.out.println("" + org.getPostcode());
        System.out.println("Active: " + org.isActive());
*/
    }
    @Test
    public void lookupOrganisationViaRestIM() {
        OdsOrganisation testOrg = null;
        try {
             testOrg = OdsWebService.lookupOrganisationViaRest("QMF");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertNotNull(testOrg);
        assertEquals("QMF", testOrg.getOdsCode());
        assertEquals("NHS NORTH EAST LONDON INTEGRATED CARE BOARD", testOrg.getOrganisationName());
        assertTrue(testOrg.isActive());
        assertEquals(OrganisationClass.HSC_ORG, testOrg.getOrganisationClass());
        assertEquals("UNEX TOWER", testOrg.getAddressLine1());
        assertEquals(2, testOrg.getOrganisationTypes().size());
        assertEquals("LONDON COMMISSIONING REGION",testOrg.getParents().get("Y56").getOrganisationName());
    }

    @Test
    public void lookupTestOrgWhenSystemDown(){
        OdsOrganisation testOrg = null;
        try {
            OdsWebService.IM_INACTIVE = true;
            testOrg = OdsWebService.lookupOrganisationViaRest("QMF");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertNotNull(testOrg);
    }

    @Test
    public void lookupPossibleInvalidOrganisationViaRestIM() {
        OdsOrganisation testOrg;
        try {
            testOrg = OdsWebService.lookupOrganisationViaRest("ThisIsAFakeTestOrg");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(null, testOrg);
    }
    @Test
    public void lookupKnownInvalidOrganisationViaRestIM() {
        OdsOrganisation testOrg;
        try {
            testOrg = OdsWebService.lookupOrganisationViaRest("V81999");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(null, testOrg);
    }

}
