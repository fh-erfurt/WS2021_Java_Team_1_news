package de.fherfurt.news.articles.business;

import de.fherfurt.appointments.client.AppointmentsClient;
import de.fherfurt.faculties.client.FacultyClient;
import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.persons.client.PersonClient;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Validates an article to make sure there is no wrong information like a non-existent author ur a publishing date in the future.
 *
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */

public class ArticleValidator {

    private Article article;

    public ArticleValidator(AppointmentsClient appointmentsClient, FacultyClient facultyClient, PersonClient personClient) {
        this.appointmentsClient = appointmentsClient;
        this.facultyClient = facultyClient;
        this.personClient = personClient;
    }

    /**
     * implementation of the DevImplementation Interfaces. Must be replaced with actual interfaces for integration.
     */
    private final AppointmentsClient appointmentsClient;
    private final FacultyClient facultyClient;
    private final PersonClient personClient;

    public boolean validateArticle(Article article) {
        this.article = article;

        return (
                validateTitle() &&
                        validateContent() &&
                        validateResponsiblePersonIds() &&
                        validateAuthorId() &&
                        validateAppointmentId() &&
                        validateFacultyName() &&
                        validateDate() &&
                        validateLanguage() &&
                        validatePriority()
        );
    }

    private boolean validateTitle() {

        return article.getTitle() != null;
    }

    private boolean validateContent() {
        return article.getContent() != null;
    }

    private boolean validateResponsiblePersonIds() {
        if (article.getResponsiblePersonIds() == null) {
            return true;
        } else if (article.getResponsiblePersonIds().isEmpty()) {
            return true;
        } else {
            for (int id : article.getResponsiblePersonIds()) {
                if (personClient.getPersonByID(id).equals(Optional.empty())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validateAuthorId() {
        return !personClient.getPersonByID(article.getAuthorId()).equals(Optional.empty());
    }

    private boolean validateAppointmentId() {
        if (article.getAppointmentId() == 0) {
            return true;
        } else return !appointmentsClient.getAppointmentById(article.getAppointmentId()).equals(Optional.empty());
    }

    private boolean validateFacultyName() {
        return facultyClient.isFacultyNameValid(article.getFacultyName());
    }

    private boolean validateDate() {
        if (article.getDate() == null) {
            return false;
        } else return article.getDate().isBefore(LocalDateTime.now()) || article.getDate().isEqual(LocalDateTime.now());
    }

    private boolean validateLanguage() {
        return article.getLanguage() != null;
    }

    private boolean validatePriority() {
        return article.getPriority() != null;
    }
}
