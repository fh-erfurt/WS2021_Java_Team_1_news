package de.fherfurt.news.articles.business.modules;

import de.fherfurt.news.articles.entity.Article;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * this class is used to search through a given amount of articles with a search term
 *
 * current attributes which are searched for (title, keywords)
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@AllArgsConstructor
public class ArticleSearchModule {
    public final String searchTerm;
    public final Set<Article> articles;

    public Set<Article> search() {
        //guard clause to prevent searching for an empty String
        if (searchTerm.isEmpty()) return articles;

        Set<Article> matchingArticles = new HashSet<>();
        for (var article : articles) {
            if (compareArticleAttributesWithSearchTerm(article)) matchingArticles.add(article);
        }
        return matchingArticles;
    }


    /**
     * compare certain variables of current article and check if any contains the search term
     *
     * @param article article to check
     * @return returns true if something matches, otherwise false
     */
    private boolean compareArticleAttributesWithSearchTerm(Article article) {

        //comparing title
        if (article.getTitle() != null && article.getTitle().toLowerCase().contains(searchTerm.toLowerCase()))
            return true;

        //comparing if searchKeyword matches with any keyword in an article
        if (article.getKeywords() != null) {
            Predicate<String> searchKeywordPredicate = Predicate.isEqual(searchTerm);
            return article.getKeywords().stream().anyMatch(searchKeywordPredicate);
        }
        return false;
    }
}
