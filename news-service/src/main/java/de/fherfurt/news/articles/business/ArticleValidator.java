package de.fherfurt.news.articles.business;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.appointments.client.*;
import de.fherfurt.faculties.client.*;
import de.fherfurt.persons.client.*;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * not validated:
 * clicks, keywords, wasModified
 * <p>
 * Validates an article to make sure there is no wrong information like a non-existent author ur a publishing date in the future.
 *
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public class ArticleValidator {

    private Article article;

    /**
     * implementation of the DevImplementation Interfaces. Must be replaced with actual interfaces for integration.
     */
    private AppointmentsClient appointmentsClient = new DevAppointmentsClient();
    private FacultiesService facultiesService = new DevFacultiesService();
    private PersonClient personClient = new DevPersonClient();

    public boolean validateArticle(Article article) {
        this.article = article;

        return  (
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
        return facultiesService.isFacultynameValid(article.getFacultyName());
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
