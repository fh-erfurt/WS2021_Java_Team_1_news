package de.fherfurt.appointments.client;

import de.fherfurt.appointments.client.transfer.objects.NewsFaculty;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public class DevFacultiesService implements FacultiesService {

    private final List<NewsFaculty> faculties = Arrays.asList(
            new NewsFaculty(1, "faculty1"),
            new NewsFaculty(2, "faculty2"),
            new NewsFaculty(3, "faculty3"),
            new NewsFaculty(4, "faculty4")
    );

    @Override
    public Optional<NewsFaculty> isFacultynameValid(String name){
        for (int i=0; i < 4; i++){
            if(faculties.get(i).getName().equals(name)){
                return Optional.ofNullable(faculties.get(i));
            }
        }
        return Optional.empty();
    }
}