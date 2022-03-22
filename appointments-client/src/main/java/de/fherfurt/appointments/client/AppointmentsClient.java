package de.fherfurt.appointments.client;

import de.fherfurt.appointments.client.transfer.objects.NewsAppointment;

import java.util.Optional;

/**
 * Interface of the Appointments Client
 *
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public interface AppointmentsClient {

    Optional<NewsAppointment> getAppointmentById(int id);

    Optional<NewsAppointment> getAppointmentByName(String title);
}
