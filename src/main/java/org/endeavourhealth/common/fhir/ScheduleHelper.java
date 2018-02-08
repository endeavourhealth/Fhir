package org.endeavourhealth.common.fhir;

import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleHelper {

    /**
     * participants are stored in two places in FHIR schedules, so this fn is a handy way to get them out in one go
     */
    public static List<Reference> getAllActors(Schedule schedule) {
        List<Reference> ret = new ArrayList<>();

        if (schedule.hasActor()) {
            Reference ref = schedule.getActor();
            ret.add(ref);
        }

        if (schedule.hasExtension()) {
            for (Extension extension: schedule.getExtension()) {
                if (extension.getUrl().equals(FhirExtensionUri.SCHEDULE_ADDITIONAL_ACTOR)) {
                    Reference ref = (Reference)extension.getValue();
                    ret.add(ref);
                }
            }
        }

        return ret;
    }
}
