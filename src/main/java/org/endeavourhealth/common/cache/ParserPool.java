package org.endeavourhealth.common.cache;

import org.hl7.fhir.instance.formats.IParser;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Type;

import java.util.Stack;

public class ParserPool implements ICache {
    private static ParserPool instance;

    public static synchronized ParserPool getInstance() {
        if (instance == null) {
            instance = new ParserPool();
            CacheManager.registerCache(instance);
        }
        return instance;
    }

    private final Stack<IParser> jsonPool = new Stack<>();
    private final Stack<IParser> xmlPool = new Stack<>();

    @Override
    public String getName() {
        return "ParserPool";
    }

    @Override
    public long getSize() {
        return jsonPool.size() + xmlPool.size();
    }

    @Override
    public synchronized void clearCache() {
        jsonPool.clear();
        xmlPool.clear();
    }

    public String composeString(Resource resource) throws Exception {
        return this.composeString(null, resource);
    }

    public String composeString(String contentType, Resource resource) throws Exception {
        IParser parser = pop(contentType);
        try {
            return parser.composeString(resource);
        } finally {
            push(parser);
        }
    }

    public String composeString(Type type, String contentType) throws Exception {
        IParser parser = pop(contentType);
        try {
            return parser.composeString(type, contentType);
        } finally {
            push(parser);
        }
    }

    public Resource parse(String data) throws Exception {
        return this.parse(null, data);
    }

    public Resource parse(String contentType, String data) throws Exception {
        IParser parser = pop(contentType);
        try {
            return parser.parse(data);
        } finally {
            push(parser);
        }
    }

    public Type parseType(String json, String contentType) throws Exception {
        IParser parser = pop(contentType);
        try {
            return parser.parseType(json, contentType);
        } finally {
            push(parser);
        }
    }

    private IParser pop(String contentType) {
        if (contentType == null || contentType.isEmpty())
            return jsonPop();

        if ("text/xml".equals(contentType) || "application/xml".equals(contentType))
            return xmlPop();

        return jsonPop();
    }

    private synchronized void push(IParser parser) {
        if (parser instanceof JsonParser) {
            //if it's one of our sub-classes, call the function to clear down the useless idMap varible which causes memory leaks
            if (parser instanceof JsonParserCustom) {
                ((JsonParserCustom)parser).clearIdMap();
            }
            jsonPool.push(parser);

        } else {
            xmlPool.push(parser);
        }
    }

    private synchronized IParser jsonPop() {
        if (jsonPool.size() > 0) {
            return jsonPool.pop();

        } else {
            return new JsonParserCustom();
            //return new JsonParser();
        }
    }

    private synchronized IParser xmlPop() {
        if (xmlPool.size() > 0)
            return xmlPool.pop();
        else
            return new XmlParser();
    }


    /**
     * the JsonParser class has a bug in that it builds up a map called idMap which is never
     * used and never cleared, so as a parser is used more and more, it will grow, causing a memory leak
     * So this sub-class simply provides a function to allow clearing that map.
     */
    class JsonParserCustom extends JsonParser {

        public void clearIdMap() {
            this.idMap.clear();
        }
    }
}
