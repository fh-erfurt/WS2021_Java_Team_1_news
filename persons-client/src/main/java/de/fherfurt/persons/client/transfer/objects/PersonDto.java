package de.fherfurt.persons.client.transfer.objects;

import lombok.*;

import java.util.List;

/**
 * Represents the Person information relevant for the News Module.
 * It is used to access Person Metadata to filter news articles with it.
 *
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(setterPrefix = "with")
public class PersonDto {

    private int id;
    private String email;
    private Salutation salutation;
    private String firstname;
    private String lastname;
    private String username;
    private String phone;
    private String imageUrl;
    private String fax;
    private int facultyId;
    private List<String> titles;
    private List<String> positions;

}