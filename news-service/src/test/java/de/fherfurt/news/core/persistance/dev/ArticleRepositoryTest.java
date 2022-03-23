package de.fherfurt.news.core.persistance.dev;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this class tests the functionality of the dev implementation {@link ArticleRepository}
 *
 * @author Maximilian Roehr <maximilian.roehr@fh-erfurt.de>
 */
class ArticleRepositoryTest {

    //logger to log errors to the console
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    //setting up some values which are used in the tests
    ArticleRepository articleRepository;

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

    @BeforeEach
    void setUp() {
        articleRepository = ArticleRepository.getInstance();
    }

    //deleting all articles currently stored in the repository
    @AfterEach
    void tearDown() {
        for (Article article : articleRepository.fetchAll()) {
            try {
                articleRepository.delete(article.getId());
            } catch (EntryNotFoundException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
    }

    @Test
    void getInstance() {
        articleRepository = null;

        articleRepository = ArticleRepository.getInstance();

        ArticleRepository articleRepository2 = ArticleRepository.getInstance();

        Assertions.assertEquals(articleRepository, articleRepository2);
    }

    @Test
    void save() {
        Article testArticle = article1;

        articleRepository.save(testArticle);

        Article fetchedArticle = null;

        try {
            fetchedArticle = articleRepository.findBy(1);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        Assertions.assertEquals(article1, fetchedArticle);
    }

    @Test
    void saveArticleWithSameId() {

        Article testArticle = article1;

        Assertions.assertEquals("this is some test content", testArticle.getContent());

        Article overwritingArticle = Article.builder().withId(1).withContent("this is the new Content").build();

        articleRepository.save(testArticle);
        articleRepository.save(overwritingArticle);

        Article overwrittenArticle = Article.builder().build();
        try {
            overwrittenArticle = articleRepository.findBy(overwritingArticle.getId());
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

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

        articleRepository.save(article1);
        articleRepository.save(customIdArticle);

        Assertions.assertEquals(testId, customIdArticle.getId());
    }

    @Test
    void saveArticleWithNoId() {

        Article noIdArticle = Article.builder().build();

        articleRepository.save(noIdArticle);

        //1 = first default id
        Assertions.assertEquals(1, noIdArticle.getId());
    }

    @Test
    void saveArticleWithNoIdWithAlreadyExistingArticles() {

        Article noIdArticle = Article.builder().build();

        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);
        articleRepository.save(noIdArticle);

        Assertions.assertEquals(4, noIdArticle.getId());
    }

    @Test
    void findBy() {
        int testId = 1;

        Article testArticle = Article.builder().build();
        articleRepository.save(testArticle);

        try {
            testArticle = articleRepository.findBy(testId);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        Assertions.assertEquals(article1.getId(), testArticle.getId());
    }

    @Test
    void findByNotExistingId() {
        int idToTest = 25;

        articleRepository.save(article1);

        EntryNotFoundException e = Assertions.assertThrows(EntryNotFoundException.class, () -> articleRepository.findBy(idToTest));

        Assertions.assertEquals("Entry not found with the Id: " + idToTest, e.getMessage());
    }

    @Test
    void delete() {
        int idToTest = 3;

        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);

        try {
            articleRepository.delete(idToTest);
        } catch (EntryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        EntryNotFoundException e = Assertions.assertThrows(EntryNotFoundException.class, () -> articleRepository.findBy(idToTest));

        Assertions.assertEquals("Entry not found with the Id: " + idToTest, e.getMessage());

    }

    @Test
    void deleteWithNoExistingId() {
        int idToTest = 25;

        articleRepository.save(article1);

        EntryNotFoundException e = Assertions.assertThrows(EntryNotFoundException.class, () -> articleRepository.delete(idToTest));

        Assertions.assertEquals("Entry not found with the Id: " + idToTest, e.getMessage());
    }

    @Test
    void fetchAll() {
        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);

        Set<Article> fetchedArticles = articleRepository.fetchAll();

        Set<Article> expectedArticles = new HashSet<>(Arrays.asList(article1, article2, article3));

        Assertions.assertEquals(expectedArticles, fetchedArticles);
    }

    @Test
    void fetchAllWithNoStoredArticles() {
        Set<Article> fetchedArticles = articleRepository.fetchAll();

        Assertions.assertEquals(Collections.emptySet(), fetchedArticles);
    }
}