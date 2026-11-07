package org.endeavourhealth.common.ods;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.Validate;
import org.endeavourhealth.common.fhir.schema.OrganisationClass;
import org.endeavourhealth.common.fhir.schema.OrganisationType;
import org.endeavourhealth.common.utility.ExpiringCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

public class OdsWebService {
    private static final Logger LOG = LoggerFactory.getLogger(OdsWebService.class);

    //private static final String OPEN_ODS_URL = "http://api.openods.co.uk/api/organisations/";

    //private static final String SPINE_SERVICES_FHIR_URL = "https://directory.spineservices.nhs.uk/STU3/Organization/";
    private static final String SPINE_SERVICES_ORD_URL = "https://directory.spineservices.nhs.uk/ORD/2-0-0/organisations/";
    private static final String INFORMATION_MODEL_ORG_URL = "https://im.endhealth.co.uk/imapi/api/ods/public/organisation/";
    private static final String INFORMATION_MODEL_API_URL = "https://im.endhealth.co.uk/imapi/api";
    static boolean IM_INACTIVE = false;
    private static ExpiringCache<String, OdsOrganisation> ordCache = new ExpiringCache<>(120 * 60 * 1000); //cache for two hours (SD-796)

    public static OdsOrganisation lookupOrganisationViaRest(String odsCode) throws Exception {
        return lookUpOrganisationViaRestNew(odsCode, null);
    }

    public static OdsOrganisation lookupOrganisationViaRest(String odsCode, Proxy proxy) throws Exception {
        return lookUpOrganisationViaRestNew(odsCode, proxy);
    }


    private static OdsOrganisation lookUpOrganisationViaRestNew(String odsCode) throws Exception {
        return lookUpOrganisationViaRestNew(odsCode, null);
    }

    private static OdsOrganisation lookUpOrganisationViaRestNew(String odsCode, Proxy proxy) throws Exception {
        Validate.notEmpty(odsCode);

        //SD-928 - ignore repeatedly calling API for known invalid ODS codes
        if (isInValidODSCode(odsCode)) {
            return null;
        }

        OdsOrganisation ret = ordCache.get(odsCode);
        if (ret == null) {
            ret = tryLookUpOrganisationViaRestNew(odsCode, proxy);
            if (ret != null) {
                ordCache.put(odsCode, ret);
            }
        }

        return ret;
    }

    //SD-928 - if the ODS code passed in is a know invalid code, return out as invalid
    //Added in additional invalid ODS codes from the top 25 list provided by NHS England from the API 404 logs
    //and the identified EMIS organisation file with the invalid ODS codes located within:
    //delta_56644_Admin_Organisation_20231003000006_2D106103-404D-4054-89B8-18F95932B092
    private static boolean isInValidODSCode(String odsCode) {

        return odsCode.equals("V81999") || odsCode.equals("V81998") || odsCode.equals("V81997") ||   //19,836
                odsCode.equals("HB") || odsCode.equals("HC") || odsCode.equals("HAA") ||
                odsCode.equals("HD") || odsCode.equals("0") || odsCode.equals("HE") ||
                odsCode.equals("HF") || odsCode.equals("HG") || odsCode.equals("HH") ||
                odsCode.equals("HJ") || odsCode.equals("HAB") || odsCode.equals("HI") ||
                odsCode.equals("HZzz") || odsCode.equals("HK") || odsCode.equals("HN") ||
                odsCode.equals("HAC") || odsCode.equals("HL") || odsCode.equals("HM") ||
                odsCode.equals("HQ") || odsCode.equals("HO") || odsCode.equals("HAG") ||
                odsCode.equals("HAD") || odsCode.equals("HP") || odsCode.equals("HAF") ||   //top 25 count to here
                odsCode.equals("HU") || odsCode.equals("HAE") || odsCode.equals("HS") ||
                odsCode.equals("HR") || odsCode.equals("HX") || odsCode.equals("HT") ||
                odsCode.equals("HAI") || odsCode.equals("HAH") || odsCode.equals("HW") ||
                odsCode.equals("HZ") || odsCode.equals("HAJ") || odsCode.equals("HAK") ||
                odsCode.equals("HAL") || odsCode.equals("HV") || odsCode.equals("HAN") ||
                odsCode.equals("HY") || odsCode.equals("HAO") || odsCode.equals("HAM") ||
                odsCode.equals("HAP") || odsCode.equals("HAR") || odsCode.equals("HAQ") ||
                odsCode.equals("HBA") || odsCode.equals("HAT") || odsCode.equals("HAW") ||
                odsCode.equals("HAU") || odsCode.equals("HAV") || odsCode.equals("HAS") ||
                odsCode.equals("HAZ") || odsCode.equals("HBD") || odsCode.equals("HA") ||
                odsCode.equals("HAX") || odsCode.equals("HAY") || odsCode.equals("HBB") ||
                odsCode.equals("HBC") || odsCode.equals("HBT") || odsCode.equals("HBH") ||
                odsCode.equals("HBG") || odsCode.equals("HBE") || odsCode.equals("HBL") ||   //50
                odsCode.equals("HBU") || odsCode.equals("HBK") || odsCode.equals("HBQ") ||
                odsCode.equals("HBM") || odsCode.equals("HBF") || odsCode.equals("HBZ") ||
                odsCode.equals("HBO") || odsCode.equals("HBI") || odsCode.equals("HCB") ||
                odsCode.equals("HCA") || odsCode.equals("HBN") || odsCode.equals("HCC") ||
                odsCode.equals("HBP") || odsCode.equals("HBW") || odsCode.equals("HBJ") ||
                odsCode.equals("HBS") || odsCode.equals("HBR") || odsCode.equals("HBX") ||
                odsCode.equals("556748283102") || odsCode.equals("308613276564") ||
                odsCode.equals("HCG") || odsCode.equals("HBV") || odsCode.equals("HCE") ||
                odsCode.equals("HCJ") || odsCode.equals("HCU") || odsCode.equals("HCN") ||
                odsCode.equals("HHZzz") || odsCode.equals("HCH") || odsCode.equals("HCF") ||
                odsCode.equals("HCI") || odsCode.equals("HFA") || odsCode.equals("HCW") || odsCode.equals("HCP") ||
                odsCode.startsWith("?");  //SD-1019
    }

    /*
     * the API fails with a 503 (Service Unavailable) or 504 (Gateway timeout) error every now and again
     * or a "javax.net.ssl.SSLException: Couldn't kickstart handshaking" if the server cannot handle >50 connections (SD-718)
     * or a "javax.net.ssl.SSLException: Connection reset" or "java.net.SocketException: Broken pipe (Write failed)" (SD-796)
     * or a "502 -> Bad Gateway" (SD-920)
     * This function gives it 60 goes with a 10s delay between each attempt before failing.
     */
    private static OdsOrganisation tryLookUpOrganisationViaRestNew(String odsCode, Proxy proxy) throws Exception {
        int lives = 60; //the API was down for several minutes, so give it up to 10 min (with 10s retry delay) before failing

        while (true) {
            lives--;

            try {
                return lookUpOrganisationViaRestNewImpl(odsCode, proxy);

            } catch (Exception ex) {
                String msg = ex.getMessage();
                boolean isConnectionErrorForRetry = msg != null
                        && (msg.contains("502")
                        || msg.contains("503")
                        || msg.contains("504")
                        || msg.contains("Couldn't kickstart handshaking")
                        || msg.contains("Connection reset")
                        || msg.contains("readHandshakeRecord")
                        || msg.contains("Broken pipe (Write failed)"));

                if (!isConnectionErrorForRetry || lives <= 0) {
                    throw ex;
                }

                LOG.warn("Exception " + ex.getMessage() + " calling into ODS web service will try " + lives + " more times");
                Thread.sleep(10 * 1000);
            }
        }
    }

    private static OdsOrganisation lookUpOrganisationViaRestNewImpl(String odsCode, Proxy proxy) throws Exception {

        //if no proxy, use the "no proxy" instance
        if (proxy == null) {
            proxy = Proxy.NO_PROXY;
        }
        String urlStr;
//        HttpURLConnection connection = (HttpURLConnection) new URL(INFORMATION_MODEL_API_URL).openConnection(proxy);
//        if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND){
//            LOG.debug(INFORMATION_MODEL_API_URL + " down, swapping to " + SPINE_SERVICES_ORD_URL);
//            IM_INACTIVE = true;
//        }
//        connection.disconnect();
        odsCode = odsCode.replace(" ", "%20");
        //if (IM_INACTIVE) {
        urlStr = SPINE_SERVICES_ORD_URL + odsCode;
        //} else {
          //  urlStr = INFORMATION_MODEL_ORG_URL + odsCode;
        //}
        //URL url = new URL(urlStr);
        //HttpURLConnection connection = (HttpURLConnection) new URL(INFORMATION_MODEL_API_URL).openConnection(proxy);
        HttpURLConnection connection = (HttpURLConnection) new URL(urlStr).openConnection(proxy);
        //connection = (HttpURLConnection) url.openConnection(proxy);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            return null;
        }

        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP response " + responseCode + " returned for GET to " + urlStr);
        }

        InputStream response = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(response);
        JsonParser parser = new JsonParser();
        JsonObject jsonRoot = (JsonObject) parser.parse(inputStreamReader);

        OdsOrganisation ret = new OdsOrganisation();

        if (!jsonRoot.has("Organisation")) {
            throw new Exception("No Organisation element found in JSON from " + urlStr);
        }
        jsonRoot = jsonRoot.getAsJsonObject("Organisation");

        if (!jsonRoot.has("OrgId")) {
            throw new Exception("No OrgId element found in JSON from " + urlStr);
        }
        JsonObject orgRoot = (JsonObject) jsonRoot.get("OrgId");
        if (!orgRoot.has("extension")) {
            throw new Exception("No extension element found for OrgId in JSON from " + urlStr);
        }
        String orgId = orgRoot.get("extension").getAsString();
        ret.setOdsCode(orgId);

        if (!jsonRoot.has("Name")) {
            throw new Exception("No Name element found in JSON from " + urlStr);
        }
        String name = jsonRoot.get("Name").getAsString();
        ret.setOrganisationName(name);

        if (jsonRoot.has("GeoLoc")) {
            JsonObject geoLocRoot = (JsonObject) jsonRoot.get("GeoLoc");
            if (geoLocRoot.has("Location")) {
                JsonObject locRoot = (JsonObject) geoLocRoot.get("Location");

                if (locRoot.has("AddrLn1")) {
                    String str = locRoot.get("AddrLn1").getAsString();
                    ret.setAddressLine1(str);
                }
                if (locRoot.has("AddrLn2")) {
                    String str = locRoot.get("AddrLn2").getAsString();
                    ret.setAddressLine2(str);
                }
                if (locRoot.has("Town")) {
                    String str = locRoot.get("Town").getAsString();
                    ret.setTown(str);
                }
                if (locRoot.has("County")) {
                    String str = locRoot.get("County").getAsString();
                    ret.setCounty(str);
                }
                if (locRoot.has("PostCode")) {
                    String str = locRoot.get("PostCode").getAsString();
                    ret.setPostcode(str);
                }
            }
        }

        if (jsonRoot.has("Status")) {
            String statusStr = jsonRoot.get("Status").getAsString();
            ret.setActive(statusStr.equalsIgnoreCase("Active"));
        }

        if (jsonRoot.has("orgRecordClass")) {
            //only two classes are defined
            //https://digital.nhs.uk/services/organisation-data-service/guidance-for-developers/search-parameters#record-class

            String classStr = jsonRoot.get("orgRecordClass").getAsString();
            if (classStr.equalsIgnoreCase("RC1")) {
                ret.setOrganisationClass(OrganisationClass.HSC_ORG);

            } else if (classStr.equalsIgnoreCase("RC2")) {
                ret.setOrganisationClass(OrganisationClass.HSC_SITE);

            } else {
                throw new Exception("Unexpected orgRecordClass in " + urlStr);
            }
        }

        if (jsonRoot.has("Rels")) {
            JsonObject relsRoot = (JsonObject) jsonRoot.get("Rels");
            if (relsRoot.has("Rel")) {
                JsonArray relArray = (JsonArray) relsRoot.get("Rel");
                for (int i = 0; i < relArray.size(); i++) {
                    JsonObject relRoot = (JsonObject) relArray.get(i);

                    if (!relRoot.has("Status")) {
                        throw new Exception("Rel object has no Status element in " + urlStr);
                    }
                    String statusStr = relRoot.get("Status").getAsString();
                    if (!statusStr.equalsIgnoreCase("Active")) {
                        continue;
                    }

                    if (!relRoot.has("Target")) {
                        throw new Exception("Rel object has no Target element in " + urlStr);
                    }
                    JsonObject targetRoot = (JsonObject) relRoot.get("Target");
                    if (!targetRoot.has("OrgId")) {
                        throw new Exception("Rel.Target object has no OrgId element in " + urlStr);
                    }
                    JsonObject orgIdRoot = (JsonObject) targetRoot.get("OrgId");
                    if (!orgIdRoot.has("extension")) {
                        throw new Exception("Rel.Target.OrgId object has no extension element in " + urlStr);
                    }
                    String parentOrgId = orgIdRoot.get("extension").getAsString();

                    OdsOrganisation parentOrg = lookUpOrganisationViaRestNew(parentOrgId, proxy);
                    ret.getParents().put(parentOrgId, parentOrg);
                }
            }
        }

        if (jsonRoot.has("Roles")) {
            JsonObject rolesRoot = (JsonObject) jsonRoot.get("Roles");
            if (rolesRoot.has("Role")) {
                JsonArray roleArray = (JsonArray) rolesRoot.get("Role");

                for (int i = 0; i < roleArray.size(); i++) {
                    JsonObject roleRoot = (JsonObject) roleArray.get(i);

                    if (!roleRoot.has("Status")) {
                        throw new Exception("Role object has no Status element in " + urlStr);
                    }
                    String statusStr = roleRoot.get("Status").getAsString();
                    if (!statusStr.equalsIgnoreCase("Active")) {
                        continue;
                    }

                    if (!roleRoot.has("id")) {
                        throw new Exception("Role object has no id element in " + urlStr);
                    }
                    String roleId = roleRoot.get("id").getAsString();
                    OrganisationType orgType = OrganisationType.fromCode(roleId);
                    ret.getOrganisationTypes().add(orgType);
                }
            }
        }


        return ret;
    }


    private static OrganisationType getOrganisationType(String roleCode) {
        try {
            return OrganisationType.fromCode(roleCode);
        } catch (Exception e) {
            return null;
        }
    }
}
