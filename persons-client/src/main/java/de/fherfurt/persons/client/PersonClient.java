package de.fherfurt.persons.client;

import de.fherfurt.persons.client.transfer.objects.NewsPerson;

import java.util.Optional;

/**
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public interface PersonClient {

    Optional<NewsPerson> getPersonByID(int id);

    Optional<NewsPerson> getPersonByMail(String mail);
}
