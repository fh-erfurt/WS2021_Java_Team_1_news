package de.fherfurt.news.articles.business.modules;

import de.fherfurt.news.articles.entity.Article;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is used to filter through a given amount of articles
 * the filtering is dependent on the faculty name
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@AllArgsConstructor
public class ArticleFilterModule {

    public final String facultyName;
    public final Set<Article> articles;

    /**
     * this function filters a given amount of articles after the facultyName
     * it uses the member variables of the class
     *
     * @return filtered articles
     */
    public Set<Article> filter() {
        Set<Article> filteredArticles = new HashSet<>();

        for (var article : articles) {
            if (article.getFacultyName().equals(facultyName)) filteredArticles.add(article);
        }
        return filteredArticles;
    }
}
