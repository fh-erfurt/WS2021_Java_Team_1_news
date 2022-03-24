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

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

class DtoToArticleMapperTest {

    NewsAppointment newsAppointmentDto = new NewsAppointment(1, "Termin", LocalDateTime.of(2022, Month.APRIL, 1, 12, 1), "Webex");

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
            .withFacultyName("Angewandte Informatik")
            .withKeywords(Set.of("Announcement", "Computers"))
            .withDate(LocalDateTime.of(2022, 7, 20, 15, 0))
            .withLanguage(ArticleDto.LanguageDto.DE)
            .withPriority(ArticleDto.PriorityDto.HIGH)
            .build();

    Article article = Article.builder()
            .withId(5)
            .withTitle("Best Title")
            .withContent("Hello Content")
            .withResponsiblePersonIds(Set.of(8))
            .withAuthorId(8)
            .withAppointmentId(1)
            .withFacultyName("Angewandte Informatik")
            .withKeywords(Set.of("Announcement", "Computers"))
            .withDate(LocalDateTime.of(2022, 7, 20, 15, 0))
            .withLanguage(Language.DE)
            .withPriority(Priority.HIGH)
            .build();

    @BeforeEach
    void setUp() {
        responsiblePersons.add(personDto);
        articleDto.setResponsiblePersons(responsiblePersons);
    }

    @Test
    void mapToArticle() {
        DtoToArticleMapper mapper = new DtoToArticleMapper();

        Article testArticle = mapper.map(articleDto);

        Assertions.assertEquals(article, testArticle);
    }
}