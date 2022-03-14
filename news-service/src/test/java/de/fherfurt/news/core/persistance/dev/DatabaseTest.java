package de.fherfurt.news.core.persistance.dev;

import de.fherfurt.news.articles.entity.Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {


    private final Database database = new Database();
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



    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {

        Article testArticle = article1;

        database.save(testArticle);

        Article savedTestArticle = database.getMap().get(testArticle.getId());

        Assertions.assertEquals(testArticle,savedTestArticle);
    }

    @Test
    void delete() {
    }

    @Test
    void findBy() {
    }

    @Test
    void fetchAll() {
    }

    @Test
    void getMap() {
    }
}