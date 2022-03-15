package de.fherfurt.appointments.client.transfer.objects;

/**
 * Represents the Faculty information relevant for the News Module.
 * It is used to access Faculty Metadata to filter news articles with it.
 *
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public class NewsFaculty {

    private int id;
    private String name;

    public NewsFaculty(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
