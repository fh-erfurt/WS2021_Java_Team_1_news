package de.fherfurt.news.articles.entity;

import de.fherfurt.news.core.persistance.Entry;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * important Information of an Article
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */

public @Data class ArticlePreview extends Entry {
    private String title;
    private int clicks;
    private String facultyName;
    private Set<String> keywords;
    private Date date;
    private boolean wasModified;
    Language language;
    Priority priority;
}