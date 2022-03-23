package de.fherfurt.news.articles.boundary.mapper;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.client.dto.ArticleDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class DtoToArticleMapper {

    TypeMap<ArticleDto,Article> typeMap = new ModelMapper().createTypeMap(ArticleDto.class,Article.class);

    public Article map(ArticleDto articleDto){
        typeMap.addMappings(mapper -> mapper.using(new PersonsToPersonIdsConverter()).map(ArticleDto::getResponsiblePersons,Article::setResponsiblePersonIds));
        return typeMap.map(articleDto);
    }

}
