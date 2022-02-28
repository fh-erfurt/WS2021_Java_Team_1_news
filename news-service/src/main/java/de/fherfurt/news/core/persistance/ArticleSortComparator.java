package de.fherfurt.news.core.persistance;

import de.fherfurt.news.articles.entity.ArticleDetails;
import lombok.AllArgsConstructor;

import java.util.Comparator;

/**
 *
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@AllArgsConstructor
public class ArticleSortComparator implements Comparator<ArticleDetails> {

    public final SortSettings sortSettings;

    @Override
    public int compare(ArticleDetails article1,ArticleDetails article2) {

        int result = 0;
        int sortDirection = (sortSettings.sortDirection == SortDirection.ASC) ? 1 : -1;

        switch (sortSettings.sortPriority){
            case DATE -> result = article1.getDate().compareTo(article2.getDate());
            case TITLE -> result = article1.getTitle().compareTo(article2.getTitle());
            case CLICKS -> result = article1.getClicks() - article2.getClicks();
            case LANGUAGE -> result = article1.getLanguage().compareTo(article2.getLanguage());
            case PRIORITY -> result = article1.getPriority().compareTo(article2.getPriority());
        }
        return result * sortDirection;
    }
}
