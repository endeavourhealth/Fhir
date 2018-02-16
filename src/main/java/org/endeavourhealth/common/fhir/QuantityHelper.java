package org.endeavourhealth.common.fhir;

import org.hl7.fhir.instance.model.*;

import java.math.BigDecimal;

public class QuantityHelper {

    public static Quantity createQuantity(Double value, String unit) {
        if (value == null) {
            return null;
        }

        return new Quantity().setValue(BigDecimal.valueOf(value)).setUnit(unit);
    }

    public static SimpleQuantity createSimpleQuantity(Double value, String unit, Quantity.QuantityComparator comparator) {
        if (value == null) {
            return null;
        }

        SimpleQuantity q = new SimpleQuantity();
        q.setValue(BigDecimal.valueOf(value)).setUnit(unit).setComparator(comparator);
        return q;
    }

    public static SimpleQuantity createSimpleQuantity(Double value, String unit) {
        if (value == null) {
            return null;
        }

        SimpleQuantity q = new SimpleQuantity();
        q.setValue(BigDecimal.valueOf(value)).setUnit(unit);
        return q;
    }

    public static SimpleQuantity createSimpleQuantity(Double value, String unit, String system, String code) {
        if (value == null) {
            return null;
        }
        SimpleQuantity q = new SimpleQuantity();
        q.setValue(BigDecimal.valueOf(value)).setUnit(unit).setSystem(system).setCode(code);
        return q;
    }

    public static SimpleQuantity createSimpleQuantity(Double value, String unit, Quantity.QuantityComparator comparator,
                                                      String system, String code ) {
        if (value == null) {
            return null;
        }
        SimpleQuantity q = new SimpleQuantity();
        q.setValue(BigDecimal.valueOf(value)).setUnit(unit).setSystem(system).setCode(code).setComparator(comparator);
        return q;
    }

    public static Duration createDuration(Integer value, String unit) {
        if (value == null) {
            return null;
        }

        Duration d = new Duration();
        d.setValue(BigDecimal.valueOf(value)).setUnit(unit);
        return d;
    }
}
