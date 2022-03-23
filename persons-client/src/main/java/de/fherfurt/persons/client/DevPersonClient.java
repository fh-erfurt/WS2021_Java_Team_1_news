package de.fherfurt.persons.client;

import de.fherfurt.persons.client.transfer.objects.PersonDto;
import de.fherfurt.persons.client.transfer.objects.Salutation;

import java.util.*;

/**
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public class DevPersonClient implements PersonClient {

    private final List<PersonDto> persons = Arrays.asList(
            new PersonDto(1, "user1@mail.com", Salutation.Herr, "firstName 1", "lastName 1", "user 1", "12345678901", "Image1", "fax", 1, null, null),
            new PersonDto(2, "user2@mail.com", Salutation.Frau, "firstName 2", "lastName 2", "user 2", "12345678902", "Image2", "fax", 2, null, null),
            new PersonDto(3, "user3@mail.com", Salutation.Enthalten, "firstName 3", "lastName 3", "user 3", "12345678903", "Image3", "fax", 3, null, null),
            new PersonDto(4, "user4@mail.com", Salutation.Herr, "firstName 4", "lastName 4", "user 4", "12345678904", "Image4", "fax", 4, null, null)
    );

    @Override
    public Optional<PersonDto> findById(int id) {
        for (int i = 0; i < 4; i++) {
            if (persons.get(i).getId() == id) {
                return Optional.ofNullable(persons.get(i));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<PersonDto> findByEmail(String mail) {
        return persons.stream().filter(user -> Objects.equals(user.getEmail(), mail)).findFirst();
    }

    @Override
    public List<PersonDto> findByFaculty(int facultyId){
        // Do nothing because this function is not needed.
        return Collections.emptyList();
    }

    @Override
    public List<PersonDto> findByName(String name){
        // Do nothing because this function is not needed.
        return Collections.emptyList();
    }

    @Override
    public void deleteBy(int id){
        // Do nothing because this function is not needed.
    }
}
