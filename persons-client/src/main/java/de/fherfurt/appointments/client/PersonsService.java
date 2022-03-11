package de.fherfurt.appointments.client;

import de.fherfurt.appointments.client.transfer.objects.NewsPerson;

import java.util.Optional;

/**
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public interface PersonsService {

    Optional<NewsPerson> getPersonByID(int id);

    Optional<NewsPerson> getPersonByMail(String mail);
}
