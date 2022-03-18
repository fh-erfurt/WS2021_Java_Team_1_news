package de.fherfurt.persons.client.transfer.objects;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the Person information relevant for the News Module.
 * It is used to access Person Metadata to filter news articles with it.
 *
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
@Getter
@Setter
public class NewsPerson {

    private int id;
    private String name;
    private String mail;
    private String tel;

    public NewsPerson(int id, String name, String mail, String tel){
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.tel = tel;
    }
}
