package de.fherfurt.appointments.client;

import de.fherfurt.appointments.client.transfer.objects.DTOAppointment;

import java.util.Optional;

public interface AppointmentsService {

    int getAppointmentIdByTitle(String title);

    DTOAppointment getAppointmentById(int id);

    //Eher so?
    Optional<DTOAppointment> findBy(int id);
}
