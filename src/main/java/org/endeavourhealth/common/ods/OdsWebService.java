package org.endeavourhealth.common.ods;

import com.google.common.base.Strings;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.endeavourhealth.common.fhir.schema.OrganisationClass;
import org.endeavourhealth.common.fhir.schema.OrganisationType;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OdsWebService {

    // needs rewiring to Stu's ODS REST lookup, using openods.co.uk for now
    // private static final String ORGANISATAION_REST_URL = "http://test.openods.co.uk/api/organisations/";
    // The endpoint seems to have changed
    private static final String ORGANISATAION_REST_URL = "http://api.openods.co.uk/api/organisations/";

    public static OdsOrganisation lookupOrganisationViaRest(String odsCode) throws Exception {
        Validate.notEmpty(odsCode);

        String urlStr = ORGANISATAION_REST_URL + odsCode;
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
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
            if (addressLines.size() > 0) {

                String line1 = addressLines.get(0).getAsString();
                ret.setAddressLine1(line1);

                String line2 = addressLines.get(1).getAsString();
                ret.setAddressLine2(line2);
            }

            String town = addressRoot.get("town").getAsString();
            ret.setTown(town);

            String county = addressRoot.get("county").getAsString();
            ret.setCounty(county);

            String postcode = addressRoot.get("postCode").getAsString();
            ret.setPostcode(postcode);
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


    private static OrganisationType getOrganisationType(String roleCode) {
        try {
            return OrganisationType.fromRoleCode(roleCode);
        } catch (Exception e) {
            return null;
        }
    }
}


