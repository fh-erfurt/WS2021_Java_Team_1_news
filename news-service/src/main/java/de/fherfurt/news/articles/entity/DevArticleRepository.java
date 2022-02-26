package de.fherfurt.news.articles.entity;

import de.fherfurt.news.core.persistance.Database;
import de.fherfurt.news.core.persistance.DevDatabase;
import de.fherfurt.news.core.persistance.PreviewRequest;
import de.fherfurt.news.core.persistance.SortSettings;
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
    private final Database<ArticleDetails> database = new DevDatabase();

    public static DevArticleRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DevArticleRepository();
        }
        return instance;
    }

    @Override
    public Optional<ArticleDetails> findBy(int id){
        return database.findBy(id);
    }

    @Override
    public void save(ArticleDetails article){
        database.save(article);
    }

    @Override
    public void delete(int id) throws EntryNotFoundException {
        database.delete(id);
    }

    /* TODO do we need that???
    @Override
    public Optional<Set<BaseArticle>> getArticlePreviews(){
        return null;
    }
    */

    @Override
    public Set<ArticleDetails> fetchAll(){
        return new HashSet<>(database.getMap().values());
    }

    @Override
    public Optional<Set<BaseArticle>> getArticlePreviews(PreviewRequest request) {
        return Optional.empty();
    }

    @Override
    public Set<ArticleDetails> sort(SortSettings sortSettings){
        return database.sort(sortSettings);
    }

    @Override
    public Set<ArticleDetails> search(String searchKeyword) {
        return database.search(searchKeyword);
    }

    @Override
    public Set<ArticleDetails> filter(String facultyName) {
        return database.filter(facultyName);
    }
}
