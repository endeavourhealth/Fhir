package org.endeavourhealth.common.fhir;

import org.hl7.fhir.instance.model.Location;
import org.hl7.fhir.instance.model.Resource;

import java.util.List;
import java.util.Optional;

public class LocationHelper {

    public static Location findLocationForName(List<Resource> fhirResources, String name) throws FhirResourceException {

        Optional<Location> ret = fhirResources
                .stream()
                .filter(t -> t instanceof Location && ((Location)t).getName().equals(name))
                .map(t -> (Location)t)
                .findFirst();

        if (!ret.isPresent()) {
            throw new FhirResourceException("Failed to find location for name " + name);
        }

        return ret.get();
    }
}
