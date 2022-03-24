package de.fherfurt.news.client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * this class is used to store relevant information for displaying a preview
 *
 * @author Dennis Rinck <dennis.rinck@fh-erfurt.de>
 */
@Setter
@Getter
@NoArgsConstructor
public class ArticlePreviewDto {

    private int id;
    private String title;
    private int clicks;
    private String facultyName;
    private Set<String> keywords;
    private LocalDateTime date;
    private boolean wasModified;

    @Builder(setterPrefix = "with")
    public ArticlePreviewDto(int id,
                             String title,
                             String facultyName,
                             Set<String> keywords,
                             LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.facultyName = facultyName;
        this.keywords = keywords;
        this.date = date;

    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) return false;

        ArticlePreviewDto otherArticle = (ArticlePreviewDto) object;


        //check all values if they are equal
        return this.getId() == otherArticle.getId()
                && this.title.equals(otherArticle.title) || (this.title == null && otherArticle.title == null)
                && this.facultyName.equals(otherArticle.facultyName) || (this.facultyName == null && otherArticle.facultyName == null)
                && this.keywords.equals(otherArticle.keywords) || (this.keywords == null && otherArticle.keywords == null)
                && this.date.equals(otherArticle.date) || (this.date == null && otherArticle.date == null);
    }
}
