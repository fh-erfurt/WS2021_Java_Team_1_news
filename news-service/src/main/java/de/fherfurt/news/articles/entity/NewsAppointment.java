package de.fherfurt.news.articles.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * data container for an appointment
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@Getter
@Setter
public class NewsAppointment {
    private int id;
    private String name;
    private Date date;
    private String place;

}
