package de.fherfurt.news.articles.business;


import de.fherfurt.news.articles.business.modules.*;
import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.core.persistance.dev.ArticleRepository;
import de.fherfurt.news.core.persistance.Repository;

import java.util.Set;

/**
 * TODO handle things like empty keywords and other cases
 *
 *  @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
public class ArticleRequestHandler {

    private ArticleRequestHandler instance;
    private Repository<Article> repository = new ArticleRepository();

    public ArticleRequestHandler getInstance(){
        if (this.instance == null){
            initialize();
        }
        return this.instance;
    }

    private void initialize(){
        this.repository = new ArticleRepository();
        this.instance = new ArticleRequestHandler();
    }

    /**
     * find certain articles with given parameters {@link PreviewRequest}
     *
     * @param request Request contains the facultyName,searchTerm and the sortSettings
     * @return
     */
    public Set<Article> handleRequest(PreviewRequest request) {
        //fetching all current articles
        Set<Article> allCurrentArticles = repository.fetchAll();

        //TODO das muss abgefangen werden
        //guard clause to prevent unnecessary computing
        if (allCurrentArticles.isEmpty()) return null;

        //fetching current articles and filtering them
        Set<Article> filteredArticles = new ArticleFilterModule(request.facultyName, allCurrentArticles).filter();
        //searching on filtered articles
        Set<Article> searchedArticles = new ArticleSearchModule(request.searchTerm, filteredArticles).search();
        //sorting those and returning them
        return new ArticleSortModule(request.sortSettings,searchedArticles.stream().toList()).sort();
    }

}
