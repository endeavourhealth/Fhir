package org.endeavourhealth.common.ods;


import com.google.common.base.Strings;
import com.google.gson.*;
import org.apache.commons.lang3.Validate;
import org.endeavourhealth.common.fhir.schema.OrganisationClass;
import org.endeavourhealth.common.fhir.schema.OrganisationType;
import org.endeavourhealth.common.utility.ExpiringCache;
import org.hl7.fhir.instance.model.Organization;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OdsWebService {

    //private static final String OPEN_ODS_URL = "http://api.openods.co.uk/api/organisations/";

    //private static final String SPINE_SERVICES_FHIR_URL = "https://directory.spineservices.nhs.uk/STU3/Organization/";
    private static final String SPINE_SERVICES_ORD_URL = "https://directory.spineservices.nhs.uk/ORD/2-0-0/organisations/";

    private static ExpiringCache<String, OdsOrganisation> ordCache = new ExpiringCache<>(60 * 60 * 1000); //cache for an hour

    public static OdsOrganisation lookupOrganisationViaRest(String odsCode) throws Exception {
        return lookUpOrganisationViaRestNew(odsCode, null);
    }

    public static OdsOrganisation lookupOrganisationViaRest(String odsCode, Proxy proxy) throws Exception {
        return lookUpOrganisationViaRestNew(odsCode, proxy);
    }


    /*private static OdsOrganisation lookupOrganisationViaRestOld(String odsCode) throws Exception {
        return lookupOrganisationViaRestOld(odsCode, null);
    }

    private static OdsOrganisation lookupOrganisationViaRestOld(String odsCode, Proxy proxy) throws Exception {

        Validate.notEmpty(odsCode);

        //if no proxy, use the "no proxy" instance
        if (proxy == null) {
            proxy = Proxy.NO_PROXY;
        }
        odsCode = odsCode.replace(" ", "%20");
        String urlStr = OPEN_ODS_URL + odsCode;
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection(proxy);
        //connection.setRequestProperty("Accept-Charset", charset);

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
        JsonObject jsonRoot = (JsonObject)parser.parse(inputStreamReader);

        OdsOrganisation ret = new OdsOrganisation();

        String name = jsonRoot.get("name").getAsString();
        ret.setOrganisationName(name);

        String ods = jsonRoot.get("odsCode").getAsString();
        ret.setOdsCode(ods);

        String cls = jsonRoot.get("recordClass").getAsString();
        ret.setOrganisationClass(OrganisationClass.fromOrganisationClassName(cls));

        String activeState = jsonRoot.get("status").getAsString();
        ret.setActive(activeState.equalsIgnoreCase("Active"));

        JsonArray addresses = (JsonArray)jsonRoot.get("addresses");
        if (addresses.size() > 0) {
            JsonObject addressRoot = (JsonObject)addresses.get(0);

            JsonArray addressLines = (JsonArray)addressRoot.get("addressLines");
            if (addressLines != null
                    && addressLines.size() > 0) {

                String line1 = addressLines.get(0).getAsString();
                ret.setAddressLine1(line1);

                if (addressLines.size() > 1) {
                    String line2 = addressLines.get(1).getAsString();
                    ret.setAddressLine2(line2);
                }
            }

            if (addressRoot.has("town")) {
                String town = addressRoot.get("town").getAsString();
                ret.setTown(town);
            }

            if (addressRoot.has("county")) {
                String county = addressRoot.get("county").getAsString();
                ret.setCounty(county);
            }

            if (addressRoot.has("postCode")) {
                String postcode = addressRoot.get("postCode").getAsString();
                ret.setPostcode(postcode);
            }
        }

        JsonArray roles = (JsonArray)jsonRoot.get("roles");
        if (roles.size() > 0) {

            //for GP practices the primary role code is "PRESCRIBING COST CENTRE", so look at all the role
            //codes and use the first one that maps to one of the options in our list of org types
            List<String> roleCodes = new ArrayList<>();

            for (int i=0; i<roles.size(); i++) {
                JsonObject roleRoot = (JsonObject)roles.get(i);

                String roleStatus = roleRoot.get("status").getAsString();
                boolean isPrimaryRole = roleRoot.get("primaryRole").getAsBoolean();
                String roleCode = roleRoot.get("code").getAsString();

                //ignore any non-active roles
                if (!roleStatus.equalsIgnoreCase("Active")) {
                    continue;
                }

                //if primary code, insert at the start of the list
                if (isPrimaryRole
                        && !roleCodes.isEmpty()) {
                    roleCodes.add(0, roleCode);

                } else {
                    roleCodes.add(roleCode);
                }
            }

            for (String roleCode: roleCodes) {
                OrganisationType type = getOrganisationType(roleCode);
                if (type != null) {
                    ret.setOrganisationType(type);
                    break;
                }
            }
        }

        JsonArray parents = (JsonArray)jsonRoot.get("relationships");
        if (parents.size() > 0) {
            for (int i=0; i<parents.size(); i++) {
                JsonObject parentRoot = (JsonObject)parents.get(i);

                String parentStatus = parentRoot.get("status").getAsString();
                String parentOdsCode = parentRoot.get("relatedOdsCode").getAsString();
                String parentName = parentRoot.get("relatedOrganisationName").getAsString();

                //ignore any non-active parents
                if (!parentStatus.equalsIgnoreCase("Active")) {
                    continue;
                }

                ret.getParents().put(parentOdsCode, parentName);
            }
        }

        inputStreamReader.close();

        if (Strings.isNullOrEmpty(ret.getOdsCode())) {
            throw new IOException("Returned ODS code is empty");
        }

        if (Strings.isNullOrEmpty(ret.getOrganisationName())) {
            throw new IOException("Returned organisation name is empty");
        }

        return ret;
    }*/

    private static OdsOrganisation lookUpOrganisationViaRestNew(String odsCode) throws Exception {
        return lookUpOrganisationViaRestNew(odsCode, null);
    }

    private static OdsOrganisation lookUpOrganisationViaRestNew(String odsCode, Proxy proxy) throws Exception {
        Validate.notEmpty(odsCode);

        OdsOrganisation ret = ordCache.get(odsCode);
        if (ret == null) {
            ret = lookUpOrganisationViaRestNewImpl(odsCode, proxy);
            if (ret != null) {
                ordCache.put(odsCode, ret);
            }
        }

        return ret;
    }


    private static OdsOrganisation lookUpOrganisationViaRestNewImpl(String odsCode, Proxy proxy) throws Exception {

        //if no proxy, use the "no proxy" instance
        if (proxy == null) {
            proxy = Proxy.NO_PROXY;
        }

        odsCode = odsCode.replace(" ", "%20");
        String urlStr = SPINE_SERVICES_ORD_URL + odsCode;
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
        //connection.setRequestProperty("Accept-Charset", charset);

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
        JsonObject orgRoot = (JsonObject)jsonRoot.get("OrgId");
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
            JsonObject geoLocRoot = (JsonObject)jsonRoot.get("GeoLoc");
            if (geoLocRoot.has("Location")) {
                JsonObject locRoot = (JsonObject)geoLocRoot.get("Location");

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
            JsonObject relsRoot = (JsonObject)jsonRoot.get("Rels");
            if (relsRoot.has("Rel")) {
                JsonArray relArray = (JsonArray)relsRoot.get("Rel");
                for (int i=0; i<relArray.size(); i++) {
                    JsonObject relRoot = (JsonObject)relArray.get(i);

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
                    JsonObject targetRoot = (JsonObject)relRoot.get("Target");
                    if (!targetRoot.has("OrgId")) {
                        throw new Exception("Rel.Target object has no OrgId element in " + urlStr);
                    }
                    JsonObject orgIdRoot = (JsonObject)targetRoot.get("OrgId");
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
            JsonObject rolesRoot = (JsonObject)jsonRoot.get("Roles");
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
                    OrganisationType orgType = OrganisationType.fromRoleCode(roleId);
                    ret.getOrganisationTypes().add(orgType);
                }
            }
        }


        return ret;
    }


    /*public static OdsOrganisation lookupOrganisationViaRest_2(String odsCode, Proxy proxy) throws Exception {

        Validate.notEmpty(odsCode);

        //if no proxy, use the "no proxy" instance
        if (proxy == null) {
            proxy = Proxy.NO_PROXY;
        }
        odsCode = odsCode.replace(" ", "%20");
        String urlStr = SPINE_SERVICES_FHIR_URL + odsCode;
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
        //connection.setRequestProperty("Accept-Charset", charset);

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

        if (!jsonRoot.has("id")) {
            throw new Exception("Missing id element in " + urlStr);
        }
        String id = jsonRoot.get("id").getAsString();
        ret.setOdsCode(id);

        if (!jsonRoot.has("name")) {
            throw new Exception("Missing name element in " + urlStr);
        }
        String name = jsonRoot.get("name").getAsString();
        ret.setOrganisationName(name);

        if (jsonRoot.has("active")) {
            String activeState = jsonRoot.get("active").getAsString();
            ret.setActive(activeState.equalsIgnoreCase("true"));
        }

        if (jsonRoot.has("type")) {
            JsonObject typeRoot = (JsonObject)jsonRoot.get("type");
            if (typeRoot.has("coding")) {
                JsonObject codingRoot = (JsonObject)jsonRoot.get("coding");
                if (codingRoot.has("display")) {
                    String classStr = codingRoot.get("display").getAsString();
                    OrganisationClass classObj = OrganisationClass.fromOrganisationClassName(classStr);
                    ret.setOrganisationClass(classObj);
                }
            }
        }

        if (jsonRoot.has("address")) {
            JsonObject address = (JsonObject)jsonRoot.get("address");

            if (address.has("line")) {
                JsonArray lines = (JsonArray) address.getAsJsonArray("line");
                for (int i=0; i<lines.size(); i++) {
                    String line = lines.get(i).getAsString();
                    if (i == 0) {
                        ret.setAddressLine1(line);
                    } else if (i == 1) {
                        ret.setAddressLine2(line);
                    } else {
                        //ignore
                    }
                }
            }

            if (address.has("city")) {
                String town = address.get("city").getAsString();
                ret.setTown(town);
            }

            if (address.has("district")) {
                String county = address.get("district").getAsString();
                ret.setCounty(county);
            }

            if (address.has("postalCode")) {
                String postcode = address.get("postalCode").getAsString();
                ret.setPostcode(postcode);
            }
        }




        String cls = jsonRoot.get("type").getAsJsonObject().getAsJsonObject("coding").get("display").getAsString();
        ret.setOrganisationClass(OrganisationClass.fromOrganisationClassName(cls));

        String cls2 = jsonRoot.get("type").getAsJsonObject().getAsJsonObject("coding").get("display").getAsString();

        JsonArray extensions = jsonRoot.getAsJsonArray("extension");
        for (int i = 0; i < extensions.size(); i++) {
            JsonElement child = extensions.get(i);
            JsonObject obj = child.getAsJsonObject();
            JsonElement childCode = obj.get("url");
            if (childCode.getAsString().equals("https://fhir.nhs.uk/STU3/StructureDefinition/Extension-ODSAPI-OrganizationRole-1")) {
                JsonArray array = obj.get("extension").getAsJsonArray();
                for (int j = 0; j < array.size(); j++) {
                    JsonElement child2 = extensions.get(i);
                    JsonObject obj2 = child2.getAsJsonObject();
                    JsonElement childCode2 = obj.get("url");
                }
            }

        }

        JsonArray roles = (JsonArray) jsonRoot.get("roles");

        JsonArray parents = (JsonArray) jsonRoot.get("relationships");
        if (parents != null && parents.size() > 0) {
            for (int i = 0; i < parents.size(); i++) {
                JsonObject parentRoot = (JsonObject) parents.get(i);

                String parentStatus = parentRoot.get("status").getAsString();
                String parentOdsCode = parentRoot.get("relatedOdsCode").getAsString();
                String parentName = parentRoot.get("relatedOrganisationName").getAsString();

                //ignore any non-active parents
                if (!parentStatus.equalsIgnoreCase("Active")) {
                    continue;
                }

                ret.getParents().put(parentOdsCode, parentName);
            }
        }

        inputStreamReader.close();

        return ret;
    }*/


    private static OrganisationType getOrganisationType(String roleCode) {
        try {
            return OrganisationType.fromRoleCode(roleCode);
        } catch (Exception e) {
            return null;
        }
    }


}


