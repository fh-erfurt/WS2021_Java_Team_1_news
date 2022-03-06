package de.fherfurt.news.articles.business;


import de.fherfurt.news.articles.business.modules.ArticleSearchModule;
import de.fherfurt.news.articles.business.modules.ArticleSortModule;
import de.fherfurt.news.articles.entity.ArticleDetails;
import de.fherfurt.news.articles.entity.ArticleRepository;
import de.fherfurt.news.articles.entity.DevArticleRepository;
import de.fherfurt.news.core.persistance.PreviewRequest;

import java.util.Set;

/**
 * TODO handle things like empty keywords and other cases
 *
 *  @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
public class ArticleRequestHandler {

    private ArticleRequestHandler instance;

    public ArticleRequestHandler getInstance(){
        if (this.instance == null){
            initialize();
        }
        return this.instance;
    }

    private void initialize(){
        this.repository = new DevArticleRepository();
        this.instance = new ArticleRequestHandler();

    }

    public Set<ArticleDetails> handleRequest(PreviewRequest request) {
        //fetching current articles and filtering them
        Set<ArticleDetails> filteredArticles = repository.filter(request.facultyName);
        //searching on filtered articles
        Set<ArticleDetails> searchedArticles = new ArticleSearchModule(request.keyword, filteredArticles).search();
        //sorting those
        return new ArticleSortModule(request.sortSettings,searchedArticles.stream().toList()).sort();
    }

    private ArticleRepository repository;
}
