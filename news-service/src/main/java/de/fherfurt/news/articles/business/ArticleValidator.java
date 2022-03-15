package de.fherfurt.news.articles.business;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.appointments.client.*;
import de.fherfurt.faculties.client.*;
import de.fherfurt.persons.client.*;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * not validated:
 * clicks, keywords, wasModified, language, priority
 *
 *  @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public class ArticleValidator {

    private Article article;

    //implementation of the interfaces
    private AppointmentsService appointmentsService = new DevAppointmentsService();
    private FacultiesService facultiesService = new DevFacultiesService();
    private PersonsService personsService = new DevPersonsService();

    public boolean validateArticle(Article article){
        this.article = article;

        if(validateTitle() && validateContent() && validateResponsiblePersonIds() && validateAuthorId() && validateAppointmentId() && validateFacultyName() && validateDate()){
            return true;
        }
        return false;
    }

    private boolean validateTitle(){
        if(article.getTitle() == null){
            return false;
        }
        return true;
    }

    private boolean validateContent(){
        if(article.getContent() == null){
            return false;
        }
        return true;
    }

    private boolean validateResponsiblePersonIds(){
        if(article.getResponsiblePersonIds() == null){
            return true;
        }
        else if(article.getResponsiblePersonIds().isEmpty()){
            return true;
        }
        else {
            for ( int id : article.getResponsiblePersonIds()){
                if(personsService.getPersonByID(id).equals(Optional.empty())){
                    return false;
                }
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
        if(article.getAppointmentId() == 0){
            return true;
        }
        else if(appointmentsService.getAppointmentById(article.getAppointmentId()).equals(Optional.empty())){
            return false;
        }
        return true;
    }

    private boolean validateFacultyName(){
        return facultiesService.isFacultynameValid(article.getFacultyName());
    }

    private boolean validateDate(){
        if(article.getDate() == null){
            return false;
        }
        else if(article.getDate().isBefore(LocalDateTime.now()) || article.getDate().isEqual(LocalDateTime.now())){
            return true;
        }
        return false;
    }
}
