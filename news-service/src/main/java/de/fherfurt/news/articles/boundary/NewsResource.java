package de.fherfurt.news.articles.boundary;

import de.fherfurt.news.articles.boundary.mapper.ArticleToArticlePreviewDtoMapper;
import de.fherfurt.news.articles.boundary.mapper.ArticleToDtoMapper;
import de.fherfurt.news.articles.boundary.mapper.DtoToArticleMapper;
import de.fherfurt.news.articles.boundary.mapper.SortSettingsConverter;
import de.fherfurt.news.articles.business.NewsController;
import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.articles.entity.PreviewRequest;
import de.fherfurt.news.client.NewsResourceClient;
import de.fherfurt.news.client.dto.ArticleDto;
import de.fherfurt.news.client.dto.ArticlePreviewDto;
import de.fherfurt.news.client.options.PreviewRequestClient;
import de.fherfurt.news.client.options.RequestTypeClient;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.LinkedList;
import java.util.List;

public class NewsResource implements NewsResourceClient {

    private final NewsController controller = new NewsController();

    @Override
    public void save(ArticleDto articleDto) {
        DtoToArticleMapper mapper = new DtoToArticleMapper();
        if (articleDto != null) {
            Article article = mapper.map(articleDto);
            controller.save(article);
        }
    }

    @Override
    public void delete(int id) throws EntryNotFoundException {
        controller.delete(id);
    }

    @Override
    public List<ArticlePreviewDto> getArticlePreviews(PreviewRequestClient request, RequestTypeClient requestType) {

        de.fherfurt.news.articles.entity.PreviewRequest newRequest = getServicePreviewRequest(request);
        de.fherfurt.news.articles.entity.RequestType newRequestType = getServiceRequestType(requestType);

        List<Article> articles = controller.getArticlePreviews(newRequest, newRequestType);

        return convertArticleListToArticlePreviewDtoList(articles);

    }

    private List<ArticlePreviewDto> convertArticleListToArticlePreviewDtoList(List<Article> articles) {

        ArticleToArticlePreviewDtoMapper dtoMapper = new ArticleToArticlePreviewDtoMapper();
        List<ArticlePreviewDto> articlePreviews = new LinkedList<>();

        for (Article article : articles) {
            articlePreviews.add(dtoMapper.map(article));
        }
        return articlePreviews;
    }

    private de.fherfurt.news.articles.entity.PreviewRequest getServicePreviewRequest(PreviewRequestClient request) {
        TypeMap<PreviewRequestClient, de.fherfurt.news.articles.entity.PreviewRequest> requestMapper = new ModelMapper().createTypeMap(PreviewRequestClient.class, PreviewRequest.class);
        requestMapper.addMappings(mapper -> mapper.using(new SortSettingsConverter()).map(PreviewRequestClient::getSortSettings,PreviewRequest::setSortSettings));
        return requestMapper.map(request);
    }

    private de.fherfurt.news.articles.entity.RequestType getServiceRequestType(RequestTypeClient requestType) {
        TypeMap<RequestTypeClient, de.fherfurt.news.articles.entity.RequestType> requestTypeMapper = new ModelMapper().createTypeMap(RequestTypeClient.class, de.fherfurt.news.articles.entity.RequestType.class);
        return requestTypeMapper.map(requestType);
    }

    @Override
    public ArticleDto getArticle(int id) throws EntryNotFoundException {
        ArticleToDtoMapper mapper = new ArticleToDtoMapper();
        Article article = controller.getArticle(id);
        return mapper.map(article);
    }
}
