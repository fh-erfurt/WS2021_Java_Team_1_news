package de.fherfurt.news.articles.boundary;

import de.fherfurt.news.articles.boundary.mapper.ArticleToArticlePreviewDtoMapper;
import de.fherfurt.news.articles.boundary.mapper.ArticleToDtoMapper;
import de.fherfurt.news.articles.boundary.mapper.DtoToArticleMapper;
import de.fherfurt.news.articles.boundary.mapper.SortSettingsConverter;
import de.fherfurt.news.articles.business.NewsController;
import de.fherfurt.news.articles.business.errors.ArticleNotValidException;
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

/**
 * This class implements the {@link NewsResourceClient} interface
 * it uses the {@link NewsController} and Mappers
 *
 * @author Dennis Rinck <dennis.rinck@fh-erfurt.de>
 */
public class NewsResource implements NewsResourceClient {

    private final NewsController controller = new NewsController();

    /**
     * save an {@link ArticleDto} to the Repository
     * the articleDto object hast to be converted an {@link Article} to be stored
     *
     * @param articleDto article to be stored
     * @throws ArticleNotValidException this exception is thrown if the article is not valid
     */
    @Override
    public void save(ArticleDto articleDto) throws ArticleNotValidException {
        DtoToArticleMapper mapper = new DtoToArticleMapper();
        if (articleDto != null) {
            Article article = mapper.map(articleDto);
            controller.save(article);
        }
    }

    /**
     * delete an {@link Article} using it's id
     *
     * @param id id to the article which should be deleted
     * @throws EntryNotFoundException this exception is thrown if the article is not found
     */
    @Override
    public void delete(int id) throws EntryNotFoundException {
        controller.delete(id);
    }

    /**
     * get all article previews with a specific request
     *
     * @param request     contains relevant information for filtering, searching and sorting
     * @param requestType declare what the kind of request
     * @return article previews
     */
    @Override
    public List<ArticlePreviewDto> getArticlePreviews(PreviewRequestClient request, RequestTypeClient requestType) {

        de.fherfurt.news.articles.entity.PreviewRequest newRequest = getServicePreviewRequest(request);
        de.fherfurt.news.articles.entity.RequestType newRequestType = getServiceRequestType(requestType);

        List<Article> articles = controller.getArticlePreviews(newRequest, newRequestType);

        return convertArticleListToArticlePreviewDtoList(articles);
    }

    /**
     * helper function to convert {@link List<Article>} to {@link List<ArticlePreviewDto>}
     *
     * @param articles list of articles
     * @return list of article preview dto's
     */
    private List<ArticlePreviewDto> convertArticleListToArticlePreviewDtoList(List<Article> articles) {

        ArticleToArticlePreviewDtoMapper dtoMapper = new ArticleToArticlePreviewDtoMapper();
        List<ArticlePreviewDto> articlePreviews = new LinkedList<>();

        for (Article article : articles) {
            articlePreviews.add(dtoMapper.map(article));
        }
        return articlePreviews;
    }

    /**
     * helper function to get right class of PreviewRequest
     *
     * @param request client side: PreviewRequest
     * @return service side: PreviewRequest
     */
    private de.fherfurt.news.articles.entity.PreviewRequest getServicePreviewRequest(PreviewRequestClient request) {
        TypeMap<PreviewRequestClient, de.fherfurt.news.articles.entity.PreviewRequest> requestMapper = new ModelMapper().createTypeMap(PreviewRequestClient.class, PreviewRequest.class);
        requestMapper.addMappings(mapper -> mapper.using(new SortSettingsConverter()).map(PreviewRequestClient::getSortSettings, PreviewRequest::setSortSettings));
        return requestMapper.map(request);
    }

    /**
     * helper function to get right class of RequestType
     *
     * @param requestType client side: RequestType
     * @return service side: RequestType
     */
    private de.fherfurt.news.articles.entity.RequestType getServiceRequestType(RequestTypeClient requestType) {
        TypeMap<RequestTypeClient, de.fherfurt.news.articles.entity.RequestType> requestTypeMapper = new ModelMapper().createTypeMap(RequestTypeClient.class, de.fherfurt.news.articles.entity.RequestType.class);
        return requestTypeMapper.map(requestType);
    }

    /**
     * get an {@link ArticleDto} with the id
     *
     * @param id id of the article
     * @return articleDto
     * @throws EntryNotFoundException this exception is thrown if the article is not found
     */
    @Override
    public ArticleDto getArticle(int id) throws EntryNotFoundException {
        ArticleToDtoMapper mapper = new ArticleToDtoMapper();
        Article article = controller.getArticle(id);
        return mapper.map(article);
    }
}
