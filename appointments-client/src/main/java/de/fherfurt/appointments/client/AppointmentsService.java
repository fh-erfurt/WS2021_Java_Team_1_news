package de.fherfurt.appointments.client;

import de.fherfurt.appointments.client.transfer.objects.NewsAppointment;

import java.util.Optional;

/**
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public interface AppointmentsService {

    Optional<NewsAppointment> getAppointmentById(int id);

    /**
     * Eher so?
     *     Optional<NewsAppointment> findBy(int id);
     */

    Optional<NewsAppointment> getAppointmentIdByTitle(String title);
}
