package de.fherfurt.appointments.client;

import de.fherfurt.appointments.client.transfer.objects.NewsAppointment;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.time.LocalDateTime;

/**
 * Dev Implementation of the Appointments Client interface
 *
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public class DevAppointmentsClient implements AppointmentsClient {

    private final List<NewsAppointment> appointments = Arrays.asList(
            new NewsAppointment(1, "appointment1", LocalDateTime.of(2022, 02, 21, 0, 0), "Mensa"),
            new NewsAppointment(2, "appointment2", LocalDateTime.of(2022, 02, 27, 0, 0), "Raum 1"),
            new NewsAppointment(3, "appointment3", LocalDateTime.of(2022, 03, 06, 0, 0), "Raum 2"),
            new NewsAppointment(4, "appointment4", LocalDateTime.of(2022, 04, 30, 0, 0), "Hof")
    );

    @Override
    public Optional<NewsAppointment> getAppointmentById(int id){
        for (int i=0; i < 4; i++){
            if(appointments.get(i).getId() == id){
                return Optional.ofNullable(appointments.get(i));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<NewsAppointment> getAppointmentByName(String title){
        return appointments.stream().filter(user -> Objects.equals(user.getTitle(), title)).findFirst();
    }
}
