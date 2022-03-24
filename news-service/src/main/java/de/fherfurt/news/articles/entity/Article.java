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
 * data container of an article
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
    public Article(int id,
                   String title,
                   String content,
                   Set<Integer> responsiblePersonIds,
                   int authorId,
                   int appointmentId,
                   String facultyName,
                   Set<String> keywords,
                   LocalDateTime date,
                   Language language,
                   Priority priority) {
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

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) return false;

        Article otherArticle = (Article) object;

        //check all values if they are equal
        return this.getId() == otherArticle.getId()
                //check title
                && checkObject(this.title, otherArticle.title)
                //check content
                && checkObject(this.content, otherArticle.content)
                //check responsiblePersonsIds
                && checkObject(this.responsiblePersonIds, otherArticle.responsiblePersonIds)
                //check authorId
                && this.authorId == otherArticle.authorId
                //check appointmentId
                && this.appointmentId == otherArticle.appointmentId
                //check facultyName
                && checkObject(this.facultyName, otherArticle.facultyName)
                //check keyWords
                && checkObject(this.keywords, otherArticle.keywords)
                //check date
                && checkObject(this.date, otherArticle.date)
                // check language
                && checkObject(this.language, otherArticle.language)
                // check priority
                && checkObject(this.priority, otherArticle.priority);
    }

    /**
     * helper function to check if
     * both objects are null
     * both objects are not null -> check if both are equal
     *
     * @param obj1 object1
     * @param obj2 object2
     * @return return if both are equal
     */
    private boolean checkObject(Object obj1, Object obj2) {
        return (obj1 == null && obj2 == null)
                || (obj1 != null && obj2 != null)
                && obj1.equals(obj2);
    }

}

