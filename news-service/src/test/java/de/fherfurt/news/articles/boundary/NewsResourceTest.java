package de.fherfurt.news.articles.boundary;

import de.fherfurt.appointments.client.transfer.objects.NewsAppointment;
import de.fherfurt.news.articles.boundary.mapper.ArticleToDtoMapper;
import de.fherfurt.news.articles.boundary.mapper.DtoToArticleMapper;
import de.fherfurt.news.articles.entity.*;
import de.fherfurt.news.client.dto.ArticleDto;
import de.fherfurt.news.client.dto.ArticlePreviewDto;
import de.fherfurt.news.client.options.PreviewRequestClient;
import de.fherfurt.news.client.options.RequestTypeClient;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import de.fherfurt.persons.client.transfer.objects.PersonDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class NewsResourceTest {
    Logger logger = Logger.getLogger(this.getClass().getName());
    ModelMapper mapper = new ModelMapper();

    NewsResource newsResource = new NewsResource();

    NewsAppointment newsAppointmentDto = new NewsAppointment(1,"Termin",LocalDateTime.of(2022, Month.APRIL,1,12,1),"Webex");

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
            .withKeywords(Set.of("Anouncment","Computers"))
            .withDate(LocalDateTime.of(2022,1,20,15,0))
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
            .withKeywords(Set.of("Anouncment","Computers"))
            .withDate(LocalDateTime.of(2022,2,20,15,0))
            .withLanguage(ArticleDto.LanguageDto.DE)
            .withPriority(ArticleDto.PriorityDto.HIGH)
            .build();

    ArticlePreviewDto articlePreviewDto = ArticlePreviewDto.builder()
            .withId(5)
            .withTitle("Best Title")
            .withFacultyName("faculty1")
            .withKeywords(Set.of("Anouncment","Computers"))
            .withDate(LocalDateTime.of(2022,1,20,15,0))
            .build();

    ArticlePreviewDto articlePreviewDto2 = ArticlePreviewDto.builder()
            .withId(4)
            .withTitle("Best Title 2")
            .withFacultyName("faculty2")
            .withKeywords(Set.of("Anouncment","Computers"))
            .withDate(LocalDateTime.of(2022,2,20,15,0))
            .build();

    Article article = Article.builder()
            .withId(5)
            .withTitle("Best Title")
            .withContent("Hello Content")
            .withResponsiblePersonIds(Set.of(1))
            .withAuthorId(1)
            .withAppointmentId(1)
            .withFacultyName("faculty1")
            .withKeywords(Set.of("Anouncment","Computers"))
            .withDate(LocalDateTime.of(2022,1,20,15,0))
            .withLanguage(Language.DE)
            .withPriority(Priority.HIGH)
            .build();

    @BeforeEach
    void setUp() {
        responsiblePersons.add(personDto);
        articleDto.setResponsiblePersons(responsiblePersons);
    }

    @Test
    void save() throws EntryNotFoundException {
        ArticleDto testArticle = articleDto;

        newsResource.save(testArticle);

        ArticleDto fetchedArticle = null;

        try {
            fetchedArticle = newsResource.getArticle(5);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        Assertions.assertTrue(articleDto.equals(fetchedArticle));
    }

    @Test
    void delete() {
        int idToTest = 5;

        newsResource.save(articleDto);

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

        newsResource.save(articleDto);
        newsResource.save(articleDto2);

        List<ArticlePreviewDto> expectedArticles = new LinkedList<>(Arrays.asList(articlePreviewDto,articlePreviewDto2));

        List<ArticlePreviewDto> actualArticles = newsResource.getArticlePreviews(request, requestType);

        Assertions.assertEquals(expectedArticles, actualArticles);
    }

    @Test
    void getArticle() {
        newsResource.save(articleDto);

        ArticleDto fetchedArticle = null;
        ArticleDto fetchedArticleRepository = null;

        try {
            fetchedArticle = newsResource.getArticle(5);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        try {
            fetchedArticleRepository = newsResource.getArticle(5);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        Assertions.assertEquals(fetchedArticleRepository, fetchedArticle);
    }

}