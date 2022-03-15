package de.fherfurt.faculties.client;

import de.fherfurt.faculties.client.transfer.objects.NewsFaculty;

import java.util.Optional;

/**
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public interface FacultiesService {

    Optional<NewsFaculty> isFacultynameValid(String name);
}
