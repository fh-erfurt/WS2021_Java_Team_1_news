package de.fherfurt.news.articles.business;

import de.fherfurt.appointments.client.AppointmentsClient;
import de.fherfurt.appointments.client.DevAppointmentsClient;
import de.fherfurt.faculties.client.DevFacultyClient;
import de.fherfurt.faculties.client.FacultyClient;
import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.articles.entity.Language;
import de.fherfurt.news.articles.entity.Priority;
import de.fherfurt.persons.client.DevPersonClient;
import de.fherfurt.persons.client.PersonClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

/**
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
class ArticleValidatorTest {

    //TODO replace all dev implementations with real implementations when going live
    AppointmentsClient appointmentsClient = new DevAppointmentsClient();
    FacultyClient facultyClient = new DevFacultyClient();
    PersonClient personClient = new DevPersonClient();

    ArticleValidator validator = new ArticleValidator(appointmentsClient, facultyClient, personClient);

    @Test
    void shouldReturnFalseEverythingEmpty() {
        Article article = Article.builder().build();

        Assertions.assertFalse(validator.validateArticle(article));
    }

    @Test
    void shouldReturnFalseEmptyTitle() {
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
    void shouldReturnFalseEmptyContent() {
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
    void shouldReturnFalseFalseResponsiblePersonIds() {
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
    void shouldReturnFalseFalseAuthorId() {
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
    void shouldReturnFalseEmptyAuthorId() {
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
    void shouldReturnFalseFalseAppointmentId() {
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
    void shouldReturnFalseFalseFacultyName() {
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
    void shouldReturnFalseEmptyFacultyName() {
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
    void shouldReturnFalseFalseDate() {
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
    void shouldReturnFalseEmptyDate() {
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
    void shouldReturnFalseEmptyLanguage() {
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
    void shouldReturnFalseEmptyPriority() {
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
    void shouldReturnTrueEverythingCorrect() {
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
    void shouldReturnTrueEmptyPersons() {
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
    void shouldReturnTrueEmptyAppointment() {
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
    void shouldReturnTrueDateLongAgo() {
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