package de.fherfurt.appointments.client;

import de.fherfurt.appointments.client.transfer.objects.NewsAppointment;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.time.LocalDateTime;

/**
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public class DevAppointmentsService implements AppointmentsService{

    private final List<NewsAppointment> persons = Arrays.asList(
            new NewsAppointment(1, "appointment1", LocalDateTime.of(2022, 02, 21, 0, 0), "Mensa"),
            new NewsAppointment(2, "appointment2", LocalDateTime.of(2022, 02, 27, 0, 0), "Raum 1"),
            new NewsAppointment(3, "appointment3", LocalDateTime.of(2022, 03, 06, 0, 0), "Raum 2"),
            new NewsAppointment(4, "appointment4", LocalDateTime.of(2022, 04, 30, 0, 0), "Hof")
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
