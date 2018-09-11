package org.endeavourhealth.common.fhir;

import org.hl7.fhir.instance.model.Period;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

public class PeriodHelper {

    public static Period createPeriod(Date start, Date end) {
        Period fhirPeriod = new Period();
        fhirPeriod.setStart(start);
        fhirPeriod.setEnd(end);
        return fhirPeriod;
    }

    public static boolean isActive(Period period) {
        return (period.getEnd() == null
                || period.getEnd().after(new Date()));
    }

    public static Period createPeriod(XMLGregorianCalendar xmlStart, XMLGregorianCalendar xmlEnd) {
        Date start = null;
        if (xmlStart != null) {
            start = xmlStart.toGregorianCalendar().getTime();
        }
        Date end = null;
        if (xmlEnd != null) {
            end = xmlEnd.toGregorianCalendar().getTime();
        }
        return createPeriod(start, end);
    }

    /**
     * utility fns for comparing periods, handling nulls, so we can sort things with periods in a consistent manner
     */
    public static int comparePeriods(Period period1, Period period2) {
        int ret = comparePeriodEnds(period1, period2);

        //if they have the same end dates (or both are null), fall back to comparing by start date
        if (ret == 0) {
            ret = comparePeriodStarts(period1, period2);
        }

        return ret;
    }

    public static int comparePeriodStarts(Period period1, Period period2) {
        Date start1 = null;
        if (period1 != null) {
            start1 = period1.getStart();
        }

        Date start2 = null;
        if (period2 != null) {
            start2 = period2.getStart();
        }

        if (start1 != null && start2 != null) {
            return start1.compareTo(start2);

        } else if (start1 != null) {
            //note this is intentionally different to the similar part in comparePeriodEnds, because
            //we want periods without end datess to be LATER, but periods without start dates to be EARLIER
            return 1;

        } else if (start2 != null) {
            return -1;

        } else {
            return 0;
        }
    }

    public static int comparePeriodEnds(Period period1, Period period2) {
        Date end1 = null;
        if (period1 != null) {
            end1 = period1.getEnd();
        }
        Date end2 = null;
        if (period2 != null) {
            end2 = period2.getEnd();
        }

        if (end1 != null && end2 != null) {
            return end1.compareTo(end2);

        } else if (end1 != null) {
            //note this is intentionally different to the similar part in comparePeriodStarts, because
            //we want periods without end datess to be LATER, but periods without start dates to be EARLIER
            return -1;

        } else if (end2 != null) {
            return 1;

        } else {
            return 0;
        }
    }
}
