package de.fherfurt.news.articles.boundary;

import de.fherfurt.appointments.client.transfer.objects.NewsAppointment;
import de.fherfurt.news.articles.boundary.mapper.ArticleToArticlePreviewDtoMapper;
import de.fherfurt.news.articles.boundary.mapper.ArticleToDtoMapper;
import de.fherfurt.news.articles.boundary.mapper.DtoToArticleMapper;
import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.articles.entity.Language;
import de.fherfurt.news.articles.entity.Priority;
import de.fherfurt.news.client.dto.ArticleDto;
import de.fherfurt.news.client.dto.ArticlePreviewDto;
import de.fherfurt.persons.client.transfer.objects.PersonDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class NewsResourceTest {
    Logger logger = Logger.getLogger(this.getClass().getName());
    ModelMapper mapper = new ModelMapper();

    NewsAppointment newsAppointmentDto = new NewsAppointment(1,"Termin",LocalDateTime.of(2022, Month.APRIL,1,12,1),"Webex");

    PersonDto personDto = PersonDto.builder()
            .withId(8)
            .withFirstname("Klaus")
            .withLastname("Heinrichsens")
            .build();

    Set<PersonDto> responsiblePersons = new HashSet<>();


    ArticleDto articleDto = ArticleDto.builder()
            .withId(5)
            .withTitle("Best Title")
            .withContent("Hello Content")
            .withResponsiblePersons(Set.of(personDto))
            .withAuthor(personDto)
            .withAppointment(newsAppointmentDto)
            .withFacultyName("Angewande Informatik")
            .withKeywords(Set.of("Anouncment","Computers"))
            .withDate(LocalDateTime.of(2022,7,20,15,0))
            .withLanguage(ArticleDto.LanguageDto.DE)
            .withPriority(ArticleDto.PriorityDto.HIGH)
            .build();

    Article article = Article.builder()
            .withId(5)
            .withTitle("Best Title")
            .withContent("Hello Content")
            .withResponsiblePersonIds(Set.of(8))
            .withAuthorId(8)
            .withAppointmentId(89)
            .withFacultyName("Angewande Informatik")
            .withKeywords(Set.of("Anouncment","Computers"))
            .withDate(LocalDateTime.of(2022,7,20,15,0))
            .withLanguage(Language.DE)
            .withPriority(Priority.HIGH)
            .build();

    @BeforeEach
    void setUp() {
        responsiblePersons.add(personDto);
        articleDto.setResponsiblePersons(responsiblePersons);
        newsAppointmentDto.setId(89);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void mapToArticle() {
        DtoToArticleMapper mapper = new DtoToArticleMapper();

        logger.log(Level.INFO,"Set of Responsible Persons: " + articleDto.getResponsiblePersons());

        Set<Integer> test = articleDto.getResponsiblePersons().stream().map(PersonDto::getId).collect(Collectors.toSet());
        logger.log(Level.INFO,"Set of Responsible Persons: " + test );
        //Set<Integer> personIds = new Has
        //typeMap.addMappings(mapper -> mapper.using(new PersonConverter()).map(ArticleDto::getResponsiblePersons,Article::setResponsiblePersonIds));


/*
        logger.log(Level.INFO,articleDto.getId()+" : "+ testArticle.getId());
        logger.log(Level.INFO,articleDto.getTitle()+"");
        logger.log(Level.INFO,articleDto.getContent()+"");
        logger.log(Level.INFO,"Set of Responsible Person Ids (testArticle): " +articleDto.getResponsiblePersons());
        logger.log(Level.INFO,articleDto.getAuthor()+"");
        logger.log(Level.INFO,articleDto.getAppointment()+"");
        logger.log(Level.INFO,articleDto.getFacultyName()+"");
        logger.log(Level.INFO,articleDto.getKeywords()+"");
        logger.log(Level.INFO,articleDto.getDate()+"");
        logger.log(Level.INFO,articleDto.getLanguage()+"");
        logger.log(Level.INFO,articleDto.getPriority()+"");
*/
        Article testArticle = mapper.map(articleDto);
        logger.log(Level.INFO,article.getId()+" : "+testArticle.getId()+"");
        logger.log(Level.INFO,article.getTitle() + " : "+ testArticle.getTitle()+"");
        logger.log(Level.INFO,article.getContent() + " : "+ testArticle.getContent()+"");
        logger.log(Level.INFO,article.getResponsiblePersonIds()+ " : "+ testArticle.getResponsiblePersonIds());
        logger.log(Level.INFO,article.getAuthorId() + " : "+ testArticle.getAuthorId()+"");
        logger.log(Level.INFO,article.getAppointmentId() + " : "+ testArticle.getAppointmentId()+"");
        logger.log(Level.INFO,article.getFacultyName() + " : "+ testArticle.getFacultyName()+"");
        logger.log(Level.INFO,article.getKeywords() + " : "+ testArticle.getKeywords()+"");
        logger.log(Level.INFO,article.getDate() + " : "+ testArticle.getDate()+"");
        logger.log(Level.INFO,article.getLanguage() + " : "+ testArticle.getLanguage()+"");
        logger.log(Level.INFO,article.getPriority() + " : "+ testArticle.getPriority()+"");
        Assertions.assertTrue(article.equals(testArticle));
    }

    @Test
    void mapToArticlePreview(){
        ArticleToArticlePreviewDtoMapper mapper = new ArticleToArticlePreviewDtoMapper();

        ArticlePreviewDto expected = ArticlePreviewDto.builder()
                .withId(5)
                .withTitle("Best Title")
                .withContent("Hello Content")
                .withFacultyName("Angewande Informatik")
                .withKeywords(Set.of("Anouncment","Computers"))
                .withDate(LocalDateTime.of(2022,7,20,15,0))
                .build();

        ArticlePreviewDto previewDto = mapper.map(article);

        Assertions.assertTrue(expected.equals(previewDto));
    }

    @Test
    void mapArticleToArticleDto(){
        ArticleToDtoMapper mapper = new ArticleToDtoMapper();

        ArticleDto articleDto = mapper.map(article);

        ArticleDto expected = ArticleDto.builder()
                .withId(5)
                .withTitle("Best Title")
                .withContent("Hello Content")
                .withResponsiblePersons(Set.of(personDto))
                .withAuthor(personDto)
                .withAppointment(newsAppointmentDto)
                .withFacultyName("Angewande Informatik")
                .withKeywords(Set.of("Anouncment","Computers"))
                .withDate(LocalDateTime.of(2022,7,20,15,0))
                .withLanguage(ArticleDto.LanguageDto.DE)
                .withPriority(ArticleDto.PriorityDto.HIGH)
                .build();

        logger.log(Level.INFO,expected.getId()+" : "+articleDto.getId()+"");
        logger.log(Level.INFO,expected.getTitle() + " : "+ articleDto.getTitle()+"");
        logger.log(Level.INFO,expected.getContent() + " : "+ articleDto.getContent()+"");
        logger.log(Level.INFO,expected.getResponsiblePersons()+ " : "+ articleDto.getResponsiblePersons());
        logger.log(Level.INFO,expected.getAuthor() + " : "+ articleDto.getAuthor()+"");
        logger.log(Level.INFO,expected.getAppointment() + " : "+ articleDto.getAppointment()+"");
        logger.log(Level.INFO,expected.getFacultyName() + " : "+ articleDto.getFacultyName()+"");
        logger.log(Level.INFO,expected.getKeywords() + " : "+ articleDto.getKeywords()+"");
        logger.log(Level.INFO,expected.getDate() + " : "+ articleDto.getDate()+"");
        logger.log(Level.INFO,expected.getLanguage() + " : "+ articleDto.getLanguage()+"");
        logger.log(Level.INFO,expected.getPriority() + " : "+ articleDto.getPriority()+"");


    }
    @Test
    void save() {

    }

    @Test
    void delete() {
    }

    @Test
    void getArticlePreviews() {
    }

    @Test
    void getArticle() {
    }

}