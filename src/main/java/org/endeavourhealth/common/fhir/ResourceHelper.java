package org.endeavourhealth.common.fhir;

import org.hl7.fhir.instance.model.Resource;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceHelper {

    public static <T extends Resource> String findResourceId(Class<T> type, List<Resource> resources) throws FhirResourceException {
        T resource = ResourceHelper.findResourceOfType(type, resources);
        if (resource != null) {
            return resource.getId();
        } else {
            return null;
        }
    }

    public static <T extends Resource> T findResourceOfType(Class<T> type, List<Resource> resources) throws FhirResourceException {
        List<T> filteredResources = resources
                .stream()
                .filter(t -> t.getClass().equals(type))
                .map(t -> (T)t)
                .collect(Collectors.toList());
        if (filteredResources.size() > 1) {
            throw new FhirResourceException("Expected 1 " + type + " but found multiple");
        } else if (filteredResources.size() == 1) {
            return filteredResources.get(0);
        } else {
            return null;
        }
    }
}
