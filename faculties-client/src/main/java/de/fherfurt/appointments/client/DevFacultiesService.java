package de.fherfurt.appointments.client;

import de.fherfurt.appointments.client.transfer.objects.NewsFaculty;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DevFacultiesService implements FacultiesService {

    private final List<NewsFaculty> faculties = Arrays.asList(
            new NewsFaculty(1, "faculty1"),
            new NewsFaculty(2, "faculty2"),
            new NewsFaculty(3, "faculty3"),
            new NewsFaculty(4, "faculty4")
    );

    @Override
    public Optional<NewsFaculty> getFacultyIdByName(String name){
        return faculties.stream().filter(user -> Objects.equals(user.getName(), name)).findFirst();
    }

    @Override
    public Optional<NewsFaculty> getFacultyNameById(int id){
        return Optional.ofNullable(faculties.get(id));
    }
}
