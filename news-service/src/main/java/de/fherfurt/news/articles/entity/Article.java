package de.fherfurt.news.articles.entity;

import de.fherfurt.news.core.persistance.Entry;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * important Information of an Article
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@Getter
@Setter
public class Article extends Entry {
    private String title;
    private String content;
    private Set<Integer> responsiblePersonIds;
    private List<BufferedImage> imageList;
    private int authorId;
    private int appointmentId;
    private int clicks;
    private String facultyName;
    private Set<String> keywords;
    private LocalDateTime date;
    private boolean wasModified;
    Language language;
    Priority priority;
}