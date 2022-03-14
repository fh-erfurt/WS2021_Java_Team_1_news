package de.fherfurt.news.articles.business.modules;

import lombok.Data;

/**
 * this class is used to encapsulate all attributes in regard to sorting
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
public @Data class SortSettings {
    public final SortDirection sortDirection;
    public final SortPriority sortPriority;
}
