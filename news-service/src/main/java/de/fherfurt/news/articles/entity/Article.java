package de.fherfurt.news.articles.entity;


import de.fherfurt.news.core.entity.Entry;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Information of an article
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
@Getter
@Setter
@NoArgsConstructor
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

    @Builder(setterPrefix = "with")
    public Article( int id,
                    String title,
                    String content,
                    Set<Integer> responsiblePersonIds,
                    int authorId,
                    int appointmentId,
                    String facultyName,
                    Set<String> keywords,
                    LocalDateTime date,
                    Language language,
                    Priority priority){
        super(id);
        this.title = title;
        this.content = content;
        this.responsiblePersonIds = responsiblePersonIds;
        this.authorId = authorId;
        this.appointmentId = appointmentId;
        this.facultyName = facultyName;
        this.keywords = keywords;
        this.date = date;
        this.language = language;
        this.priority = priority;
    }
}

