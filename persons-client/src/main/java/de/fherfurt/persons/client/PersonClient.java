package de.fherfurt.persons.client;

import de.fherfurt.persons.client.transfer.objects.PersonDto;

import java.util.List;
import java.util.Optional;

/**
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public interface PersonClient {

    Optional<PersonDto> findById(int id);

    Optional<PersonDto> findByEmail(String email);

    List<PersonDto> findByFaculty(int facultyId);

    List<PersonDto> findByName(String name);

    void deleteBy(int id);
}
