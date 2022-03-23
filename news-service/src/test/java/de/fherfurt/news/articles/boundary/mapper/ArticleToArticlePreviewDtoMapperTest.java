package de.fherfurt.news.articles.boundary.mapper;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.articles.entity.Language;
import de.fherfurt.news.articles.entity.Priority;
import de.fherfurt.news.client.dto.ArticlePreviewDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArticleToArticlePreviewDtoMapperTest {

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
}