package de.fherfurt.news.articles.business.modules.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * this class is used to encapsulate all attributes in regard to sorting
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@NoArgsConstructor
@AllArgsConstructor
public @Data class SortSettings {
    public SortDirection sortDirection;
    public SortPriority sortPriority;
}
