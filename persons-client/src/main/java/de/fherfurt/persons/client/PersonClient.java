package de.fherfurt.persons.client;

import de.fherfurt.persons.client.transfer.objects.NewsPerson;

import java.util.Optional;

/**
 * Interface for the Person Client
 *
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public interface PersonClient {

    Optional<NewsPerson> getPersonByID(int id);

    Optional<NewsPerson> getPersonByMail(String mail);
}
