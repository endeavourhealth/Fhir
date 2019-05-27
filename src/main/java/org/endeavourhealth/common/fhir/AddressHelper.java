package org.endeavourhealth.common.fhir;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.instance.model.*;

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


    public static boolean hasLine(Address address, String line) {
        if (!address.hasLine()) {
            return false;
        }

        for (StringType s: address.getLine()) {
            String str = s.getValue();
            if (str.equalsIgnoreCase(line)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasCity(Address address, String city) {
        return address.hasCity()
                && address.getCity().equalsIgnoreCase(city);
    }

    public static boolean hasDistrict(Address address, String district) {
        return address.hasDistrict()
                && address.getDistrict().equalsIgnoreCase(district);
    }

    public static boolean hasPostcode(Address address, String postcode) {
        if (address.hasPostalCode()) {

            //the ADT wrongly leave spaces in the postcode, so we need to remove them to compare
            postcode = postcode.replace(" ", "");

            String s = address.getPostalCode();
            s = s.replace(" ", ""); //the ADT wrongly leave spaces in the postcode, so we need to remove them to compare

            if (s.equals(postcode)) {
                return true;
            }
        }

        return false;
    }

    public static List<Address> findMatches(Address toCheck, List<Address> potentials) {

        List<Address> ret = new ArrayList<>();

        for (Address address: potentials) {

            boolean matches = true;

            //note the text, period and ID aren't compared. The text is derived from the other fields and
            //isn't consistently set (by the HL7 Receiver) and the ID is used to perform exact matches
            //within the Barts transform. This function just tries to find addresses that look the same.

            if (address.hasLine()) {
                for (StringType line: address.getLine()) {
                    if (!hasLine(toCheck, line.toString())) {
                        matches = false;
                        break;
                    }
                }
            }

            if (address.hasCity()) {
                if (!hasCity(toCheck, address.getCity())) {
                    matches = false;
                }
            }

            if (address.hasDistrict()) {
                if (!hasDistrict(toCheck, address.getDistrict())) {
                    matches = false;
                }
            }

            if (address.hasPostalCode()) {
                String postcode = address.getPostalCode();
                if (!hasPostcode(toCheck, postcode)) {
                    matches = false;
                }
            }

            if (address.hasUse()) {
                Address.AddressUse use = address.getUse();
                if (!hasUse(toCheck, use)) {
                    matches = false;
                }
            }

            //if we make it here, it's a duplicate
            if (matches) {
                ret.add(address);
            }
        }

        return ret;
    }

    public static boolean hasUse(Address toCheck, Address.AddressUse use) {

        //when an address is ended it's use is changed to OLD, so compare use normally,
        //but also let the use pass if either one of them is OLD
        return toCheck.hasUse()
                && (toCheck.getUse() == use
                || toCheck.getUse() == Address.AddressUse.OLD
                || use == Address.AddressUse.OLD);
    }
}
