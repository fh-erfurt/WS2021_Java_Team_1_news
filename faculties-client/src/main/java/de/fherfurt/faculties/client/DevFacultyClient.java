package de.fherfurt.faculties.client;

import de.fherfurt.faculties.client.transfer.objects.NewsFaculty;

import java.util.Arrays;
import java.util.List;

/**
 * Dev Implementation of the Faculty Client interface
 *
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public class DevFacultyClient implements FacultyClient {

    private final List<NewsFaculty> faculties = Arrays.asList(
            new NewsFaculty("faculty1"),
            new NewsFaculty("faculty2"),
            new NewsFaculty("faculty3"),
            new NewsFaculty("faculty4")
    );

    @Override
    public boolean isFacultyNameValid(String name) {
        for (int i = 0; i < 4; i++) {
            if (faculties.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
