package de.fherfurt.news.articles.boundary;

import de.fherfurt.appointments.client.transfer.objects.NewsAppointment;
import de.fherfurt.news.articles.business.errors.ArticleNotValidException;
import de.fherfurt.news.client.dto.ArticleDto;
import de.fherfurt.news.client.dto.ArticlePreviewDto;
import de.fherfurt.news.client.options.PreviewRequestClient;
import de.fherfurt.news.client.options.RequestTypeClient;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import de.fherfurt.persons.client.transfer.objects.PersonDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

class NewsResourceTest {

    Logger logger = Logger.getLogger(this.getClass().getName());

    NewsResource newsResource = new NewsResource();

    NewsAppointment newsAppointmentDto = new NewsAppointment(1, "Termin", LocalDateTime.of(2022, Month.APRIL, 1, 12, 1), "Webex");

    PersonDto personDto = PersonDto.builder()
            .withId(1)
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
            .withFacultyName("faculty1")
            .withKeywords(Set.of("Announcement", "Computers"))
            .withDate(LocalDateTime.of(2022, 1, 20, 15, 0))
            .withLanguage(ArticleDto.LanguageDto.DE)
            .withPriority(ArticleDto.PriorityDto.HIGH)
            .build();

    ArticleDto articleDto2 = ArticleDto.builder()
            .withId(4)
            .withTitle("Best Title 2")
            .withContent("Hello Content 2")
            .withResponsiblePersons(Set.of(personDto))
            .withAuthor(personDto)
            .withAppointment(newsAppointmentDto)
            .withFacultyName("faculty2")
            .withKeywords(Set.of("Announcement", "Computers"))
            .withDate(LocalDateTime.of(2022, 2, 20, 15, 0))
            .withLanguage(ArticleDto.LanguageDto.DE)
            .withPriority(ArticleDto.PriorityDto.HIGH)
            .build();

    ArticlePreviewDto articlePreviewDto = ArticlePreviewDto.builder()
            .withId(5)
            .withTitle("Best Title")
            .withFacultyName("faculty1")
            .withKeywords(Set.of("Announcement", "Computers"))
            .withDate(LocalDateTime.of(2022, 1, 20, 15, 0))
            .build();

    ArticlePreviewDto articlePreviewDto2 = ArticlePreviewDto.builder()
            .withId(4)
            .withTitle("Best Title 2")
            .withFacultyName("faculty2")
            .withKeywords(Set.of("Announcement", "Computers"))
            .withDate(LocalDateTime.of(2022, 2, 20, 15, 0))
            .build();

    @BeforeEach
    void setUp() {
        responsiblePersons.add(personDto);
        articleDto.setResponsiblePersons(responsiblePersons);
    }

    @Test
    void save() {
        ArticleDto testArticle = articleDto;

        try {
            newsResource.save(testArticle);
        } catch (ArticleNotValidException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        ArticleDto fetchedArticle = null;

        try {
            fetchedArticle = newsResource.getArticle(5);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        Assertions.assertEquals(articleDto, fetchedArticle);
    }

    @Test
    void delete() {
        int idToTest = 5;

        try {
            newsResource.save(articleDto);
        } catch (ArticleNotValidException e) {
            logger.log(Level.WARNING,e.getMessage());
        }

        try {
            newsResource.delete(idToTest);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        EntryNotFoundException e = Assertions.assertThrows(EntryNotFoundException.class, () -> newsResource.getArticle(idToTest));

        Assertions.assertEquals("Article not found: " + idToTest, e.getMessage());
    }

    @Test
    void getArticlePreviews() {
        PreviewRequestClient.SortSettingsClient sortSettings = new PreviewRequestClient.SortSettingsClient(PreviewRequestClient.SortDirectionClient.ASC, PreviewRequestClient.SortPriorityClient.TITLE);
        PreviewRequestClient request = new PreviewRequestClient("", "", sortSettings);
        RequestTypeClient requestType = RequestTypeClient.SORT;

        try {
            newsResource.save(articleDto);
        } catch (ArticleNotValidException e) {
            logger.log(Level.WARNING,e.getMessage());
        }
        try {
            newsResource.save(articleDto2);
        } catch (ArticleNotValidException e) {
            logger.log(Level.WARNING,e.getMessage());
        }

        List<ArticlePreviewDto> expectedArticles = new LinkedList<>(Arrays.asList(articlePreviewDto, articlePreviewDto2));

        List<ArticlePreviewDto> actualArticles = newsResource.getArticlePreviews(request, requestType);

        Assertions.assertEquals(expectedArticles, actualArticles);
    }

    @Test
    void getArticle() {

        try {
            newsResource.save(articleDto);
        } catch (ArticleNotValidException e) {
            logger.log(Level.WARNING,e.getMessage());
        }

        ArticleDto fetchedArticle = null;

        try {
            fetchedArticle = newsResource.getArticle(5);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        Assertions.assertEquals(articleDto, fetchedArticle);
    }

}