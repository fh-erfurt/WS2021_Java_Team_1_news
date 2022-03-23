package de.fherfurt.news.articles.business;

import de.fherfurt.appointments.client.AppointmentsClient;
import de.fherfurt.appointments.client.DevAppointmentsClient;
import de.fherfurt.faculties.client.DevFacultyClient;
import de.fherfurt.faculties.client.FacultyClient;
import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.articles.entity.PreviewRequest;
import de.fherfurt.news.articles.entity.RequestType;
import de.fherfurt.news.core.persistance.Repository;
import de.fherfurt.news.core.persistance.dev.ArticleRepository;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import de.fherfurt.persons.client.DevPersonClient;
import de.fherfurt.persons.client.PersonClient;

import java.util.List;

public class NewsController {

    Repository<Article> repository = new ArticleRepository();

    AppointmentsClient appointmentsClient = new DevAppointmentsClient();

    FacultyClient facultyClient = new DevFacultyClient();

    PersonClient personClient = new DevPersonClient();

    ArticleValidator articleValidator = new ArticleValidator(appointmentsClient, facultyClient, personClient);

    public void save(Article article){
        if (articleValidator.validateArticle(article)){
            repository.save(article);
        }
    }

    public void delete(int id) throws EntryNotFoundException {
        try {
            repository.delete(id);
        } catch (EntryNotFoundException e) {
            throw new EntryNotFoundException("Article not found: " + id);
        }
    }

    public List<Article> getArticlePreviews(PreviewRequest request, RequestType requestType){
        return new ArticleRequestHandler(repository).handleRequest(request,requestType);
    }

    public Article getArticle(int id) throws EntryNotFoundException {
        try{
            return repository.findBy(id);
        }catch(EntryNotFoundException e){
            throw new EntryNotFoundException("Article not found: "+id);
        }
    }
}

