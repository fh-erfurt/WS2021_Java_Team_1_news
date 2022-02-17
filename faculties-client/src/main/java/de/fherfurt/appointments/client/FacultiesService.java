package de.fherfurt.appointments.client;

import de.fherfurt.appointments.client.transfer.objects.NewsFaculty;

import java.util.Optional;

public interface FacultiesService {

    Optional<NewsFaculty> getFacultyIdByName(String name);

    Optional<String> getFacultyNameById(int id);
}
