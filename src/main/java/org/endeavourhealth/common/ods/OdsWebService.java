package org.endeavourhealth.common.ods;


import com.google.common.base.Strings;
import com.google.gson.*;
import org.apache.commons.lang3.Validate;
import org.endeavourhealth.common.fhir.schema.OrganisationClass;
import org.endeavourhealth.common.fhir.schema.OrganisationType;
import org.hl7.fhir.instance.model.Organization;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OdsWebService {

    // needs rewiring to Stu's ODS REST lookup, using openods.co.uk for now
    // private static final String ORGANISATAION_REST_URL = "http://test.openods.co.uk/api/organisations/";
    // The endpoint seems to have changed
    private static final String ORGANISATAION_REST_URL = "http://api.openods.co.uk/api/organisations/";
    private static final String ORGANISATION_REST_URL = "https://directory.spineservices.nhs.uk/STU3/Organization/";
    private  static  final  String ORGANISATION_REST_URL_ODS = "https://directory.spineservices.nhs.uk/ORD/2-0-0/organisations/";



    public static OdsOrganisation lookupOrganisationViaRest(String odsCode) throws Exception {
        return lookupOrganisationViaRest(odsCode, null);
    }

    public static OdsOrganisation lookUpOrganisationViaRestOds(String odsCode, Proxy proxy) throws IOException {
        Validate.notEmpty(odsCode);

        //if no proxy, use the "no proxy" instance
        if (proxy == null) {
            proxy = Proxy.NO_PROXY;
        }
        odsCode = odsCode.replace(" ", "%20");
        String urlStr = ORGANISATION_REST_URL_ODS + odsCode;
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
        Gson gson = new Gson();
        Organization org = gson.fromJson(inputStreamReader, Organization.class);
        System.out.println("RAB>>>>:"  + jsonRoot);
        JsonObject orgRoot = jsonRoot.getAsJsonObject("Organisation");
        String name  = orgRoot.getAsJsonPrimitive("Name").getAsString();
        ret.setOrganisationName(name);
        System.out.println("RAB>>>>: name: "  + name);
        JsonObject orgId = orgRoot.getAsJsonObject("OrgId");
        JsonObject loc = orgRoot.getAsJsonObject("GeoLoc").getAsJsonObject("Location");
        String addLin1 = loc.getAsJsonPrimitive("AddrLn1").getAsString();
        ret.setAddressLine1(addLin1);

        String addLin2 = loc.getAsJsonPrimitive("AddrLn2").getAsString();
        ret.setAddressLine2(addLin2);
        String addLin3 = loc.getAsJsonPrimitive("AddrLn3").getAsString();
        ret.setAddressLine2(addLin3);
        String town = loc.getAsJsonPrimitive("Town").getAsString();
        ret.setTown(town);
        String county = loc.getAsJsonPrimitive("County").getAsString();
        ret.setCounty(county);
        String postCode = loc.getAsJsonPrimitive("PostCode").getAsString();
        ret.setPostcode(postCode);
        String country = loc.getAsJsonPrimitive("Country").getAsString();
        ret.setCounty(country);

        JsonArray role = orgRoot.getAsJsonObject("Roles").getAsJsonArray("Role");
        if (role.size() > 0) {
            boolean active = role.get(0).getAsJsonObject().getAsJsonPrimitive("Status").getAsString().equalsIgnoreCase("Active");
            System.out.println("Active is : " + active);
            boolean primaryRole = role.get(0).getAsJsonObject().getAsJsonPrimitive("primaryRole").getAsBoolean();
            if (primaryRole) {
                String roleId = role.get(0).getAsJsonObject().getAsJsonPrimitive("uniqueRoleId").getAsString();
            }
        }

        //System.out.println("RAB>>>>:"  + org.getName());


        return ret;
    }


    public static OdsOrganisation lookupOrganisationViaRest_2(String odsCode, Proxy proxy) throws Exception {

        Validate.notEmpty(odsCode);

        //if no proxy, use the "no proxy" instance
        if (proxy == null) {
            proxy = Proxy.NO_PROXY;
        }
        odsCode = odsCode.replace(" ", "%20");
        String urlStr = ORGANISATION_REST_URL + odsCode;
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
        String cls = jsonRoot.get("type").getAsJsonObject().getAsJsonObject("coding").get("display").getAsString();
        ret.setOrganisationClass(OrganisationClass.fromOrganisationClassName(cls));
        String name = jsonRoot.get("name").getAsString();
        ret.setOrganisationName(name);
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
        String activeState = jsonRoot.get("active").getAsString();
        ret.setActive(activeState.equalsIgnoreCase("true"));

        JsonObject address = (JsonObject) jsonRoot.get("address");
        JsonArray addresses = (JsonArray) address.getAsJsonArray("line");
        if (addresses != null && addresses.size() > 0) {
            String line1 = addresses.get(0).getAsString();
            ret.setAddressLine1(line1);
            if (addresses.size() > 1) {
                String line2 = addresses.get(1).getAsString();
                ret.setAddressLine2(line2);
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
    }


    private static OrganisationType getOrganisationType(String roleCode) {
        try {
            return OrganisationType.fromRoleCode(roleCode);
        } catch (Exception e) {
            return null;
        }
    }
    public static OdsOrganisation lookupOrganisationViaRest(String odsCode, Proxy proxy) throws Exception {

        Validate.notEmpty(odsCode);

        //if no proxy, use the "no proxy" instance
        if (proxy == null) {
            proxy = Proxy.NO_PROXY;
        }
        odsCode = odsCode.replace(" ", "%20");
        String urlStr = ORGANISATAION_REST_URL + odsCode;
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
    }

}


