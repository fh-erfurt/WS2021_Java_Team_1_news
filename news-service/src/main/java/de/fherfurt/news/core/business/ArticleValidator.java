package de.fherfurt.news.core.business;

import de.fherfurt.news.articles.entity.Article;

public class ArticleValidator {


    /**
     * not validated:
     * clicks, keywords, wasModified, language, priority
     * @return
     */
    public boolean validateArticle(Article article){
        if(article.getTitle().equals(null) || article.getContent().equals(null)){
            return false;
        }
    }

    private boolean validateResponsiblePersonIds(Article article){
        if()
    }

    private boolean validateAuthorId(Article article){
        if()
    }

    private boolean validateAppointmentId(Article article){

    }

    private boolean validateFacultyName(Article article){

    }

    private boolean validateDate(Article article){

    }
}
