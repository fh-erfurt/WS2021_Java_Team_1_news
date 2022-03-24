package de.fherfurt.news.articles.boundary.mapper;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.client.dto.ArticlePreviewDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

/**
 * this class maps an {@link Article} to an {@link ArticlePreviewDto}
 *
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
public class ArticleToArticlePreviewDtoMapper {

    TypeMap<Article, ArticlePreviewDto> typeMap = new ModelMapper().createTypeMap(Article.class, ArticlePreviewDto.class);

    public ArticlePreviewDto map(Article article) {
        return typeMap.map(article);
    }
}
