package org.endeavourhealth.common.fhir;

import org.hl7.fhir.instance.model.*;
import org.hl7.fhir.instance.model.api.IBaseExtension;
import org.hl7.fhir.instance.model.api.IBaseHasExtensions;

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

    public static Extension createPositiveIntExtension(String uri, Integer value) {
        return createExtension(uri, new PositiveIntType(value));
    }

    public static Extension createDecimalExtension(String uri, Double value) {
        return createExtension(uri, new DecimalType(value));
    }

    public static Extension createOrUpdateBooleanExtension(IBaseHasExtensions resource, String uri, boolean value) {
        return createOrUpdateExtension(resource, uri, new BooleanType(value));
    }

    public static Extension createOrUpdateStringExtension(IBaseHasExtensions resource, String uri, String value) {
        return createOrUpdateExtension(resource, uri, new StringType(value));
    }

    public static Extension createOrUpdateIntegerExtension(IBaseHasExtensions resource, String uri, Integer value) {
        return createOrUpdateExtension(resource, uri, new IntegerType(value));
    }

    public static Extension createOrUpdateDateTimeExtension(IBaseHasExtensions resource, String uri, Date value) {
        return createOrUpdateExtension(resource, uri, new DateTimeType(value));
    }

    public static Extension createOrUpdatePositiveIntExtension(IBaseHasExtensions resource, String uri, Integer value) {
        return createOrUpdateExtension(resource, uri, new PositiveIntType(value));
    }

    public static Extension createOrUpdateDecimalExtension(IBaseHasExtensions resource, String uri, Double value) {
        return createOrUpdateExtension(resource, uri, new DecimalType(value));
    }

    public static Extension createOrUpdateExtension(IBaseHasExtensions resource, String uri, Type value) {
        Extension extension = findOrCreateExtension(resource, uri);
        extension.setValue(value);
        return extension;
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

    public static boolean hasExtension(IBaseHasExtensions resource, String extensionUrl) {
        return findExtension(resource, extensionUrl) != null;
    }

    public static Extension findExtension(IBaseHasExtensions resource, String extensionUrl) {
        for (IBaseExtension extension: resource.getExtension()) {
            if (extension.getUrl().equals(extensionUrl)) {
                return (Extension)extension;
            }
        }

        return null;
    }

    public static Type findExtensionValue(IBaseHasExtensions resource, String extensionUrl) {
        Extension extension = findExtension(resource, extensionUrl);
        if (extension != null) {
            return extension.getValue();
        } else {
            return null;
        }
    }

    public static DateType findExtensionValueDate(IBaseHasExtensions resource, String extensionUrl) {
        return (DateType)findExtensionValue(resource, extensionUrl);
    }

    public static CodeableConcept findExtensionValueCodeableConcept(IBaseHasExtensions resource, String extensionUrl) {
        return (CodeableConcept)findExtensionValue(resource, extensionUrl);
    }

    public static Extension findOrCreateExtension(IBaseHasExtensions resource, String extensionUrl) {
        Extension ret = findExtension(resource, extensionUrl);
        if (ret == null) {
            ret = (Extension)resource.addExtension();
            ret.setUrl(extensionUrl);
        }
        return ret;
    }

    public static Extension removeExtension(IBaseHasExtensions resource, String extensionUrl) {
        for (int i=0; i<resource.getExtension().size(); i++) {
            IBaseExtension extension = resource.getExtension().get(i);
            if (extension.getUrl().equals(extensionUrl)) {
                resource.getExtension().remove(i);
                return (Extension)extension;
            }
        }

        return null;
    }

    /**
     * sometimes we need to artificially change a FHIR resource so that it can be sent through
     * the queues again. The FHIR Resource Filer explicitly stops this happening if a resource
     * hasn't actually changed. So this function uses a dummy extension to create an artificial
     * change to bypass that.
     */
    public static void setResourceChanged(IBaseHasExtensions resource) {
        Extension ext = findOrCreateExtension(resource, FhirExtensionUri.RESOURCE_CHANGED);

        DecimalType val = new DecimalType(1);
        if (ext.hasValue()) {
            DecimalType currentVal = (DecimalType)ext.getValue();
            int valInt = currentVal.getValueAsInteger();
            val = new DecimalType(valInt + 1);
        }

        ext.setValue(val);
    }
}
