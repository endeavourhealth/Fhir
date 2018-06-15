package org.endeavourhealth.common.fhir.schema;

import org.endeavourhealth.common.fhir.FhirValueSetUri;

public enum LocationPhysicalType {

    AMBULATORY("am", "Ambulatory"),
    AREA("area", "Area"),
    BED("bd", "Bed"),
    BUILDING("bu", "Building"),
    CABINET("ca", "Cabinet"),
    CORRIDOR("co", "Corridor"),
    FACILITY("fa", "Facility"),
    JURISDICTION("jdn", "Jurisdiction"),
    HOUSE("ho", "House"),
    LEVEL("lvl", "Level"),
    NURSEUNIT("nu", "Nurseunit"),
    ROAD("rd", "Road"),
    ROOM("ro", "Room"),
    VEHICLE("ve", "Vehicle"),
    WING("wi", "Wing"),
    SURGICAL("sg", "Surgical");


    private String code = null;
    private String description = null;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getSystem() {
        return FhirValueSetUri.VALUE_SET_PHYSICAL_LOCATION_TYPE;
    }

    LocationPhysicalType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static LocationPhysicalType fromCode(String v) {
        for (LocationPhysicalType c: LocationPhysicalType.values()) {
            if (c.getCode().equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown code [" + v + "]");
    }
}
