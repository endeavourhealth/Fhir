package org.endeavourhealth.common.fhir;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.endeavourhealth.common.cache.ParserPool;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.model.Resource;

public class JsonHelper {
    public static String getPrettyJson(Resource resource) throws Exception {
        String json = ParserPool.getInstance().composeString(resource);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        com.google.gson.JsonParser jp = new com.google.gson.JsonParser();
        JsonElement je = jp.parse(json);
        return gson.toJson(je);
    }
}
