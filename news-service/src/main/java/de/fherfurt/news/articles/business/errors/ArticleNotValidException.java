package de.fherfurt.news.articles.business.errors;

public class ArticleNotValidException extends Exception{
    public ArticleNotValidException(String message){
        super(message);
    }
}
