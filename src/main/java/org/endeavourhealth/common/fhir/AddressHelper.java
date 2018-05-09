package org.endeavourhealth.common.fhir;

import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.instance.model.Address;
import org.hl7.fhir.instance.model.Patient;
import org.hl7.fhir.instance.model.StringType;

import java.util.ArrayList;
import java.util.List;

public class AddressHelper
{
    public static Address createAddress(Address.AddressUse addressUse, String houseNameFlatNumber, String street, String village, String town, String county, String postcode) {
        Address address = new Address();

        if (addressUse != null)
            if (addressUse != Address.AddressUse.NULL)
                address.setUse(addressUse);

        if (StringUtils.isNotBlank(houseNameFlatNumber))
            address.addLine(houseNameFlatNumber);

        if (StringUtils.isNotBlank(street))
            address.addLine(street);

        if (StringUtils.isNotBlank(village))
            address.addLine(village);

        if (StringUtils.isNotBlank(town))
            address.setCity(town);

        if (StringUtils.isNotBlank(county))
            address.setDistrict(county);

        if (StringUtils.isNotBlank(postcode))
            address.setPostalCode(postcode);


        String displayText = generateDisplayText(address);
        address.setText(displayText);

        return address;
    }

    /*public static String createDisplayText(List<String> lines)
    {
        List<String> trimmedLines = new ArrayList<>();

        for (String line : lines)
            if (StringUtils.isNotBlank(line))
                trimmedLines.add(line.trim());

        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (String trimmedLine : trimmedLines)
        {
            sb.append(trimmedLine);

            if (++count < lines.size())
                sb.append(", ");
        }

        return sb.toString();
    }*/

    public static String generateDisplayText(Address address) {

        ArrayList<String> displayTextLines = new ArrayList<>();

        if (address.hasLine()) {
            for (StringType line: address.getLine()) {
                displayTextLines.add(line.getValue());
            }
        }

        if (address.hasCity()) {
            displayTextLines.add(address.getCity());
        }

        if (address.hasDistrict()) {
            displayTextLines.add(address.getDistrict());
        }

        if (address.hasPostalCode()) {
            displayTextLines.add(address.getPostalCode());
        }

        return String.join(", ", displayTextLines);
    }

    /**
     * returns the "best" home address from the patient resource
     */
    public static Address findHomeAddress(Patient fhirPatient) {

        if (!fhirPatient.hasAddress()) {
            return null;
        }

        List<Address> homeAddresses = new ArrayList<>();

        for (Address fhirAddress: fhirPatient.getAddress()) {
            if (fhirAddress.getUse() == null
                    || fhirAddress.getUse() == Address.AddressUse.HOME) {
                homeAddresses.add(fhirAddress);
            }
        }

        //return first non-ended one
        for (Address address: homeAddresses) {
            if (!address.hasPeriod()
                    || PeriodHelper.isActive(address.getPeriod())) {
                return address;
            }
        }

        //if no non-ended one, then return the last one, as it was added most recently
        if (!homeAddresses.isEmpty()) {
            int size = homeAddresses.size();
            return homeAddresses.get(size-1);
        }

        return null;
    }
}
