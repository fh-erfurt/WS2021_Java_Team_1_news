package de.fherfurt.news.articles.boundary.mapper;

import de.fherfurt.persons.client.transfer.objects.PersonDto;
import org.modelmapper.AbstractConverter;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * this is used to get the Ids of the persons
 * converts {@link Set<PersonDto>} to {@link Set<Integer>}
 *
 * @author Maximilian Roehr <maximilian.roehr@fh-erfurt.de>
 */
public class PersonsToPersonIdsConverter extends AbstractConverter<Set<PersonDto>, Set<Integer>> {

    @Override
    protected Set<Integer> convert(Set<PersonDto> persons) {

        return persons
                .stream()
                .map(PersonDto::getId)
                .collect(Collectors.toSet());
    }
}