package de.fherfurt.news.client;


import de.fherfurt.news.client.dto.ArticleDto;
import de.fherfurt.news.client.dto.ArticlePreviewDto;
import de.fherfurt.news.client.options.PreviewRequestClient;
import de.fherfurt.news.client.options.RequestTypeClient;

import java.util.List;

/**
 * this interface represents the functionalities other services or the client could use
 *
 * @author Dennis Rinck <dennis.rinck@fh-erfurt.de>
 */
public interface NewsResourceClient {

    /**
     * save an article to the NewsService module
     *
     * @param article article to be stored
     * @throws Exception this exception is thrown if the article is not valid
     */
    void save(ArticleDto article) throws Exception;

    /**
     * delete an article of the NewsService module
     *
     * @param id article id which should be deleted
     * @throws Exception this exception is thrown if the article is not found
     */
    void delete(int id) throws Exception;

    /**
     * get article previews with a specific request
     *
     * @param request     contains relevant information for filtering, searching and sorting
     * @param requestType declare what the kind of request
     * @return returns article previews
     */
    List<ArticlePreviewDto> getArticlePreviews(PreviewRequestClient request, RequestTypeClient requestType);

    /**
     * get the article with an id
     *
     * @param id id of the article
     * @return article
     * @throws Exception this exception is thrown if the article is not found
     */
    ArticleDto getArticle(int id) throws Exception;
}
