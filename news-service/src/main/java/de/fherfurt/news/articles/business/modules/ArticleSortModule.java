package de.fherfurt.news.articles.business.modules;

import de.fherfurt.news.articles.entity.ArticleDetails;
import de.fherfurt.news.articles.entity.ArticleSortComparator;
import de.fherfurt.news.core.persistance.SortSettings;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO maybe use Regex for searching
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@AllArgsConstructor
public class ArticleSortModule {

    public final SortSettings sortSettings;
    public final List<ArticleDetails> articleList;

    public Set<ArticleDetails> sort(){
        articleList.sort(new ArticleSortComparator(sortSettings));
        return new HashSet<>(articleList);
    }
}
