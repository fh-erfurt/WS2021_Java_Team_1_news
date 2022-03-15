package de.fherfurt.faculties.client.transfer.objects;

/**
 * Represents the Faculty information relevant for the News Module.
 * It is used to access Faculty Metadata to filter news articles with it.
 *
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public class NewsFaculty {

    private String name;

    public NewsFaculty(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
