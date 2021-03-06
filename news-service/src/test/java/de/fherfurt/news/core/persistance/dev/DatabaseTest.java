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
 * this class tests the functionality of the dev implementation {@link Database}
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
class DatabaseTest {

    //logger to log errors to the console
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    //setting up some values which are used in the tests
    private final Database database = new Database();

    Article article1 = Article.builder()
            .withId(1)
            .withTitle("article 1")
            .withContent("this is some test content")
            .build();

    Article article2 = Article.builder()
            .withId(2)
            .withTitle("article 2")
            .withContent("this is some test content")
            .build();

    Article article3 = Article.builder()
            .withId(3)
            .withTitle("article 3")
            .withContent("this is some test content")
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

        Article testArticle = article1;

        Assertions.assertEquals("this is some test content", testArticle.getContent());

        Article overwritingArticle = Article.builder().withId(1).withContent("this is the new Content").build();

        database.save(testArticle);
        database.save(overwritingArticle);

        Article overwrittenArticle = database.getMap().get(overwritingArticle.getId());

        Assertions.assertEquals("this is the new Content", overwrittenArticle.getContent());
    }

    @Test
    void saveArticleWithCustomId() {
        int testId = 25;

        Article customIdArticle = Article.builder()
                .withId(testId)
                .withTitle("Custom ID Article")
                .withContent("Placeholder")
                .build();

        database.save(article1);
        database.save(customIdArticle);

        Assertions.assertEquals(testId, customIdArticle.getId());
    }

    @Test
    void saveArticleWithNoId() {

        Article noIdArticle = Article.builder().build();

        database.save(noIdArticle);

        //1 = first default id
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
    void deleteWithNoExistingId() {
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
    void findByNotExistingId() {
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

        Set<Article> expectedArticles = new HashSet<>(Arrays.asList(article1, article2, article3));

        Assertions.assertEquals(expectedArticles, fetchedArticles);
    }

    @Test
    void fetchAllWithNoStoredArticles() {
        Set<Article> fetchedArticles = database.fetchAll();

        Assertions.assertEquals(Collections.emptySet(), fetchedArticles);
    }
}