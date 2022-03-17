package de.fherfurt.news.articles.business.modules;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.articles.entity.Priority;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@AllArgsConstructor
public class ArticleSortComparator implements Comparator<Article> {

    public final SortSettings sortSettings;

    @Override
    public int compare(Article article1, Article article2) {

        int result = 0;
        int sortDirection = (sortSettings.sortDirection == SortDirection.ASC) ? 1 : -1;

        switch (sortSettings.sortPriority) {
            case DATE -> result = checkDate(article1.getDate(),article2.getDate());
            case TITLE -> result = checkTitle(article1.getTitle(),article2.getTitle());
            case CLICKS -> result = article2.getClicks() - article1.getClicks();
            case PRIORITY -> result = checkPriority(article1.getPriority(),article2.getPriority());
        }
        return result * sortDirection;
    }

    private int checkDate(LocalDateTime date1, LocalDateTime date2) {
        //using guard clauses to maintain readability
        if (date1 == null && date2 == null) return 0;
        if (date1 == null) return 1;
        if (date2 == null) return -1;
        //default action
        return date1.compareTo(date2);
    }
    private int checkTitle(String title1, String title2) {
        //using guard clauses to maintain readability
        if (title1 == null && title2 == null) return 0;
        if (title1 == null) return 1;
        if (title2 == null) return -1;
        //default action
        return title1.compareTo(title2);
    }
    private int checkPriority(Priority priority1, Priority priority2) {
        //using guard clauses to maintain readability
        if (priority1 == null && priority2 == null) return 0;
        if (priority1 == null) return 1;
        if (priority2 == null) return -1;
        //default action
        return priority1.compareTo(priority2);
    }
}
