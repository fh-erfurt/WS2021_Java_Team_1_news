package de.fherfurt.appointments.client;

import de.fherfurt.appointments.client.transfer.objects.NewsAppointment;

import java.util.Optional;

public interface AppointmentsService {

    Optional<NewsAppointment> getAppointmentById(int id);

    /**
     * Eher so?
     *     Optional<NewsAppointment> findBy(int id);
     */

    Optional<NewsAppointment> getAppointmentIdByTitle(String title);
}
