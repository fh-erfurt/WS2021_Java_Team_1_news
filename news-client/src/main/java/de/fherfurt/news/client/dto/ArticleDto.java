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
    LanguageDto language;
    PriorityDto priority;

    @Builder(setterPrefix = "with")
    public ArticleDto( int id,
                    String title,
                    String content,
                    Set<PersonDto> responsiblePersons,
                    PersonDto author,
                    NewsAppointment appointment,
                    String facultyName,
                    Set<String> keywords,
                    LocalDateTime date,
                    LanguageDto language,
                    PriorityDto priority){
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

    public enum LanguageDto{
        DE,EN
    }

    public enum PriorityDto{
        CRITICAL,
        HIGH,
        NORMAL
    }
}
