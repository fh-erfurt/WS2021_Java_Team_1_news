package de.fherfurt.news.client.dto;

import de.fherfurt.appointments.client.transfer.objects.NewsAppointment;
import de.fherfurt.persons.client.transfer.objects.PersonDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * this is the article object which contains objects like {@link PersonDto} and {@link NewsAppointment}
 * this class should be used in the client side
 *
 * @author Dennis Rinck <dennis.rinck@fh-erfurt.de>
 */
@Getter
@Setter
@NoArgsConstructor
public class ArticleDto {

    private int id;
    private String title;
    private String content;
    private Set<PersonDto> responsiblePersons;
    private List<BufferedImage> imageList;
    private PersonDto author;
    private NewsAppointment appointment;
    private int clicks;
    private String facultyName;
    private Set<String> keywords;
    private LocalDateTime date;
    private boolean wasModified;
    private LanguageDto language;
    private PriorityDto priority;

    @Builder(setterPrefix = "with")
    public ArticleDto(int id,
                      String title,
                      String content,
                      Set<PersonDto> responsiblePersons,
                      PersonDto author,
                      NewsAppointment appointment,
                      String facultyName,
                      Set<String> keywords,
                      LocalDateTime date,
                      LanguageDto language,
                      PriorityDto priority) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.responsiblePersons = responsiblePersons;
        this.author = author;
        this.appointment = appointment;
        this.facultyName = facultyName;
        this.keywords = keywords;
        this.date = date;
        this.language = language;
        this.priority = priority;
    }

    public enum LanguageDto {
        DE, EN
    }

    public enum PriorityDto {
        CRITICAL,
        HIGH,
        NORMAL
    }

    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) return false;

        ArticleDto otherArticle = (ArticleDto) object;


        //check all values if they are equal
        return this.getId() == otherArticle.getId()
                && this.title.equals(otherArticle.title) || (this.title == null && otherArticle.title == null)
                && this.content.equals(otherArticle.content) || (this.content == null && otherArticle.content == null)
                && this.responsiblePersons.equals(otherArticle.responsiblePersons)
                && this.author == otherArticle.author
                && this.appointment == otherArticle.appointment
                && this.facultyName.equals(otherArticle.facultyName) || (this.facultyName == null && otherArticle.facultyName == null)
                && this.keywords.equals(otherArticle.keywords) || (this.keywords == null && otherArticle.keywords == null)
                && this.date.equals(otherArticle.date) || (this.date == null && otherArticle.date == null)
                && this.language == otherArticle.language
                && this.priority == otherArticle.priority;
    }
}
