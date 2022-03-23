package de.fherfurt.news.articles.business;

import de.fherfurt.news.articles.business.modules.entity.SortDirection;
import de.fherfurt.news.articles.business.modules.entity.SortPriority;
import de.fherfurt.news.articles.business.modules.entity.SortSettings;
import de.fherfurt.news.articles.entity.*;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

class NewsControllerTest {
    Logger logger = Logger.getLogger(this.getClass().getName());

    NewsController newsController = new NewsController();

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

    Article article2 = Article.builder()
            .withId(4)
            .withTitle("Best Title 2")
            .withContent("Hello Content 2")
            .withResponsiblePersonIds(Set.of(1))
            .withAuthorId(1)
            .withAppointmentId(1)
            .withFacultyName("faculty2")
            .withKeywords(Set.of("Anouncment","Computers"))
            .withDate(LocalDateTime.of(2022,2,20,15,0))
            .withLanguage(Language.DE)
            .withPriority(Priority.HIGH)
            .build();

    @Test
    void save() {
        Article testArticle = article;

        newsController.save(testArticle);

        Article fetchedArticle = null;

        try {
            fetchedArticle = newsController.getArticle(5);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        Assertions.assertEquals(article, fetchedArticle);
    }

    @Test
    void delete() {
        int idToTest = 5;

        newsController.save(article);

        try {
            newsController.delete(idToTest);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        EntryNotFoundException e = Assertions.assertThrows(EntryNotFoundException.class, () -> newsController.getArticle(idToTest));

        Assertions.assertEquals("Article not found: " + idToTest, e.getMessage());
    }

    @Test
    void getArticlePreviews() {
        SortSettings sortSettings = new SortSettings(SortDirection.ASC, SortPriority.TITLE);
        PreviewRequest request = new PreviewRequest("", "", sortSettings);
        RequestType requestType = RequestType.SORT;

        newsController.save(article);
        newsController.save(article2);

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article,article2));

        List<Article> actualArticles = newsController.getArticlePreviews(request, requestType);

        Assertions.assertEquals(expectedArticles, actualArticles);
    }

    @Test
    void getArticle() {

        newsController.repository.save(article);

        Article fetchedArticle = null;
        Article fetchedArticleRepository = null;

        try {
            fetchedArticle = newsController.getArticle(5);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        try {
            fetchedArticleRepository = newsController.repository.findBy(5);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        Assertions.assertEquals(fetchedArticleRepository, fetchedArticle);
    }
}