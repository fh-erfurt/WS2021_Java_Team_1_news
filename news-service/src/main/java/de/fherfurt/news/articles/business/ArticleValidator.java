package de.fherfurt.news.articles.business;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.appointments.client.*;
import de.fherfurt.faculties.client.*;
import de.fherfurt.persons.client.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class ArticleValidator {

    private Article article;

    private AppointmentsService appointmentsService = new DevAppointmentsService();
    private FacultiesService facultiesService = new DevFacultiesService();
    private PersonsService personsService = new DevPersonsService();

    /**
     * not validated:
     * clicks, keywords, wasModified, language, priority
     * @return
     */
    public boolean validateArticle(Article article){
        this.article = article;

        if(article.getTitle().equals(null) || article.getContent().equals(null)){
            return false;
        }
    }

    private boolean validateResponsiblePersonIds(){
        for ( int id : article.getResponsiblePersonIds()){
            if(personsService.getPersonByID(id).equals(Optional.empty())){
                return false;
            }
        }
        return true;
    }

    private boolean validateAuthorId(){
        if(personsService.getPersonByID(article.getAuthorId()).equals(Optional.empty())){
            return false;
        }
        return true;
    }

    private boolean validateAppointmentId(){
        if(appointmentsService.getAppointmentById(article.getAppointmentId()).equals(Optional.empty())){
            return false;
        }
        return true;
    }

    private boolean validateFacultyName(){
        return facultiesService.isFacultynameValid(article.getFacultyName());
    }

    private boolean validateDate(){
        if(Duration.between(LocalDate.now(), article.getDate()).compareTo(Duration.between(LocalDate.now(), LocalDate.now().plusYears(3))) > 0
                || Duration.between(LocalDate.now(), article.getDate()).compareTo(Duration.between(LocalDate.now(), LocalDate.now().minusMonths(1))) < 0){
            return false;
        }
        return true;
    }
}
