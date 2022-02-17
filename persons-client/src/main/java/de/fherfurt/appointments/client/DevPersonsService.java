package de.fherfurt.appointments.client;

import de.fherfurt.appointments.client.transfer.objects.NewsPerson;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DevPersonsService implements PersonsService {

    private final List<NewsPerson> persons = Arrays.asList(
            new NewsPerson(1, "user1", "user1@1mail.com", "12345678901"),
            new NewsPerson(2, "user2", "user2@2mail.com", "12345678902"),
            new NewsPerson(3, "user3", "user3@3mail.com", "12345678903"),
            new NewsPerson(4, "user4", "user4@4mail.com", "12345678904")
    );

    @Override
    Optional<NewsPerson> getPersonById(int id){
        return Optional.ofNullable(persons.get(id));
    }

    @Override
    Optional<NewsPerson> getPersonIdByMail(String mail){
        return persons.stream().filter(user -> Objects.equals(user.getMail(), mail)).findFirst();
    }
}
