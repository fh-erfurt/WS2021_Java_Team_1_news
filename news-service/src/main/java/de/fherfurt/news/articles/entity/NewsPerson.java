package de.fherfurt.news.articles.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * data container for a person
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@Getter
@Setter
public class NewsPerson {
    private int id;
    private String name;
    private String mail;
    private String telNumber;
}
