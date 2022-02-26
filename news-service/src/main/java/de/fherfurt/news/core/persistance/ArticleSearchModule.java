package de.fherfurt.news.core.persistance;

import de.fherfurt.news.articles.entity.ArticleDetails;
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
    public final String searchKeyword;
    public final Set<ArticleDetails> articles;


    Set<ArticleDetails> search() {
        Set<ArticleDetails> matchingArticles = new HashSet<>();
        for (var article: articles) {
           if(compareArticleAttributesWithKeyword(article)) matchingArticles.add(article);
        }
        return matchingArticles;
    }

    private boolean compareArticleAttributesWithKeyword(ArticleDetails article){
        //comparing title
        if(article.getTitle().contains(searchKeyword)) return true;
        //comparing if searchKeyword matches with any keyword in an article
        Predicate<String> searchKeywordPredicate = Predicate.isEqual(searchKeyword);
        if(article.getKeywords().stream().anyMatch(searchKeywordPredicate)) return true;

        //TODO maybe add other search options

        return false;
    }
}
