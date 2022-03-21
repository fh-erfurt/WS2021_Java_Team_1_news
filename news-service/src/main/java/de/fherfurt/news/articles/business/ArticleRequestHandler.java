package de.fherfurt.news.articles.business;


import de.fherfurt.news.articles.business.modules.ArticleFilterModule;
import de.fherfurt.news.articles.business.modules.ArticleSearchModule;
import de.fherfurt.news.articles.business.modules.ArticleSortModule;

import de.fherfurt.news.articles.business.modules.entity.SortSettings;
import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.articles.entity.PreviewRequest;
import de.fherfurt.news.articles.entity.RequestType;
import de.fherfurt.news.core.persistance.Repository;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * TODO handle things like empty keywords and other cases
 * all returned articles are always sorted, filtering and searching is optional
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@AllArgsConstructor
public class ArticleRequestHandler {

    private final Repository<Article> repository;

    /**
     * find certain articles with given parameters {@link PreviewRequest}
     *
     * @param request Request contains the facultyName,searchTerm and the sortSettings
     * @return
     */
    public List<Article> handleRequest(PreviewRequest request, RequestType requestType) {
        //fetching all current articles
        Set<Article> allCurrentArticles = repository.fetchAll();

        //guard clause to return an empty list immediately
        if (allCurrentArticles.isEmpty()) return Collections.emptyList();

        Set<Article> articles = Collections.emptySet();

        //checks if it has to filter articles
        if (requestType == RequestType.FILTER_SORT) {
            //filtering fetched articles
            articles = new ArticleFilterModule(request.facultyName, allCurrentArticles).filter();
        }
        //checks if it has to search through the filtered articles
        else if (requestType == RequestType.FILTER_SEARCH_SORT){
            //searching on filtered articles
            articles = new ArticleSearchModule(request.searchTerm, articles).search();
        }
        return sortArticles(request.sortSettings, articles);
    }

    /**
     *
     * @param sortSettings
     * @param articles
     * @return
     */
    private List<Article> sortArticles(SortSettings sortSettings, Set<Article> articles) {
        return new ArticleSortModule(sortSettings, articles.stream().toList()).sort();
    }
}
