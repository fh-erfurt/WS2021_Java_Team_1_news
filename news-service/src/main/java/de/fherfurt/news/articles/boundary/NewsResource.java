package de.fherfurt.news.articles.boundary;

import de.fherfurt.news.articles.business.NewsController;
import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.client.NewsResourceClient;
import de.fherfurt.news.client.dto.*;
import de.fherfurt.news.client.options.PreviewRequest;
import de.fherfurt.news.client.options.RequestType;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import org.modelmapper.ModelMapper;

import java.util.List;

public class NewsResource implements NewsResourceClient {

    private final NewsController controller = new NewsController();


    Article mapToArticle(ArticleDto articleDto) {
        /*int authorId = articleDto.getAuthor().getId();
        Set<Integer> responsiblePersonIds = new HashSet<>();

        for (PersonDto person: articleDto.getResponsiblePersons()) {
            responsiblePersonIds.add(person.getId());
        }

        int appointmentId = articleDto.getAppointment().getId();

        Language language;

        switch (articleDto.getLanguage()){
            case DE -> language = Language.DE;
            case EN -> language = Language.EN;
        }

        Article article = Article.builder()
                .withId(articleDto.getId())
                .withTitle(articleDto.getTitle())
                .withContent(articleDto.getContent())
                .withResponsiblePersonIds(responsiblePersonIds)
                .withAuthorId(authorId)
                .withAppointmentId(appointmentId)
                .withFacultyName(articleDto.getFacultyName())
                .withKeywords(articleDto.getKeywords())
                .withDate(articleDto.getDate())
                .withLanguage((Language) articleDto.getLanguage())
                .withPriority(articleDto.getPriority())

        return*/
        return null;
    }

    @Override
    public void save(ArticleDto articleDto) {
        ModelMapper modelMapper = new ModelMapper();
        Article article = modelMapper.map(articleDto, Article.class);



    }

    @Override
    public void delete(int id) throws EntryNotFoundException {
        try {
            controller.delete(id);
        } catch (EntryNotFoundException e) {
            throw new EntryNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<ArticlePreviewDto> getArticlePreviews(PreviewRequest request, RequestType requestType) {
        return null;
    }

    @Override
    public ArticleDto getArticle(int id) {
        return null;
    }
}
