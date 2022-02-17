package de.fherfurt.appointments.client;

import de.fherfurt.appointments.client.transfer.objects.NewsPerson;

import java.util.Optional;

public interface PersonsService {

    Optional<NewsPerson> getPersonById(int id);

    Optional<NewsPerson> getPersonIdByMail(String mail);
}
