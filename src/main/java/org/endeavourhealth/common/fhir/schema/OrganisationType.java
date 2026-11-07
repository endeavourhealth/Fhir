package org.endeavourhealth.common.fhir.schema;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.endeavourhealth.common.fhir.FhirValueSetUri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganisationType {
    private static final Logger LOG = LoggerFactory.getLogger(OrganisationType.class);


    //roles can be looked up on the ODS API using
    // https://directory.spineservices.nhs.uk/ORD/2-0-0/roles/<rolecode>
    // e.g.
    // https://directory.spineservices.nhs.uk/ORD/2-0-0/roles/RO261
    private static final String SPINE_SERVICES_ORD_URL = "https://directory.spineservices.nhs.uk/ORD/2-0-0/roles";
    private static final String INFORMATION_MODEL_ORG_ROLE_URL = "https://im.endhealth.co.uk/imapi/api/ods/public/roles";

    //explicit codes that are needed
    public static final String CODE_PRESCRIBING_COST_CENTRE = "RO177";
    public static final String CODE_STRATEGIC_PARTNERSHIP = "RO261";
    public static final String CODE_FOUNDATION_TRUST = "RO57";
    public static final String CODE_HOSPICE = "HP";
    public static final String CODE_REGISTERED_UNDER_PART_2_CARE_STDS_ACT_2000 = "RO15";
    public static final String CODE_LEVEL_04_PCT = "RO22";
    public static final String CODE_EXTENDED_ACCESS_HUB = "RO267";
    public static final String CODE_EXTENDED_ACCESS_PROVIDER = "RO266";
    public static final String CODE_NHS_TRUST_DERIVED = "RO24";
    public static final String CODE_MEDICINE_SUPPLIER = "RO268";
    public static final String CODE_SPECIALISED_COMMISSIONING_HUB = "RO211";
    public static final String CODE_TREATMENT_CENTRE = "RO29";
    public static final String CODE_PCT_DERIVED = "RO25";
    public static final String CODE_OUT_OF_HOURS_COST_CENTRE = "RO80";
    public static final String CODE_COMMISSIONING_HUB = "RO218";
    public static final String CODE_NHS_TRUST_SITE = "SI";
    public static final String CODE_SOCIAL_CARE_SITE = "RO101";
    public static final String CODE_COVID_VACCINATION_CENTRE = "RO279";
    public static final String CODE_LOCAL_AUTHORITY = "EL";
    public static final String CODE_GP_PRACTICE = "PR";
    public static final String CODE_CCG = "CC";
    public static final String CODE_PCT = "PT";
    public static final String CODE_NHS_TRUST = "TR";
    public static final String CODE_PRIMARY_CARE_NETWORK = "PCN";
    public static final String CODE_SUSTAINABILITY_TRANSFORMATION_PARTNERSHIP = "RO262";
    public static final String CODE_GOVERNMENT_OFFICE_REGION = "GO";
    public static final String CODE_HEALTH_AUTHORITY = "HU";
    public static final String CODE_NHS_TRUST_SERVICE = "TS";
    static boolean attemptFailed = false;
    //static cache of all types, hard-coded plus ones from ODS web service
    private static List<OrganisationType> cachedTypeList;
    private static Map<String, OrganisationType> cachedTypeMap;

    //members
    private String odsRoleCode = null;
    private String ddsLegacyCode = null;
    private String description = null;



    private OrganisationType(String odsRoleCode, String ddsLegacyCode, String description) {
        this.odsRoleCode = odsRoleCode;
        this.ddsLegacyCode = ddsLegacyCode;
        this.description = description;
    }


    public String getOdsRoleCode() {
        return odsRoleCode;
    }

    public String getDdsLegacyCode() {
        return ddsLegacyCode;
    }

    private void setDdsLegacyCode(String ddsLegacyCode) {
        this.ddsLegacyCode = ddsLegacyCode;
    }

    public String getDescription() {
        return description;
    }

    public String getSystem() {
        return FhirValueSetUri.VALUE_SET_ORGANISATION_TYPE;
    }

    /**
     * because some org types have both legacy DDS and ODS role type codes, we use this fn
     * to get a single code. For backwards compatability reasons, it prefers a DDS code over ODS
     */
    public String getCode() {

        if (ddsLegacyCode != null) {
            return ddsLegacyCode;
        }

        if (odsRoleCode != null) {
            return odsRoleCode;
        }

        throw new RuntimeException("No suitable code for OrganisationType " + description);
    }

    @Override
    public String toString() {
        return this.description;
    }

    /**
     * looks up a type based on matching either the ODS role code or the legacy type code
     */
    public static OrganisationType fromCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        Map<String, OrganisationType> types = getTypesMap();

        OrganisationType t = types.get(code);
        if (t != null) {
            return t;
        }

        throw new IllegalArgumentException("Unknown organisation type for code [" + code + "]");
    }


    public static OrganisationType fromDescription(String description) {
        if (StringUtils.isBlank(description)) {
            return null;
        }

        List<OrganisationType> types = getTypesList();
        for (OrganisationType t : types) {
            if (t.getDescription().equalsIgnoreCase(description)) {
                return t;
            }
        }

        throw new IllegalArgumentException("Unknown organisation type with description [" + description + "]");
    }

    private static Map<String, OrganisationType> getTypesMap() {
        if (cachedTypeMap == null) {
            cacheTypes();
        }
        return cachedTypeMap;
    }

    public static List<OrganisationType> getTypesList() {
        if (cachedTypeList == null) {
            cacheTypes();
        }
        return cachedTypeList;
    }

    private static synchronized void cacheTypes() {

        //if multiple threads attempt to call this then the syncronized statement will cause them to queue
        //so check the static cache variables again
        if (cachedTypeMap != null
                || cachedTypeList != null) {
            return;
        }

        LOG.trace("Caching org types");

        //hit the ODS web service and get all the official types
        List<OrganisationType> odsTypes = lookUpOrganisationTypesViaRestImpl(null);

        //for the ODS types that were duplicated in legacy codes, set that legacy code on them
        //need to reverse the map first
        Map<String, String> legacyMap = getDdsLegacyToOdsMap();
        Map<String, String> odsMap = new HashMap<>();
        for (String legacyCode: legacyMap.keySet()) {
            String odsRoleCode = legacyMap.get(legacyCode);
            odsMap.put(odsRoleCode, legacyCode);
        }

        //set legacy code on any that needs it
        for (OrganisationType t: odsTypes) {
            String legacyCode = odsMap.get(t.getOdsRoleCode());
            if (legacyCode != null) {
                t.setDdsLegacyCode(legacyCode);
            }
        }

        //get the list of DDS hard-coded types. Some of these are also official ones, and others are non-standard
        List<OrganisationType> hardCodedTypes = createHardcodedTypes();

        List<OrganisationType> allTypes = new ArrayList<>();
        allTypes.addAll(odsTypes);
        allTypes.addAll(hardCodedTypes);

        Map<String, OrganisationType> allTypeMap = new HashMap<>();
        for (OrganisationType t: allTypes) {
            String legacyCode = t.getDdsLegacyCode();
            String odsRoleCode = t.getOdsRoleCode();

            if (legacyCode != null) {
                allTypeMap.put(legacyCode, t);
            }
            if (odsRoleCode != null) {
                allTypeMap.put(odsRoleCode, t);
            }
        }

        cachedTypeList = allTypes;
        cachedTypeMap = allTypeMap;
        LOG.trace("Cached " + allTypes.size() + " org types");
    }



    /**
     * there are some organisation types that are not official
     */
    private static List<OrganisationType> createHardcodedTypes() {

        List<OrganisationType> ret = new ArrayList<>();

        ret.add(new OrganisationType(null, "BM", "Booking Management System (BMS) Call Centre Establishment"));
        ret.add(new OrganisationType(null, "AA", "Abeyance and Dispersal GP Practice"));
        ret.add(new OrganisationType(null, "HA", "High Level Health Geography"));
        ret.add(new OrganisationType(null, "RO", "National Groupings"));
        ret.add(new OrganisationType(null, "TS", "NHS Trust Service"));
        ret.add(new OrganisationType(null, "NA", "Northern Ireland Health & Social Care Board"));
        ret.add(new OrganisationType(null, "NB", "Northern Ireland Health & Social Care Trust"));
        ret.add(new OrganisationType(null, "NC", "Northern Ireland Local Commissioning Group"));
        ret.add(new OrganisationType(null, "WH", "Welsh Health Commission"));
        ret.add(new OrganisationType(null, "CO", "Community"));
        ret.add(new OrganisationType(null, "CM", "Community Mental Health Team"));
        ret.add(new OrganisationType(null, "HO", "Hospital"));
        ret.add(new OrganisationType(null, "HD", "Hospital Department"));
        ret.add(new OrganisationType(null, "CI", "Clinic"));
        ret.add(new OrganisationType(null, "PV", "Private Clinic"));
        ret.add(new OrganisationType(null, "SH", "Strategic Health Authority"));
        ret.add(new OrganisationType(null, "PC", "Primary Care Organisation"));
        ret.add(new OrganisationType(null, "BR", "Branch Surgery"));
        ret.add(new OrganisationType(null, "OO", "Out of Hours"));
        ret.add(new OrganisationType(null, "NH", "Nursing Home"));
        ret.add(new OrganisationType(null, "AE", "Accident & Emergency"));

        return ret;
    }

    /**
     * there are a number of older DDS org types that have been superseded now the ODS types are
     * available, and this map converts one to the other
     */
    private static Map<String, String> getDdsLegacyToOdsMap() {

        Map<String, String> ret = new HashMap<>();

        ret.put("AR", "RO92"); //Application Service Provider
        ret.put("CN", "RO103"); //Cancer Network
        ret.put("CR", "RO105"); //Cancer Registry
        ret.put("CT", "RO107"); //Care Trust
        ret.put("CC", "RO98"); //Clinical Commissioning Group (CCG)
        ret.put("CL", "RO102"); //Clinical Network
        ret.put("CA", "RO213"); //Commissioning Support Unit (CSU)
        ret.put("JG", "RO235"); //Court
        ret.put("DD", "RO110"); //Dental Practice
        ret.put("ED", "RO117"); //Education Establishment
        ret.put("EA", "RO116"); //Executive Agency
        ret.put("AP", "RO91"); //Executive Agency Programme
        ret.put("GD", "RO126"); //Government Department
        ret.put("GO", "RO128"); //Government Office Region (GOR)
        ret.put("PR", "RO76"); //GP Practices in England and Wales
        ret.put("JD", "RO69"); //Immigration Removal Centre
        ret.put("PH", "RO172"); //Independent Sector Healthcare Provider (ISHP)
        ret.put("PS", "RO176"); //Independent Sector Healthcare Provider Site (ISHPS)
        ret.put("EL", "RO141"); //Local Authority
        ret.put("LO", "RO146"); //Local Service Provider (LSP)
        ret.put("MH", "RO150"); //Military Hospital
        ret.put("NP", "RO159"); //National Application Service Provider
        ret.put("NS", "RO161"); //NHS Support Agency
        ret.put("TR", "RO197"); //NHS Trust
        ret.put("SI", "RO198"); //NHS Trust Site
        ret.put("NN", "RO157"); //Non-NHS Organisation
        ret.put("OH", "RO166"); //Optical Headquarters
        ret.put("OA", "RO162"); //Other Statutory Authority (OSA)
        ret.put("PY", "RO182"); //Pharmacy
        ret.put("PX", "RO181"); //Pharmacy Headquarters
        ret.put("JE", "RO233"); //Police Constabulary
        ret.put("JF", "RO234"); //Police Custody Suite
        ret.put("PT", "RO179"); //Primary Care Trust
        ret.put("ID", "RO137"); //Primary Healthcare Directorate (Isle of Man)
        ret.put("PN", "RO175"); //Prison Health Service
        ret.put("EE", "RO117"); //School
        ret.put("JC", "RO231"); //Secure Children's Home
        ret.put("JB", "RO230"); //Secure Training Centre (STC)
        ret.put("JH", "RO236"); //Sexual Assault Referral Centre (SARC)
        ret.put("SA", "RO189"); //Special Health Authority (SpHA)
        ret.put("WA", "RO200"); //Welsh Assembly
        ret.put("LH", "RO144"); //Welsh Local Health Board
        ret.put("JA", "RO228"); //Young Offenders Institute
        ret.put("HP", "RO7"); //Hospice
        ret.put("HU", "RO132"); //Health Authority (HA)
        ret.put("PCN", "RO272"); //Primary Care Network

        return ret;
    }


    /**
     * the API fails with a 503 (Service Unavailable) or 504 (Gateway timeout) error every now and again. This function gives it
     * five goes with a 10s delay between each attempt before failing.
     */
    private static List<OrganisationType> lookUpOrganisationTypesViaRestImpl(Proxy proxy) {

        LOG.trace("Getting ODS org role types");
        int lives = 30; //the API was down for several minutes, so give it up to 5 min (with 10s retry delay) before failing

        while (true) {
            lives--;

            try {
                return tryLookUpOrganisationTypesViaRestImpl(proxy);

            } catch (Exception ex) {
                String msg = ex.getMessage();
                boolean is503 = msg != null
                        && (msg.contains("503") || msg.contains("504"));

                if (!is503 || lives <= 0) {
                    throw new RuntimeException("Failed to look up ODS organisation role types", ex);
                }

                LOG.warn("Exception " + ex.getMessage() + " calling into ODS web service will try " + lives + " more times");
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException ie) {
                    throw new RuntimeException("Sleep interrupted", ie);
                }
            }
        }
    }

    public static List<OrganisationType> tryLookUpOrganisationTypesViaRestImpl(Proxy proxy) throws Exception {

        //if no proxy, use the "no proxy" instance
        if (proxy == null) {
            proxy = Proxy.NO_PROXY;
        }
        URL url;
//        if (!attemptFailed) {
//             url = new URL(INFORMATION_MODEL_ORG_ROLE_URL);
//        } else {
        url = new URL(SPINE_SERVICES_ORD_URL);
        //}

        HttpURLConnection connection = (HttpURLConnection)url.openConnection(proxy);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {  // && attemptFailed) {
            return null;
        }
//        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND){
//            attemptFailed = true;
//           tryLookUpOrganisationTypesViaRestImpl(proxy);
//        }

        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP response " + responseCode + " returned for GET to " + url);
        }

        InputStream response = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(response);
        JsonParser parser = new JsonParser();
        JsonObject jsonRoot = (JsonObject) parser.parse(inputStreamReader);

        if (!jsonRoot.has("Roles")) {
            throw new Exception("No Roles element found in JSON from " + url);
        }

        List<OrganisationType> ret = new ArrayList<>();

        JsonArray rolesRoot = jsonRoot.getAsJsonArray("Roles");

        for (int i=0; i<rolesRoot.size(); i++) {
            JsonObject roleRoot = (JsonObject)rolesRoot.get(i);

            if (!roleRoot.has("id")) {
                throw new Exception("Role " + i + " is missing id element found in JSON from " + url);
            }
            if (!roleRoot.has("displayName")) {
                throw new Exception("Role " + i + " is missing displayName element found in JSON from " + url);
            }
            String id = roleRoot.get("id").getAsString();
            String desc = roleRoot.get("displayName").getAsString();

            OrganisationType t = new OrganisationType(id, null, desc);
            ret.add(t);
        }

        return ret;
    }


}
