package de.fherfurt.news.articles.business.modules;

import de.fherfurt.news.articles.entity.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * this class tests the functionality of the {@link ArticleFilterModule}
 *
 * @author Maximilian Roehr <maximilian.roehr@fh-erfurt.de>
 */
class ArticleFilterModuleTest {

    //setting up some values which are used in the tests
    private final String ai = "Angewandte Informatik";
    private final String ge = "Gebäude und Energie Technik";
    private String facultyFilterName;

    private final Article articleAI = Article.builder().withFacultyName(ai).build();
    private final Article articleAI2 = Article.builder().withFacultyName(ai).build();
    private final Article articleAI3 = Article.builder().withFacultyName(ai).build();
    private final Article articleGE = Article.builder().withFacultyName(ge).build();

    private final Set<Article> articles = new HashSet<>(Arrays.asList(articleAI, articleAI2, articleAI3, articleGE));


    @Test
    void filter() {

        this.facultyFilterName = ai;

        Set<Article> expectedArticles = new HashSet<>(Arrays.asList(articleAI, articleAI2, articleAI3));

        Set<Article> filteredArticles = new ArticleFilterModule(this.facultyFilterName, this.articles).filter();

        Assertions.assertArrayEquals(expectedArticles.toArray(), filteredArticles.toArray());

    }

    @Test
    void filterWithNotExistingFacultyName() {

        this.facultyFilterName = "Architektur";

        Set<Article> filteredArticles = new ArticleFilterModule(this.facultyFilterName, this.articles).filter();

        Assertions.assertEquals(Collections.emptySet(), filteredArticles);

    }
}