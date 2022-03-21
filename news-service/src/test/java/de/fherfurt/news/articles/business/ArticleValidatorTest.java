package de.fherfurt.news.articles.business;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.articles.entity.Language;
import de.fherfurt.news.articles.entity.Priority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

/**
 *  @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
class ArticleValidatorTest {

    @Test
    void shouldReturnFalseEverythingEmpty(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder().build();

        Assertions.assertTrue(validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseEmptyTitle(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withContent("Example Content")
                .withResponsiblePersonIds(Set.of(1, 2, 3))
                .withAuthorId(4)
                .withAppointmentId(1)
                .withFacultyName("faculty1")
                .withDate(LocalDateTime.of(2022, Month.FEBRUARY, 14, 10, 0))
                .withLanguage(Language.DE)
                .withPriority(Priority.NORMAL)
                .build();


        Assertions.assertFalse(validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseEmptyContent(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withResponsiblePersonIds(Set.of(1, 2, 3))
                .withAuthorId(4)
                .withAppointmentId(1)
                .withFacultyName("faculty1")
                .withDate(LocalDateTime.of(2022, Month.FEBRUARY, 14, 10, 0))
                .withLanguage(Language.DE)
                .withPriority(Priority.NORMAL)
                .build();

        Assertions.assertFalse(validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseFalseResponsiblePersonIds(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withContent("Example Content")
                .withResponsiblePersonIds(Set.of(1, 2, 3, 20))
                .withAuthorId(4)
                .withAppointmentId(1)
                .withFacultyName("faculty1")
                .withDate(LocalDateTime.of(2022, Month.FEBRUARY, 14, 10, 0))
                .withLanguage(Language.DE)
                .withPriority(Priority.NORMAL)
                .build();

        Assertions.assertFalse(validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseFalseAuthorId(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withContent("Example Content")
                .withResponsiblePersonIds(Set.of(1, 2, 3))
                .withAuthorId(40)
                .withAppointmentId(1)
                .withFacultyName("faculty1")
                .withDate(LocalDateTime.of(2022, Month.FEBRUARY, 14, 10, 0))
                .withLanguage(Language.DE)
                .withPriority(Priority.NORMAL)
                .build();

        Assertions.assertFalse(validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseEmptyAuthorId(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withContent("Example Content")
                .withResponsiblePersonIds(Set.of(1, 2, 3))
                .withAppointmentId(1)
                .withFacultyName("faculty1")
                .withDate(LocalDateTime.of(2022, Month.FEBRUARY, 14, 10, 0))
                .withLanguage(Language.DE)
                .withPriority(Priority.NORMAL)
                .build();

        Assertions.assertFalse(validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseFalseAppointmentId(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withContent("Example Content")
                .withResponsiblePersonIds(Set.of(1, 2, 3))
                .withAuthorId(4)
                .withAppointmentId(10)
                .withFacultyName("faculty1")
                .withDate(LocalDateTime.of(2022, Month.FEBRUARY, 14, 10, 0))
                .withLanguage(Language.DE)
                .withPriority(Priority.NORMAL)
                .build();

        Assertions.assertFalse(validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseFalseFacultyName(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withContent("Example Content")
                .withResponsiblePersonIds(Set.of(1, 2, 3))
                .withAuthorId(4)
                .withAppointmentId(1)
                .withFacultyName("faculty non existent")
                .withDate(LocalDateTime.of(2022, Month.FEBRUARY, 14, 10, 0))
                .withLanguage(Language.DE)
                .withPriority(Priority.NORMAL)
                .build();

        Assertions.assertFalse(validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseEmptyFacultyName(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withContent("Example Content")
                .withResponsiblePersonIds(Set.of(1, 2, 3))
                .withAuthorId(4)
                .withAppointmentId(1)
                .withDate(LocalDateTime.of(2022, Month.FEBRUARY, 14, 10, 0))
                .withLanguage(Language.DE)
                .withPriority(Priority.NORMAL)
                .build();

        Assertions.assertFalse(validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseFalseDate(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withContent("Example Content")
                .withResponsiblePersonIds(Set.of(1, 2, 3))
                .withAuthorId(4)
                .withAppointmentId(1)
                .withFacultyName("faculty1")
                .withDate(LocalDateTime.of(99999, Month.FEBRUARY, 14, 10, 0))
                .withLanguage(Language.DE)
                .withPriority(Priority.NORMAL)
                .build();

        Assertions.assertFalse(validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseEmptyDate(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withContent("Example Content")
                .withResponsiblePersonIds(Set.of(1, 2, 3))
                .withAuthorId(4)
                .withAppointmentId(1)
                .withFacultyName("faculty1")
                .withLanguage(Language.DE)
                .withPriority(Priority.NORMAL)
                .build();

        Assertions.assertFalse(validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseEmptyLanguage(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withContent("Example Content")
                .withResponsiblePersonIds(Set.of(1, 2, 3))
                .withAuthorId(4)
                .withAppointmentId(1)
                .withFacultyName("faculty1")
                .withDate(LocalDateTime.of(2022, Month.FEBRUARY, 14, 10, 0))
                .withPriority(Priority.NORMAL)
                .build();

        Assertions.assertFalse(validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseEmptyPriority(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withContent("Example Content")
                .withResponsiblePersonIds(Set.of(1, 2, 3))
                .withAuthorId(4)
                .withAppointmentId(1)
                .withFacultyName("faculty1")
                .withDate(LocalDateTime.of(2022, Month.FEBRUARY, 14, 10, 0))
                .withLanguage(Language.DE)
                .build();

        Assertions.assertFalse(validator.validateArticle(article));
    }

    @Test
    void shouldReturnTrueEverythingCorrect(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withContent("Example Content")
                .withResponsiblePersonIds(Set.of(1, 2, 3))
                .withAuthorId(4)
                .withAppointmentId(1)
                .withFacultyName("faculty1")
                .withDate(LocalDateTime.of(2022, Month.FEBRUARY, 14, 10, 0))
                .withLanguage(Language.DE)
                .withPriority(Priority.NORMAL)
                .build();

        Assertions.assertTrue(validator.validateArticle(article));
    }

    @Test
    void shouldReturnTrueEmptyPersons(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withContent("Example Content")
                .withAuthorId(4)
                .withAppointmentId(1)
                .withFacultyName("faculty1")
                .withDate(LocalDateTime.of(2022, Month.FEBRUARY, 14, 10, 0))
                .withLanguage(Language.DE)
                .withPriority(Priority.NORMAL)
                .build();

        Assertions.assertTrue(validator.validateArticle(article));
    }

    @Test
    void shouldReturnTrueEmptyAppointment(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withContent("Example Content")
                .withResponsiblePersonIds(Set.of(1, 2, 3))
                .withAuthorId(4)
                .withFacultyName("faculty1")
                .withDate(LocalDateTime.of(2022, Month.FEBRUARY, 14, 10, 0))
                .withLanguage(Language.DE)
                .withPriority(Priority.NORMAL)
                .build();

        Assertions.assertTrue(validator.validateArticle(article));
    }

    @Test
    void shouldReturnTrueDateLongAgo(){
        ArticleValidator validator = new ArticleValidator();
        Article article = Article.builder()
                .withTitle("Example Title")
                .withContent("Example Content")
                .withResponsiblePersonIds(Set.of(1, 2, 3))
                .withAuthorId(4)
                .withAppointmentId(1)
                .withFacultyName("faculty1")
                .withDate(LocalDateTime.of(1900, Month.FEBRUARY, 14, 10, 0))
                .withLanguage(Language.DE)
                .withPriority(Priority.NORMAL)
                .build();

        Assertions.assertTrue(validator.validateArticle(article));
    }
}