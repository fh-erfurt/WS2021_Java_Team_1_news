package de.fherfurt.news.articles.entity;

import de.fherfurt.news.core.persistance.Database;
import de.fherfurt.news.core.persistance.DevDatabase;
import de.fherfurt.news.core.persistance.PreviewRequest;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;


/**
 * This class contains the Development Implementation of our ArticleRepository
 * It is used for communicating with the Development Implementation of our Database Interface{@link DevDatabase}
 * It also is a Singleton, because we only want to have one database to work with
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 * */

public class DevArticleRepository implements ArticleRepository{

    private static DevArticleRepository instance;
    private final Database<Article> database = new DevDatabase();

    public static DevArticleRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DevArticleRepository();
        }
        return instance;
    }

    @Override
    public Optional<Article> findBy(int id){
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
    public Optional<Set<ArticlePreview>> getArticlePreviews(PreviewRequest request){
        Set<ArticlePreview> articlePreviews = new HashSet<>();
        return Optional.ofNullable(articlePreviews);
    }
}
