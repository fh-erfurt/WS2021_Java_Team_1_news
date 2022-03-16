package de.fherfurt.news.core.persistance.dev;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO add comments
 *
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
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


    @Test
    void save() {

        Article testArticle = article1;

        database.save(testArticle);

        Article savedTestArticle = database.getMap().get(testArticle.getId());

        Assertions.assertEquals(article1, savedTestArticle);
    }

    @Test
    void saveArticleWithSameId() {

        Article testArticle1 = article1;
        Article testArticle2 = article1;

        database.save(testArticle1);
        database.save(testArticle2);

        Article savedTestArticle2 = database.getMap().get(testArticle2.getId());

        Assertions.assertEquals(2, savedTestArticle2.getId());
    }

    @Test
    void saveArticleWithCustomId() {

        Article customIdArticle = Article.builder()
                .withId(25)
                .withTitle("Custom ID Article")
                .withContent("Placeholder")
                .build();

        database.save(article1);
        database.save(customIdArticle);

        Assertions.assertEquals(25, customIdArticle.getId());
    }

    @Test
    void saveArticleWithNoId() {

        Article noIdArticle = Article.builder().build();

        database.save(noIdArticle);

        //1 = first defaul id
        Assertions.assertEquals(1, noIdArticle.getId());
    }

    @Test
    void saveArticleWithNoIdWithAlreadyExistingArticles() {

        Article noIdArticle = Article.builder().build();

        database.save(article1);
        database.save(article2);
        database.save(article3);
        database.save(noIdArticle);

        Assertions.assertEquals(4, noIdArticle.getId());
    }

    @Test
    void createNewId() {
        database.save(article1);
        database.save(article2);
        database.save(article3);
        int newId = database.createNewId();
        Assertions.assertEquals(4, newId);
    }

    @Test
    void delete() {
        database.save(article1);
        database.save(article2);
        database.save(article3);

        try {
            database.delete(3);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        Assertions.assertNull(database.map.get(3));
    }

    @Test
    void deleteWithNoOccurringId() {
        int idToTest = 25;

        database.save(article1);

        EntryNotFoundException e = Assertions.assertThrows(EntryNotFoundException.class, () -> database.delete(idToTest));

        Assertions.assertEquals("Entry not found with the Id: " + idToTest, e.getMessage());
    }

    @Test
    void findBy() {
        database.save(article1);
        Article foundArticle = null;
        try {
            foundArticle = database.findBy(1);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        Assertions.assertEquals(article1, foundArticle);
    }

    @Test
    void findByNotOccurringId() {
        int idToTest = 25;

        database.save(article1);

        EntryNotFoundException e = Assertions.assertThrows(EntryNotFoundException.class, () -> database.findBy(idToTest));

        Assertions.assertEquals("Entry not found with the Id: " + idToTest, e.getMessage());
    }

    @Test
    void fetchAll() {
        database.save(article1);
        database.save(article2);
        database.save(article3);

        Set<Article> fetchedArticles = database.fetchAll();

        Set<Article> expectedArticles =new HashSet<>(Arrays.asList(article1, article2, article3));

        Assertions.assertEquals(expectedArticles,fetchedArticles);
    }

    @Test
    void fetchAllWithNoStoredArticles() {
        Set<Article> fetchedArticles = database.fetchAll();

        Assertions.assertEquals(Collections.emptySet(), fetchedArticles);
    }

}