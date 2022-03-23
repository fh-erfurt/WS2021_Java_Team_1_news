package de.fherfurt.news.client;


import de.fherfurt.news.client.dto.ArticleDto;
import de.fherfurt.news.client.dto.ArticlePreviewDto;
import de.fherfurt.news.client.options.PreviewRequest;
import de.fherfurt.news.client.options.RequestType;

import java.util.List;

public interface NewsResourceClient {

    void save(ArticleDto article);

    void delete(int id) throws Exception;

    List<ArticlePreviewDto> getArticlePreviews(PreviewRequest request, RequestType requestType);

    ArticleDto getArticle(int id);
}
