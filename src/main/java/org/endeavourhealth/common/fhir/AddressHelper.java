package org.endeavourhealth.common.fhir;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.instance.model.*;

import java.util.ArrayList;
import java.util.Date;
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
     *
     * https://endeavourhealth.atlassian.net/browse/SD-247
     * had to change this function to deal with patients having multiple active
     * addresses at the same time. In that case, it'll use the most recently started one.
     */
    public static Address findHomeAddress(Patient fhirPatient) {

        if (!fhirPatient.hasAddress()) {
            return null;
        }

        //just get a list of home addresses
        List<Address> homeAddresses = new ArrayList<>();

        for (Address fhirAddress: fhirPatient.getAddress()) {
            if (fhirAddress.getUse() == null
                    || fhirAddress.getUse() == Address.AddressUse.HOME) {
                homeAddresses.add(fhirAddress);
            }
        }

        if (homeAddresses.isEmpty()) {
            return null;
        }

        //sort, using a static fn to do the comparison, since there's a fair bit of logic
        homeAddresses.sort((o1, o2) -> compareAddresses(o1, o2));

        //post-sorting, simply return the last one in the list
        return homeAddresses.get(homeAddresses.size()-1);
    }

    private static int compareAddresses(Address addr1, Address addr2) {

        //ones without end dates are always later than ones with end dates
        Date end1 = null;
        if (addr1.hasPeriod() && addr1.getPeriod().hasEnd()) {
           end1 = addr1.getPeriod().getEnd(); 
        }

        Date end2 = null;
        if (addr2.hasPeriod() && addr2.getPeriod().hasEnd()) {
            end2 = addr2.getPeriod().getEnd();
        }
        
        if (end1 == null && end2 == null) {
            //if neither has an end, then let the below sorting by start date apply
            
        } else if (end1 != null && end2 == null) {
            //if 1 is ended but 2 is not, then 1 goes before 2 (null end counts as later)
            return -1;
            
        } else if (end1 == null && end2 != null) {
            //if 1 is not ended but 2 is, then 2 goes before 1
            return 1;
            
        } else {
            //if both are ended, then sort by end date
            int comp = end1.compareTo(end2);
            if (comp != 0) {
                return comp;
            } else {
                //if the end dates are the same, then drop into the below sorting by start date
            }
        }
        
        Date start1 = null;
        if (addr1.hasPeriod() && addr1.getPeriod().hasStart()) {
            start1 = addr1.getPeriod().getStart();
        }

        Date start2 = null;
        if (addr2.hasPeriod() && addr2.getPeriod().hasStart()) {
            start2 = addr2.getPeriod().getStart();
        }

        if (start1 == null && start2 == null) {
            //if neither has a start date, then we can't sort any more
            return 0;

        } else if (start1 != null && start2 == null) {
            //if 1 has a date and 2 doesn't, then place 2 first (null start counts as earlier)
            return 1;

        } else if (start1 == null && start2 != null) {
            //if 1 doesn't have a start, but 2 does, then place 1 first
            return -1;

        } else {
            //if both have starts, then it's a straight date comparison
            return start1.compareTo(start2);
        }
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
