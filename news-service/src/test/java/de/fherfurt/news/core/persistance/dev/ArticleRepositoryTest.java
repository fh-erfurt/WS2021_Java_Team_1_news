package de.fherfurt.news.core.persistance.dev;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class ArticleRepositoryTest {

    ArticleRepository articleRepository;
    private final Logger logger = Logger.getLogger(this.getClass().getName());


    Article article1 = Article.builder()
            .withId(1)
            .withTitle("Artikel 1")
            .withContent("das ist Test Conten")
            .build();

    Article article2 = Article.builder()
            .withId(2)
            .withTitle("Artikel 2")
            .withContent("das ist Test Conten")
            .build();

    Article article3 = Article.builder()
            .withId(3)
            .withTitle("Artikel 3")
            .withContent("das ist Test Conten")
            .build();

    List<Article> articleList = new LinkedList<>();

    @BeforeEach
    void setUp() {

        articleRepository = ArticleRepository.getInstance();

        articleList.addAll(Arrays.asList(article1,article2,article3));

        for (Article article: articleList ) {
            articleRepository.save(article);
        }
    }
    @AfterEach
    void tearDown(){
        for (Article article: articleRepository.fetchAll()) {
            try {
                articleRepository.delete(article.getId());
            }catch (EntryNotFoundException e){
                logger.log(Level.WARNING,e.getMessage());
            }
        }
    }

    @Test
    void findBy() {
        int testId = 1;
        Article testArticle = Article.builder().build();
        try{
            testArticle = articleRepository.findBy(testId);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING,e.getMessage());
        }
        Assertions.assertEquals(article1,testArticle);
    }

    @Test
    void save() {

    }

    @Test
    void delete() {
    }

    @Test
    void fetchAll() {
    }
}