package de.fherfurt.news.articles.business.modules;

import de.fherfurt.news.articles.entity.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


class ArticleSearchModuleTest {

    private String searchTerm = "";

    Set<String> articleKeywords1 = new HashSet<>(Arrays.asList("Prüfungen", "Corona"));
    Set<String> articleKeywords2 = new HashSet<>(Arrays.asList("4. Semester", "Corona"));
    Set<String> articleKeywords3 = new HashSet<>(Arrays.asList("Stundenplan", "4. Semester"));


    private final Article article1 = Article.builder()
            .withKeywords(articleKeywords1)
            .withTitle("Neue Corona Verordnung").build();
    private final Article article2 = Article.builder()
            .withKeywords(articleKeywords2)
            .withTitle("Stundenplan").build();
    private final Article article3 = Article.builder()
            .withKeywords(articleKeywords3).build();

    private final Set<Article> articles = new HashSet<>(Arrays.asList(article1, article2, article3));

    @Test
    void search() {
        this.searchTerm = "Corona";

        Set<Article> expectedArticles = new HashSet<>(Arrays.asList(article1,article2));

        Set<Article> searchedArticles = new ArticleSearchModule(this.searchTerm, this.articles).search();

        Assertions.assertArrayEquals(expectedArticles.toArray(), searchedArticles.toArray());
    }

    @Test
    void searchForNotExistingKeyWordOrTitle() {
        this.searchTerm = "test";

        Set<Article> searchedArticles = new ArticleSearchModule(this.searchTerm, this.articles).search();

        Assertions.assertEquals(Collections.emptySet(),searchedArticles);
    }

    @Test
    void searchForExistingKeywordOrTitle() {
        this.searchTerm = "Stundenplan";

        Set<Article> expectedArticles = new HashSet<>(Arrays.asList(article2,article3));

        Set<Article> searchedArticles = new ArticleSearchModule(this.searchTerm, this.articles).search();

        Assertions.assertArrayEquals(expectedArticles.toArray(), searchedArticles.toArray());
    }


}