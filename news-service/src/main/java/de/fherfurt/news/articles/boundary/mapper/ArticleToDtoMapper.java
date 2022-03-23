package de.fherfurt.news.articles.boundary.mapper;

import de.fherfurt.appointments.client.AppointmentsClient;
import de.fherfurt.appointments.client.DevAppointmentsClient;
import de.fherfurt.appointments.client.transfer.objects.NewsAppointment;
import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.client.dto.ArticleDto;
import de.fherfurt.persons.client.DevPersonClient;
import de.fherfurt.persons.client.PersonClient;
import de.fherfurt.persons.client.transfer.objects.PersonDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ArticleToDtoMapper {

    TypeMap<Article,ArticleDto> typeMap = new ModelMapper().createTypeMap(Article.class,ArticleDto.class);
    PersonClient personClient = new DevPersonClient();
    AppointmentsClient appointmentsClient = new DevAppointmentsClient();

    public ArticleDto map(Article article){

        Set<Integer> responsiblePersonIds = article.getResponsiblePersonIds();

        Set<PersonDto> responsiblePersons = getPersons(responsiblePersonIds);
        PersonDto author = getPerson(article.getAuthorId());
        NewsAppointment newsAppointment = getAppointment(article.getAppointmentId());

        typeMap.addMappings(mapper -> mapper.map(Article::getResponsiblePersonIds,(dest,v)->dest.setResponsiblePersons(responsiblePersons)));
        typeMap.addMappings(mapper -> mapper.map(Article::getAuthorId, (dest,v)->dest.setAuthor(author)));
        typeMap.addMappings(mapper -> mapper.map(Article::getAppointmentId, (dest,v)->dest.setAppointment(newsAppointment)));

        return typeMap.map(article);
    }

    private NewsAppointment getAppointment(int id) {
        if (appointmentsClient.getAppointmentById(id).isPresent()) {
            Optional<NewsAppointment> appointment = appointmentsClient.getAppointmentById(id);
            return appointment.get();
        }
        return null;
    }

    private PersonDto getPerson(int id){
        if (!personClient.findById(id).isEmpty()){
            Optional<PersonDto> person = personClient.findById(id);
            return person.get();
        }
        return null;
    }

    private Set<PersonDto> getPersons(Set<Integer> personIds){
        Set<PersonDto> personDtos = new HashSet<>();
        for (int id: personIds){
            personDtos.add(getPerson(id));
        }
        return personDtos;
    }
}
