package de.fherfurt.appointments.client.transfer.objects;

import java.util.Date;

/**
 * Represents the Appointment information relevant for the News Module.
 * It is used to access Appointment Metadata in to show it in news articles with an appointment.
 *
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public class NewsAppointment {
    private int id;
    private String title;
    private Date date;
    private String place;

    public NewsAppointment(int id, String title, Date date, String place){
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
