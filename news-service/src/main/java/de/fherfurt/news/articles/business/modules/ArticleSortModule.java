package de.fherfurt.news.articles.business.modules;

import de.fherfurt.news.articles.entity.Article;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@AllArgsConstructor
public class ArticleSortModule {

    public final SortSettings sortSettings;
    public final List<Article> articleList;

    public Set<Article> sort(){
        articleList.sort(new ArticleSortComparator(sortSettings));
        return new HashSet<>(articleList);
    }
}
