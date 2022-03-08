package de.fherfurt.appointments.client.transfer.objects;

import java.time.LocalDateTime;

/**
 * Represents the Appointment information relevant for the News Module.
 * It is used to access Appointment Metadata in to show it in news articles with an appointment.
 *
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
