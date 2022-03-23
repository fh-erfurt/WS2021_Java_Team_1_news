package de.fherfurt.news.articles.business;

import de.fherfurt.news.articles.business.modules.entity.SortDirection;
import de.fherfurt.news.articles.business.modules.entity.SortPriority;
import de.fherfurt.news.articles.business.modules.entity.SortSettings;
import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.articles.business.modules.entity.PreviewRequest;
import de.fherfurt.news.articles.entity.Priority;
import de.fherfurt.news.articles.business.modules.entity.RequestType;
import de.fherfurt.news.core.persistance.Repository;
import de.fherfurt.news.core.persistance.dev.ArticleRepository;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this class tests the functionality of the {@link ArticleRequestHandler}
 *
 * @author Maximilian Roehr <maximilian.roehr@fh-erfurt.de>
 */
class ArticleRequestHandlerTest {

    //logger to log errors to the console
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    //setting up some values which are used in the tests
    private PreviewRequest request;
    private RequestType requestType;

    private final String ai = "Angewandte Informatik";
    private final String ge = "Gebäude und Energie Technik";

    Set<String> articleKeywords1 = new HashSet<>(Arrays.asList("Prüfungen", "Corona"));
    Set<String> articleKeywords2 = new HashSet<>(Arrays.asList("4. Semester", "Corona"));
    Set<String> articleKeywords3 = new HashSet<>(Arrays.asList("Stundenplan", "4. Semester"));

    private final Article article1 = Article.builder()
            .withTitle("Neue Corona Verordnung")
            .withKeywords(articleKeywords1)
            .withFacultyName(ai)
            .withDate(LocalDateTime.now())
            .withPriority(Priority.CRITICAL)
            .build();
    private final Article article2 = Article.builder()
            .withTitle("Stundenplan")
            .withKeywords(articleKeywords2)
            .withFacultyName(ai)
            .withDate(LocalDateTime.of(2021, Month.JUNE, 25, 13, 13))
            .withPriority(Priority.NORMAL)
            .build();
    private final Article article3 = Article.builder()
            .withTitle("Angestellte gesucht")
            .withKeywords(articleKeywords3)
            .withFacultyName(ge)
            .withDate(LocalDateTime.of(2022, Month.JANUARY, 23, 11, 11))
            .withPriority(Priority.HIGH)
            .build();

    private final Set<Article> articles = new HashSet<>(Arrays.asList(article1, article2, article3));

    private final Repository<Article> articleRepository = new ArticleRepository();

    //saving predefined articles into the repository
    @BeforeEach
    void setUp() {
        for (Article article : articles) {
            articleRepository.save(article);
        }
    }

    //deleting all articles out of the repository
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
    void handleRequestSort() {

        SortSettings sortSettings = new SortSettings(SortDirection.ASC, SortPriority.TITLE);
        request = new PreviewRequest("", "", sortSettings);
        requestType = RequestType.SORT;

        ArticleRequestHandler requestHandler = new ArticleRequestHandler(articleRepository);

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article3, article1, article2));

        List<Article> actualArticles = requestHandler.handleRequest(request, requestType);

        Assertions.assertEquals(expectedArticles, actualArticles);
    }

    @Test
    void handleRequestFilterSort() {

        SortSettings sortSettings = new SortSettings(SortDirection.ASC, SortPriority.TITLE);
        request = new PreviewRequest("", ai, sortSettings);

        requestType = RequestType.FILTER_SORT;

        ArticleRequestHandler requestHandler = new ArticleRequestHandler(articleRepository);

        List<Article> expectedArticles = new LinkedList<>(Arrays.asList(article1, article2));

        List<Article> actualArticles = requestHandler.handleRequest(request, requestType);

        Assertions.assertEquals(expectedArticles, actualArticles);

    }

    @Test
    void handleRequestFilterSearchSort() {

        SortSettings sortSettings = new SortSettings(SortDirection.ASC, SortPriority.TITLE);
        request = new PreviewRequest("stundenplan", ai, sortSettings);

        requestType = RequestType.FILTER_SEARCH_SORT;

        ArticleRequestHandler requestHandler = new ArticleRequestHandler(articleRepository);

        List<Article> expectedArticles = new LinkedList<>();
        expectedArticles.add(article2);

        List<Article> actualArticles = requestHandler.handleRequest(request, requestType);

        Assertions.assertEquals(expectedArticles, actualArticles);
    }
}