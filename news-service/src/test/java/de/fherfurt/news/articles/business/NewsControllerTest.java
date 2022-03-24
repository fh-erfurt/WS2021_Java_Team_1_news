package de.fherfurt.news.articles.business;

import de.fherfurt.news.articles.business.errors.ArticleNotValidException;
import de.fherfurt.news.articles.business.modules.entity.*;
import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.articles.entity.Language;
import de.fherfurt.news.articles.entity.Priority;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import org.junit.jupiter.api.AfterEach;
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

    NewsController newsController = NewsController.getInstance();

    Article article = Article.builder()
            .withId(5)
            .withTitle("Best Title")
            .withContent("Hello Content")
            .withResponsiblePersonIds(Set.of(1))
            .withAuthorId(1)
            .withAppointmentId(1)
            .withFacultyName("faculty1")
            .withKeywords(Set.of("Announcement", "Computers"))
            .withDate(LocalDateTime.of(2022, 1, 20, 15, 0))
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
            .withKeywords(Set.of("Announcement", "Computers"))
            .withDate(LocalDateTime.of(2022, 2, 20, 15, 0))
            .withLanguage(Language.DE)
            .withPriority(Priority.HIGH)
            .build();

    @AfterEach
    void tearDown(){
        for (Article article: newsController.repository.fetchAll()) {
            try {
                newsController.delete(article.getId());
            } catch (EntryNotFoundException e) {
                logger.log(Level.WARNING,e.getMessage());
            }
        }
    }

    @Test
    void save() {
        Article testArticle = article;

        try {
            newsController.save(testArticle);
        } catch (ArticleNotValidException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

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

        try {
            newsController.save(article);
        } catch (ArticleNotValidException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

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

        try {
            newsController.save(article);
        } catch (ArticleNotValidException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        try {
            newsController.save(article2);
        } catch (ArticleNotValidException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article, article2));

        List<Article> actualArticles = newsController.getArticlePreviews(request, requestType);

        Assertions.assertEquals(expectedArticles, actualArticles);
    }

    @Test
    void getArticle() {

        newsController.repository.save(article);

        Article fetchedArticle = null;

        try {
            fetchedArticle = newsController.getArticle(5);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        Assertions.assertEquals(article, fetchedArticle);
    }
}