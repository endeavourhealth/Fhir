package org.endeavourhealth.common.fhir;

import org.hl7.fhir.instance.model.Appointment;
import org.hl7.fhir.instance.model.ResourceType;

public class ParticipantHelper {

    public static Appointment.AppointmentParticipantComponent createParticipant(ResourceType resourceType, String id) {
        return createParticipant(resourceType, id, Appointment.ParticipantRequired.REQUIRED, Appointment.ParticipationStatus.ACCEPTED);
    }
    public static Appointment.AppointmentParticipantComponent createParticipant(ResourceType resourceType, String id, Appointment.ParticipantRequired required, Appointment.ParticipationStatus status) {
        return new Appointment.AppointmentParticipantComponent()
                .setActor(ReferenceHelper.createReference(resourceType, id))
                .setRequired(required)
                .setStatus(status);
    }
}
