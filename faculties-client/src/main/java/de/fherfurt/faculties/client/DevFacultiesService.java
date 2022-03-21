package de.fherfurt.faculties.client;

import de.fherfurt.faculties.client.transfer.objects.NewsFaculty;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public class DevFacultiesService implements FacultiesService {

    private final List<NewsFaculty> faculties = Arrays.asList(
            new NewsFaculty("faculty1"),
            new NewsFaculty("faculty2"),
            new NewsFaculty("faculty3"),
            new NewsFaculty("faculty4")
    );

    @Override
    public boolean isFacultynameValid(String name){
        for (int i=0; i < 4; i++){
            if(faculties.get(i).getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
