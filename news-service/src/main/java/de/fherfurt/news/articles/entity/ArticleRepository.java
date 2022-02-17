package de.fherfurt.news.articles.entity;

import de.fherfurt.news.core.persistance.PreviewRequest;
import de.fherfurt.news.core.persistance.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * specified API definition of the Repository which is used to communicate to the underlying database
 * it only used for Articles
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
public interface ArticleRepository extends Repository<Article> {


    /**
     * fetches all the ArticlePreviews which meet the requirements of {@link PreviewRequest}
     *
     * @param request Request handles the information's for fetching Article Previews (SortSettings,keyword,facultyId)
     * @return returns a set of ArticlePreviews if there are some, returns empty{@link Optional} if no ArticlePreview is available
     */
    Optional<Set<ArticlePreview>> getArticlePreviews(PreviewRequest request);
}
