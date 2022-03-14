package de.fherfurt.news.core.persistance.dev;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.core.persistance.Repository;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;

import java.util.Objects;
import java.util.Set;


/**
 * This class contains the Development Implementation of our Repository Interface
 * It is used for communicating with the Development Implementation of our Database {@link Database}
 * It also is a Singleton, because we only want to have one database to work with
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 * */

public class ArticleRepository implements Repository<Article> {

    private static ArticleRepository instance;
    private final Database database = new Database();

    /**
     * Get the current instance of the Article Repository, if there is none a new one will be initialized
     *
     * @return the current existing instance or a new one
     */
    public static ArticleRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ArticleRepository();
        }
        return instance;
    }

    @Override
    public Article findBy(int id) throws EntryNotFoundException{
        return database.findBy(id);
    }

    @Override
    public void save(Article article){
        database.save(article);
    }

    @Override
    public void delete(int id) throws EntryNotFoundException {
        database.delete(id);
    }

    @Override
    public Set<Article> fetchAll() {
        return database.fetchAll();
    }
}