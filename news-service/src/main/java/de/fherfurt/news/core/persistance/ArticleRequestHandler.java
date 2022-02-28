package de.fherfurt.news.core.persistance;


import de.fherfurt.news.articles.entity.ArticleDetails;
import de.fherfurt.news.articles.entity.ArticleRepository;
import de.fherfurt.news.articles.entity.DevArticleRepository;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TODO handle things like empty keywords and other cases
 *
 *  @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
public class ArticleRequestHandler {

    private ArticleRequestHandler instance;
    private ArticleRequestHandler(){

    }

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
        Set<ArticleDetails> sortedArticles = searchedArticles.stream()
                .sorted(new ArticleSortComparator(request.sortSettings))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return sortedArticles;
    }

    private ArticleRepository repository;
}
