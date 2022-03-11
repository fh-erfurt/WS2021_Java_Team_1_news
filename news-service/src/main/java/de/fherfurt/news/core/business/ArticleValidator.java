package de.fherfurt.news.core.business;

import de.fherfurt.news.articles.entity.Article;

public class ArticleValidator {
    private Article article;


    /**
     * not validated:
     * clicks, keywords, wasModified, language, priority
     * @return
     */
    public boolean validateArticle(){
        if(article.getTitle().equals(null) || article.getContent().equals(null)){
            return false;
        }
    }

    private boolean validateResponsiblePersonIds(){
        if()
    }

    private boolean validateAuthorId(){
        if()
    }

    private boolean validateAppointmentId(){

    }

    private boolean validateFacultyName(){

    }

    private boolean validateDate(){

    }
}
