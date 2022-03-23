package de.fherfurt.news.articles.business.modules.entity;

import lombok.Data;

/**
 * This class is used to encapsulate all requirements of a request as a simple data container
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */

public @Data class PreviewRequest {
    public final String searchTerm;
    public final String facultyName;
    public final SortSettings sortSettings;
}
