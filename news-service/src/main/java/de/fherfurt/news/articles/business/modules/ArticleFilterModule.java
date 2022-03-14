package de.fherfurt.news.articles.business.modules;

import de.fherfurt.news.articles.entity.Article;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO add comments
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@AllArgsConstructor
public class ArticleFilterModule {

    public final String facultyName;
    public final Set<Article> articles;

    public Set<Article> filter(){
        Set<Article> filteredArticles = new HashSet<>();

        for (var article: articles) {
            if (article.getFacultyName().equals(facultyName)) filteredArticles.add(article);
        }
        return filteredArticles;
    }
}
