package org.endeavourhealth.common.fhir;

import org.hl7.fhir.instance.model.Period;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PeriodHelper {

    public static Period createPeriod(Date start, Date end) {
        Period fhirPeriod = new Period();
        fhirPeriod.setStart(start);
        fhirPeriod.setEnd(end);
        return fhirPeriod;
    }

    public static boolean isActive(Period period) {
        return isActive(period, new Date());
    }

    public static boolean isActive(Period period, Date asOfDate) {
        return period == null //assume the lack of a period means it's active (i.e. not known to be ended)
                || period.getEnd() == null
                || period.getEnd().after(asOfDate);
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

    /**
     * tests if a given date falls within a period
     * - if the period doesn't have a start date, then it's considered to have always existed
     * - if the period doesn't have an end date, then it's considered to go on indefinitely
     */
    public static boolean isWithin(Period period, Date dTest) {
        if (period.hasStart()) {
            Date dStart = period.getStart();
            if (dTest.before(dStart)) {
                return false;
            }
        }
        if (period.hasEnd()) {
            Date dEnd = period.getEnd();
            if (dTest.after(dEnd)) {
                return false;
            }
        }

        return true;
    }

    /**
     * tests if two periods overlap
     * - if a period doesn't have a start date, then it's considered to have always existed
     * - if a period doesn't have an end date, then it's considered to go on indefinitely
     * - if a period ends at the same time as one starts, then this is NOT considered an overlap
     */
    public static boolean overlaps(Period p1, Period p2) {

        Date start1 = p1.getStart();
        Date end1 = p1.getEnd();
        Date start2 = p2.getStart();
        Date end2 = p2.getEnd();

        //if one of the periods has no start and end, then we definitely have an overlap
        if ((start1 == null && end1 == null)
                || (start2 == null && end2 == null)) {
            return true;
        }
        
        //much easier if we don't have to deal with nulls
        if (start1 == null) {
            start1 = new Date(0);
        }
        if (end1 == null) {
            end1 = new Date(Long.MAX_VALUE);
        }
        if (start2 == null) {
            start2 = new Date(0);
        }
        if (end2 == null) {
            end2 = new Date(Long.MAX_VALUE);
        }

        //if first period started during second period, then it overlaps
        if (!start1.before(start2) //equals or after
                && start1.before(end2)) { //before, because matching the end date doesn't count as an overlap
            return true;
        }

        //if second period started during first period, then it overlaps
        if (!start2.before(start1) //equals or after
            && start2.before(end1)) { //before, because matching the end date doesn't count as an overlap
            return true;
        }

        return false;
    }

    /**
     * helper fn for logging out Period objects where we may have nulls at any point
     */
    public static String toString(Period p) {

        if (p == null) {
            return "[NULL PERIOD]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (p.hasStart()) {
            sb.append(dateFormat.format(p.getStart()));
        } else {
            sb.append("NULL");
        }

        sb.append(" to ");

        if (p.hasEnd()) {
            sb.append(dateFormat.format(p.getEnd()));
        } else {
            sb.append("NULL");
        }

        sb.append("]");
        return sb.toString();
    }
}
