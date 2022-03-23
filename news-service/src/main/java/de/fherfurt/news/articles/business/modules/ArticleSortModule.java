package de.fherfurt.news.articles.business.modules;

import de.fherfurt.news.articles.business.modules.entity.SortSettings;
import de.fherfurt.news.articles.entity.Article;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * this class is used for sorting articles
 * it receives {@link SortSettings} to define how the given articles should be sorted
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@AllArgsConstructor
public class ArticleSortModule {

    public final SortSettings sortSettings;
    public final List<Article> articleList;

    public List<Article> sort() {
        //guard clause to return an empty list immediately
        if (articleList.isEmpty()) return Collections.emptyList();

        articleList.sort(new ArticleSortComparator(sortSettings));
        return new LinkedList<>(articleList);
    }
}
