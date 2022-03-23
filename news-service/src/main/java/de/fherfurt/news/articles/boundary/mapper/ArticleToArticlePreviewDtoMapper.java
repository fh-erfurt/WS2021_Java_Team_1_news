package de.fherfurt.news.articles.boundary.mapper;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.client.dto.ArticlePreviewDto;
import de.fherfurt.persons.client.DevPersonClient;
import de.fherfurt.persons.client.PersonClient;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;


public class ArticleToArticlePreviewDtoMapper {

    TypeMap<Article, ArticlePreviewDto> typeMap = new ModelMapper().createTypeMap(Article.class, ArticlePreviewDto.class);
    PersonClient personClient = new DevPersonClient();

    public ArticlePreviewDto map(Article article){
        return typeMap.map(article);
    }
}
