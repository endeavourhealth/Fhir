package org.endeavourhealth.common.fhir;

import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReferenceHelper {

    private static final String INTERNAL_REFERENCE_PREFIX = "#";

    public static String createResourceReference(ResourceType resourceType, String id) {
        return createResourceReference(resourceType.toString(), id);
    }

    public static String createResourceReference(String resourceType, String id) {
        return resourceType + "/" + id;
    }

    public static Reference createReference(ResourceType resourceType, String id) {
        return createReference(resourceType.toString(), id);
    }

    public static Reference createReference(String resourceType, String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("Blank id when creating reference for " + resourceType);
        }

        return createReference(createResourceReference(resourceType, id));
    }

    public static Reference createInternalReference(String id) {
        return createReference(INTERNAL_REFERENCE_PREFIX + id);
    }

    public static Reference createReference(String referenceValue) {
        return new Reference().setReference(referenceValue);
    }

    public static List<Reference> createReferences(List<String> referenceValues) {
        List<Reference> ret = new ArrayList<>();
        for (String referenceValue: referenceValues) {
            ret.add(createReference(referenceValue));
        }
        return ret;
    }


    public static Reference createReferenceExternal(Resource resource) {
        return createReference(resource.getResourceType(), resource.getId());
    }

    public static String getReferenceId(Reference reference) {
        return getReferenceId(reference, null);
    }

    public static String getReferenceId(Reference reference, ResourceType resourceType) {
        ReferenceComponents referenceComponents = getReferenceComponents(reference);

        if (referenceComponents == null)
            return null;

        //if a null resource type is passed in, then it shouldn't check it at all
        if (resourceType != null
            && !referenceComponents.getResourceType().equals(resourceType)) {
            return null;
        }

        /*if (!referenceComponents.getResourceType().equals(resourceType))
            return null;*/

        return referenceComponents.getId();
    }

    public static ReferenceComponents getReferenceComponents(Reference reference) {
        if (reference == null) {
            return null;
        }

        if (!reference.hasReference()) {
            return null;
        }

        if (reference.getReference().startsWith(INTERNAL_REFERENCE_PREFIX)) {
            return null;
        }

        String[] parts = reference.getReference().split("\\/");

        if ((parts == null) || (parts.length == 0))
            return null;

        if (parts.length != 2)
            throw new IllegalArgumentException("Invalid reference string: "+reference.getReference());

        ResourceType resourceType = ResourceType.valueOf(parts[0]);
        return new ReferenceComponents(resourceType, parts[1]);
    }

    public static boolean isResourceType(Reference reference, ResourceType resourceType) {
        ReferenceComponents referenceComponents = getReferenceComponents(reference);

        if (referenceComponents == null)
            return false;

        return referenceComponents.getResourceType().equals(resourceType);
    }

    public static ResourceType getResourceType(Reference reference) {
        ReferenceComponents comps = getReferenceComponents(reference);
        return comps.getResourceType();
    }

    public static <T extends Resource> Reference findAndCreateReference(Class<T> type, List<Resource> resources) throws FhirResourceException {
        T resource = ResourceHelper.findResourceOfType(type, resources);
        if (resource != null) {
            return createReferenceExternal(resource);
        } else {
            return null;
        }
    }


    /**
     * the Reference class doesn't override the equals(..) function, so we have these utility functions for doing what
     * should be otherwise easy
     */
    public static boolean equals(Reference reference1, Reference reference2) {
        return reference1.getReference().equals(reference2.getReference());
    }

    public static boolean contains(List<Reference> references, Reference reference) {
        for (Reference listReference: references) {
            if (equals(listReference, reference)) {
                return true;
            }
        }
        return false;
    }

    public static void remove(List<Reference> references, Reference reference) {
        for (int i=0; i<references.size(); i++) {
            Reference listReference = references.get(i);
            if (equals(listReference, reference)) {
                references.remove(i);
                return;
            }
        }
    }

    /**
     * removes any duplicate references from a list, handling the fact that Reference doesn't
     * implement the hashCode or equals functions
     */
    public static void removeDuplicates(List<Reference> references) {

        Set<String> tmpFound = new HashSet<>();
        for (int i=references.size()-1; i>=0; i--) {
            Reference ref = references.get(i);
            String refValue = ref.getReference();
            if (tmpFound.contains(refValue)) {
                references.remove(i);
            } else {
                tmpFound.add(refValue);
            }
        }
    }

}
