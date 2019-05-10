package org.endeavourhealth.common.fhir;

import com.google.common.base.Strings;
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

        //return last non-ended one
        for (int i=homeAddresses.size()-1; i>=0; i--) {
            Address address = homeAddresses.get(i);
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

    public static String findCity(Patient fhirPatient) {
        Address address = findHomeAddress(fhirPatient);
        if (address != null
                && address.hasCity()) {
            return address.getCity();
        }

        return null;
    }

    public static String findDistrict(Patient fhirPatient) {
        Address address = findHomeAddress(fhirPatient);
        if (address != null
                && address.hasDistrict()) {
            return address.getDistrict();
        }

        return null;
    }

    public static String findAddressLine(Patient fhirPatient, int index) {
        Address address = findHomeAddress(fhirPatient);
        if (address != null
                && address.hasLine()) {
            List<StringType> lines = address.getLine();
            if (index < lines.size()) {
                StringType stringType = lines.get(index);
                return stringType.getValue();
            }
        }

        return null;
    }

    public static String findPostcode(Patient fhirPatient) {

        Address fhirAddress = findHomeAddress(fhirPatient);
        if (fhirAddress != null) {

            //Homerton seem to sometimes enter extra information in the postcode
            //field, making it longer than the 8 chars the field allows. So
            //simply truncate down
            String s = fhirAddress.getPostalCode();
            if (!Strings.isNullOrEmpty(s)
                    && s.length() > 8) {
                s = s.substring(0, 8);
            }
            return s;
            //return fhirAddress.getPostalCode();
        }
        return null;
    }
}
