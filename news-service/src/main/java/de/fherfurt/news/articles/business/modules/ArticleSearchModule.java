package de.fherfurt.news.articles.business.modules;

import de.fherfurt.news.articles.entity.Article;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * TODO maybe use Regex for searching
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@AllArgsConstructor
public class ArticleSearchModule {
    public final String searchTerm;
    public final Set<Article> articles;

    public Set<Article> search() {
        //guard clause to prevent searching for an empty String
        if(searchTerm.isEmpty()) return articles;

        Set<Article> matchingArticles = new HashSet<>();
        for (var article: articles) {
           if(compareArticleAttributesWithSearchTerm(article)) matchingArticles.add(article);
        }
        return matchingArticles;
    }

    private boolean compareArticleAttributesWithSearchTerm(Article article){
        //comparing title
        if(article.getTitle().contains(searchTerm)) return true;
        //comparing if searchKeyword matches with any keyword in an article
        Predicate<String> searchKeywordPredicate = Predicate.isEqual(searchTerm);
        if(article.getKeywords().stream().anyMatch(searchKeywordPredicate)) return true;

        return false;
    }
}