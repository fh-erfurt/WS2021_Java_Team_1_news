package de.fherfurt.news.articles.business;

import de.fherfurt.appointments.client.AppointmentsClient;
import de.fherfurt.appointments.client.DevAppointmentsClient;
import de.fherfurt.faculties.client.DevFacultyClient;
import de.fherfurt.faculties.client.FacultyClient;
import de.fherfurt.news.articles.business.errors.ArticleNotValidException;
import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.articles.business.modules.entity.PreviewRequest;
import de.fherfurt.news.articles.business.modules.entity.RequestType;
import de.fherfurt.news.core.persistance.Repository;
import de.fherfurt.news.core.persistance.dev.ArticleRepository;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import de.fherfurt.persons.client.DevPersonClient;
import de.fherfurt.persons.client.PersonClient;
import lombok.Getter;

import java.util.List;

public class NewsController {

    private NewsController() {
        repository = new ArticleRepository();
        appointmentsClient = new DevAppointmentsClient();
        facultyClient = new DevFacultyClient();
        personClient = new DevPersonClient();
        articleValidator = new ArticleValidator(appointmentsClient,facultyClient,personClient);
    }

    public static NewsController getInstance() {
        if (instance == null){
            instance = new NewsController();
        }
        return instance;
    }

    private static NewsController instance;

    Repository<Article> repository;

    @Getter
    AppointmentsClient appointmentsClient;
    @Getter
    FacultyClient facultyClient;
    @Getter
    PersonClient personClient;

    ArticleValidator articleValidator;

    public void save(Article article) throws ArticleNotValidException {
        if (articleValidator.validateArticle(article)) {
            try {
                if (repository.findBy(article.getId()) != null) {
                    article.setWasModified(true);
                }
            } catch (EntryNotFoundException ignored) {/*do nothing*/}
            repository.save(article);
        } else {
            throw new ArticleNotValidException("Article not valid");
        }
    }

    public void delete(int id) throws EntryNotFoundException {
        try {
            repository.delete(id);
        } catch (EntryNotFoundException e) {
            throw new EntryNotFoundException("Article not found: " + id);
        }
    }

    public List<Article> getArticlePreviews(PreviewRequest request, RequestType requestType) {
        return new ArticleRequestHandler(repository).handleRequest(request, requestType);
    }

    public Article getArticle(int id) throws EntryNotFoundException {
        try {
            return repository.findBy(id);
        } catch (EntryNotFoundException e) {
            throw new EntryNotFoundException("Article not found: " + id);
        }
    }
}

