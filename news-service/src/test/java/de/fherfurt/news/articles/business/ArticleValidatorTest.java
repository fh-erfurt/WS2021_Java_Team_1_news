package de.fherfurt.news.articles.business;

import de.fherfurt.news.articles.entity.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArticleValidatorTest {

    @Test
    void shouldReturnFalseEverythingEmpty(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        Assertions.assertEquals(false, validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseEmptyTitle(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        article.setContent("Example Content");
        article.setResponsiblePersonIds(Set.of(1, 2, 3));
        article.setAuthorId(4);
        article.setAppointmentId(1);
        article.setFacultyName("faculty1");
        article.setDate(LocalDateTime.of(2022, 02, 14, 10, 0));

        Assertions.assertEquals(false, validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseEmptyContent(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        article.setTitle("Example Title");
        article.setResponsiblePersonIds(Set.of(1, 2, 3));
        article.setAuthorId(4);
        article.setAppointmentId(1);
        article.setFacultyName("faculty1");
        article.setDate(LocalDateTime.of(2022, 02, 14, 10, 0));

        Assertions.assertEquals(false, validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseFalseResponsiblePersonIds(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        article.setTitle("Example Title");
        article.setContent("Example Content");
        article.setResponsiblePersonIds(Set.of(1, 2, 3, 20));
        article.setAuthorId(4);
        article.setAppointmentId(1);
        article.setFacultyName("faculty1");
        article.setDate(LocalDateTime.of(2022, 02, 14, 10, 0));

        Assertions.assertEquals(false, validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseFalseAuthorId(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        article.setTitle("Example Title");
        article.setContent("Example Content");
        article.setResponsiblePersonIds(Set.of(1, 2, 3));
        article.setAuthorId(40);
        article.setAppointmentId(1);
        article.setFacultyName("faculty1");
        article.setDate(LocalDateTime.of(2022, 02, 14, 10, 0));

        Assertions.assertEquals(false, validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseEmptyAuthorId(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        article.setTitle("Example Title");
        article.setContent("Example Content");
        article.setResponsiblePersonIds(Set.of(1, 2, 3));
        article.setAppointmentId(1);
        article.setFacultyName("faculty1");
        article.setDate(LocalDateTime.of(2022, 02, 14, 10, 0));

        Assertions.assertEquals(false, validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseFalseAppointmentId(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        article.setTitle("Example Title");
        article.setContent("Example Content");
        article.setResponsiblePersonIds(Set.of(1, 2, 3));
        article.setAuthorId(4);
        article.setAppointmentId(10);
        article.setFacultyName("faculty1");
        article.setDate(LocalDateTime.of(2022, 02, 14, 10, 0));

        Assertions.assertEquals(false, validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseFalseFacultyName(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        article.setTitle("Example Title");
        article.setContent("Example Content");
        article.setResponsiblePersonIds(Set.of(1, 2, 3));
        article.setAuthorId(4);
        article.setAppointmentId(1);
        article.setFacultyName("faculty non existent");
        article.setDate(LocalDateTime.of(2022, 02, 14, 10, 0));

        Assertions.assertEquals(false, validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseEmptyFacultyName(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        article.setTitle("Example Title");
        article.setContent("Example Content");
        article.setResponsiblePersonIds(Set.of(1, 2, 3));
        article.setAuthorId(4);
        article.setAppointmentId(1);
        article.setDate(LocalDateTime.of(2022, 02, 14, 10, 0));

        Assertions.assertEquals(false, validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseFalseDate(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        article.setTitle("Example Title");
        article.setContent("Example Content");
        article.setResponsiblePersonIds(Set.of(1, 2, 3));
        article.setAuthorId(4);
        article.setAppointmentId(1);
        article.setFacultyName("faculty1");
        article.setDate(LocalDateTime.of(2023, 02, 14, 10, 0));

        Assertions.assertEquals(false, validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseEmptyDate(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        article.setTitle("Example Title");
        article.setContent("Example Content");
        article.setResponsiblePersonIds(Set.of(1, 2, 3));
        article.setAuthorId(4);
        article.setAppointmentId(1);
        article.setFacultyName("faculty1");

        Assertions.assertEquals(false, validator.validateArticle(article));
    }

    @Test
    void shouldReturnTrueEverythingCorrect(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        article.setTitle("Example Title");
        article.setContent("Example Content");
        article.setResponsiblePersonIds(Set.of(1, 2, 3));
        article.setAuthorId(4);
        article.setAppointmentId(1);
        article.setFacultyName("faculty1");
        article.setDate(LocalDateTime.of(2022, 02, 14, 10, 0));

        Assertions.assertEquals(true, validator.validateArticle(article));
    }

    @Test
    void shouldReturnTrueEmptyPersons(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        article.setTitle("Example Title");
        article.setContent("Example Content");
        article.setAuthorId(4);
        article.setAppointmentId(1);
        article.setFacultyName("faculty1");
        article.setDate(LocalDateTime.of(2022, 02, 14, 10, 0));

        Assertions.assertEquals(true, validator.validateArticle(article));
    }

    @Test
    void shouldReturnTrueEmptyAppointment(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        article.setTitle("Example Title");
        article.setContent("Example Content");
        article.setResponsiblePersonIds(Set.of(1, 2, 3));
        article.setAuthorId(4);
        article.setFacultyName("faculty1");
        article.setDate(LocalDateTime.of(2022, 02, 14, 10, 0));

        Assertions.assertEquals(true, validator.validateArticle(article));
    }

    @Test
    void shouldReturnTrueDateLongAgo(){
        ArticleValidator validator = new ArticleValidator();
        Article article = new Article();

        article.setTitle("Example Title");
        article.setContent("Example Content");
        article.setResponsiblePersonIds(Set.of(1, 2, 3));
        article.setAuthorId(4);
        article.setAppointmentId(1);
        article.setFacultyName("faculty1");
        article.setDate(LocalDateTime.of(1900, 02, 14, 10, 0));

        Assertions.assertEquals(true, validator.validateArticle(article));
    }
}