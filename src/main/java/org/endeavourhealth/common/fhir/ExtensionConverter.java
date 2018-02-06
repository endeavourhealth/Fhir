package org.endeavourhealth.common.fhir;

import org.hl7.fhir.instance.model.*;

import java.util.Date;

public class ExtensionConverter {

    public static Extension createBooleanExtension(String uri, boolean value) {
        return createExtension(uri, new BooleanType(value));
    }

    public static Extension createStringExtension(String uri, String value) {
        return createExtension(uri, new StringType(value));
    }

    public static Extension createIntegerExtension(String uri, Integer value) {
        return createExtension(uri, new IntegerType(value));
    }

    public static Extension createDateTimeExtension(String uri, Date value) {
        return createExtension(uri, new DateTimeType(value));
    }

    public static Extension createExtension(String uri, Type value) {
        return new Extension()
                .setUrl(uri)
                .setValue(value);
    }

    public static Extension createCompoundExtension(String uri, Extension... subExtensions) {
        Extension e = new Extension()
                .setUrl(uri);

        for (Extension subExtension: subExtensions) {
            e.addExtension(subExtension);
        }

        return e;
    }

    public static boolean hasExtension(DomainResource resource, String extensionUrl) {
        return findExtension(resource, extensionUrl) != null;
    }

    public static Extension findExtension(DomainResource resource, String extensionUrl) {
        if (resource.hasExtension()) {
            for (Extension extension: resource.getExtension()) {
                if (extension.getUrl().equals(extensionUrl)) {
                    return extension;
                }
            }
        }
        return null;
    }

    public static Extension findOrCreateExtension(DomainResource resource, String extensionUrl) {
        Extension ret = findExtension(resource, extensionUrl);
        if (ret == null) {
            ret = new Extension();
            ret.setUrl(extensionUrl);
            resource.getExtension().add(ret);
        }
        return ret;
    }
}
