package org.endeavourhealth.common.fhir;

public class FhirResourceException extends Exception {
    public FhirResourceException() {
        super();
    }
    public FhirResourceException(String message) {
        super(message);
    }
}
