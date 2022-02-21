package de.fherfurt.appointments.client;

import de.fherfurt.appointments.client.transfer.objects.NewsAppointment;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public class DevAppointmentsService implements AppointmentsService{

    private final List<NewsAppointment> persons = Arrays.asList(
            new NewsAppointment(1, "appointment1", "21/02/2022", "Mensa"),
            new NewsAppointment(2, "appointment2", "27/02/2022", "Raum 1"),
            new NewsAppointment(3, "appointment3", "06/03/2022", "Raum 2"),
            new NewsAppointment(4, "appointment4", "30/04/2022", "Hof")
    );

    @Override
    public Optional<NewsAppointment> getAppointmentById(int id){
        return Optional.ofNullable(persons.get(id));
    }

    @Override
    public Optional<NewsAppointment> getAppointmentIdByTitle(String title){
        return persons.stream().filter(user -> Objects.equals(user.getTitle(), title)).findFirst();
    }
}
