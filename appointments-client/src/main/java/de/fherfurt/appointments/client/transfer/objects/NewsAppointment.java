package de.fherfurt.appointments.client.transfer.objects;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents the Appointment information relevant for the News Module.
 * It is used to access Appointment Metadata in to show it in news articles with an appointment.
 *
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
@Getter
@Setter
public class NewsAppointment {
    private int id;
    private String title;
    private LocalDateTime date;
    private String place;

    public NewsAppointment(int id, String title, LocalDateTime date, String place){
        this.id = id;
        this.title = title;
        this.date = date;
        this.place = place;
    }
}