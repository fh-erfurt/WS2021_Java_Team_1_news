package de.fherfurt.news.articles.business.modules;

import de.fherfurt.news.articles.business.modules.entity.SortDirection;
import de.fherfurt.news.articles.business.modules.entity.SortPriority;
import de.fherfurt.news.articles.business.modules.entity.SortSettings;
import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.articles.entity.Priority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class ArticleSortModuleTest {

    private SortSettings sortSettings;

    private final Article article1 = Article.builder()
            .withTitle("Neue Corona Verordnung")
            .withDate(LocalDateTime.now())
            .withPriority(Priority.CRITICAL)
            .build();
    private final Article article2 = Article.builder()
            .withTitle("Stundenplan")
            .withDate(LocalDateTime.of(2021, Month.JUNE, 25, 13, 13))
            .withPriority(Priority.NORMAL)
            .build();
    private final Article article3 = Article.builder()
            .withTitle("Angestellte gesucht")
            .withDate(LocalDateTime.of(2022, Month.JANUARY, 23, 11, 11))
            .withPriority(Priority.HIGH)
            .build();

    private final Article article4 = Article.builder().build();

    List<Article> articles = new LinkedList<>(Arrays.asList(article1, article2, article3));


    @BeforeEach
    void setUp() {
        article1.setClicks(100);
        article2.setClicks(10);
        article3.setClicks(1);
    }

    /**
     * this is a helper function to check the order of articles are correct
     *
     * @param expected articles which are expected
     * @param actual   actual articles
     * @return returns true if the order and the contents of the actual articles equals the expected ones
     */
    private boolean checkOrderOfArticlesWithEquals(List<Article> expected, List<Article> actual) {
        if (actual.size() != expected.size()) return false;

        for (int i = 0; i < actual.size(); i++) {
            if (!actual.get(i).equals(expected.get(i))) return false;
        }
        return true;
    }

    @Test
    void sortWithNewlyCreatedArticlesAndMatchingAttributes() {
        this.sortSettings = new SortSettings(SortDirection.ASC, SortPriority.TITLE);

        //is the exact same as article2 but will have another pointer
        //this is how we check if the actual values are equal
        Article testArticle = Article.builder()
                .withTitle("Stundenplan")
                .withDate(LocalDateTime.of(2021, Month.JUNE, 25, 13, 13))
                .withPriority(Priority.NORMAL)
                .build();

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article3, article1, testArticle));

        List<Article> sortedArticles = new ArticleSortModule(this.sortSettings, articles).sort();

        Assertions.assertTrue(checkOrderOfArticlesWithEquals(expectedArticles,sortedArticles));

    }

    @Test
    void sortByTitleAscending() {
        this.sortSettings = new SortSettings(SortDirection.ASC, SortPriority.TITLE);

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article3, article1, article2));

        List<Article> sortedArticles = new ArticleSortModule(this.sortSettings, articles).sort();

        Assertions.assertEquals(expectedArticles, sortedArticles);
    }

    @Test
    void sortByTitleAscendingWithWrongExpectedOrder() {
        this.sortSettings = new SortSettings(SortDirection.ASC, SortPriority.TITLE);

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article1, article2, article3));

        List<Article> sortedArticles = new ArticleSortModule(this.sortSettings, this.articles).sort();

        Assertions.assertNotEquals(expectedArticles, sortedArticles);
    }

    @Test
    void sortByTitleAscendingWithTitleNotInitialized() {
        this.sortSettings = new SortSettings(SortDirection.ASC, SortPriority.TITLE);

        this.articles.add(this.article4);

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article4, article3, article1, article2));

        List<Article> sortedArticles = new ArticleSortModule(this.sortSettings, this.articles).sort();

        Assertions.assertNotEquals(expectedArticles, sortedArticles);
    }

    @Test
    void sortByTitleDescending() {
        this.sortSettings = new SortSettings(SortDirection.DESC, SortPriority.TITLE);

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article2, article1, article3));

        List<Article> sortedArticles = new ArticleSortModule(this.sortSettings, this.articles).sort();

        Assertions.assertEquals(expectedArticles, sortedArticles);
    }

    @Test
    void sortByDateAscending() {
        this.sortSettings = new SortSettings(SortDirection.ASC, SortPriority.DATE);

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article2, article3, article1));

        List<Article> sortedArticles = new ArticleSortModule(this.sortSettings, articles).sort();

        Assertions.assertEquals(expectedArticles, sortedArticles);
    }

    @Test
    void sortByDateDescending() {
        this.sortSettings = new SortSettings(SortDirection.DESC, SortPriority.DATE);

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article1, article3, article2));

        List<Article> sortedArticles = new ArticleSortModule(this.sortSettings, articles).sort();

        Assertions.assertEquals(expectedArticles, sortedArticles);
    }

    @Test
    void sortByClicksAscending() {
        this.sortSettings = new SortSettings(SortDirection.ASC, SortPriority.CLICKS);

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article3, article2, article1));

        List<Article> sortedArticles = new ArticleSortModule(this.sortSettings, this.articles).sort();

        Assertions.assertNotEquals(expectedArticles, sortedArticles);
    }

    @Test
    void sortByClicksDescending() {
        this.sortSettings = new SortSettings(SortDirection.DESC, SortPriority.CLICKS);

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article1, article2, article3));

        List<Article> sortedArticles = new ArticleSortModule(this.sortSettings, this.articles).sort();

        Assertions.assertNotEquals(expectedArticles, sortedArticles);
    }

    @Test
    void sortByPriorityAscending() {
        this.sortSettings = new SortSettings(SortDirection.ASC, SortPriority.PRIORITY);

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article2, article3, article1));

        List<Article> sortedArticles = new ArticleSortModule(this.sortSettings, this.articles).sort();

        Assertions.assertNotEquals(expectedArticles, sortedArticles);
    }

    @Test
    void sortByPriorityDescending() {
        this.sortSettings = new SortSettings(SortDirection.DESC, SortPriority.PRIORITY);

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article1, article3, article2));

        List<Article> sortedArticles = new ArticleSortModule(this.sortSettings, this.articles).sort();

        Assertions.assertNotEquals(expectedArticles, sortedArticles);
    }

    @Test
    void sortWithNoGivenArticles() {
        this.sortSettings = new SortSettings(SortDirection.ASC, SortPriority.TITLE);

        List<Article> noArticles = new LinkedList<>();

        List<Article> sortedArticles = new ArticleSortModule(this.sortSettings, noArticles).sort();

        Assertions.assertEquals(Collections.emptyList(), sortedArticles);
    }
}