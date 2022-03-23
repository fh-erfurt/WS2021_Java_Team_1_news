package de.fherfurt.news.articles.boundary.mapper;

import de.fherfurt.appointments.client.transfer.objects.NewsAppointment;
import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.articles.entity.Language;
import de.fherfurt.news.articles.entity.Priority;
import de.fherfurt.news.client.dto.ArticleDto;
import de.fherfurt.persons.client.transfer.objects.PersonDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DtoToArticleMapperTest {

    ModelMapper mapper = new ModelMapper();

    NewsAppointment newsAppointmentDto = new NewsAppointment(1,"Termin",LocalDateTime.of(2022, Month.APRIL,1,12,1),"Webex");

    PersonDto personDto = PersonDto.builder()
            .withId(8)
            .withFirstname("Klaus")
            .withLastname("Heinrichsens")
            .build();

    Set<PersonDto> responsiblePersons = new HashSet<>();


    ArticleDto articleDto = ArticleDto.builder()
            .withId(5)
            .withTitle("Best Title")
            .withContent("Hello Content")
            .withResponsiblePersons(Set.of(personDto))
            .withAuthor(personDto)
            .withAppointment(newsAppointmentDto)
            .withFacultyName("Angewande Informatik")
            .withKeywords(Set.of("Anouncment","Computers"))
            .withDate(LocalDateTime.of(2022,7,20,15,0))
            .withLanguage(ArticleDto.LanguageDto.DE)
            .withPriority(ArticleDto.PriorityDto.HIGH)
            .build();

    Article article = Article.builder()
            .withId(5)
            .withTitle("Best Title")
            .withContent("Hello Content")
            .withResponsiblePersonIds(Set.of(8))
            .withAuthorId(8)
            .withAppointmentId(89)
            .withFacultyName("Angewande Informatik")
            .withKeywords(Set.of("Anouncment","Computers"))
            .withDate(LocalDateTime.of(2022,7,20,15,0))
            .withLanguage(Language.DE)
            .withPriority(Priority.HIGH)
            .build();

    @BeforeEach
    void setUp() {
        responsiblePersons.add(personDto);
        articleDto.setResponsiblePersons(responsiblePersons);
        newsAppointmentDto.setId(89);
    }

    @Test
    void mapToArticle() {
        DtoToArticleMapper mapper = new DtoToArticleMapper();
        Set<Integer> test = articleDto.getResponsiblePersons().stream().map(PersonDto::getId).collect(Collectors.toSet());


        Article testArticle = mapper.map(articleDto);

        Assertions.assertTrue(article.equals(testArticle));
    }
}