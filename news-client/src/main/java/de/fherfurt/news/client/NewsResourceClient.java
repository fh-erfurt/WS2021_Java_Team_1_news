package de.fherfurt.news.client;


import de.fherfurt.news.client.dto.ArticleDto;
import de.fherfurt.news.client.dto.ArticlePreviewDto;
import de.fherfurt.news.client.options.PreviewRequestClient;
import de.fherfurt.news.client.options.RequestTypeClient;

import java.util.List;

public interface NewsResourceClient {

    void save(ArticleDto article);

    void delete(int id) throws Exception;

    List<ArticlePreviewDto> getArticlePreviews(PreviewRequestClient request, RequestTypeClient requestType);

    ArticleDto getArticle(int id) throws Exception;
}
