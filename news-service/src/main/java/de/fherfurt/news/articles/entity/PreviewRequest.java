package de.fherfurt.news.articles.entity;

import de.fherfurt.news.articles.business.modules.entity.SortSettings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used to encapsulate all requirements of a request as a simple data container
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */

@NoArgsConstructor
@AllArgsConstructor
public @Data class PreviewRequest {
    public String searchTerm;
    public String facultyName;
    public SortSettings sortSettings;
}
