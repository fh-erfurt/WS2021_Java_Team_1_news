package de.fherfurt.news.articles.entity;

import de.fherfurt.news.articles.entity.dev.BaseArticle;
import de.fherfurt.news.core.persistance.Database;
import de.fherfurt.news.core.persistance.dev.DevDatabase;
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
    public void delete(int id) throws EntryNotFoundException { database.delete(id); }

    @Override
    public Optional<Set<BaseArticle>> getBaseArticles(PreviewRequest request){
        Set<BaseArticle> articlePreviews = new HashSet<>();
        return Optional.ofNullable(articlePreviews);
    }

    /*TODO check if we need to implement everything */

    @Override
    public Set<ArticleDetails> fetchAll() {
        return null;
    }
    @Override
    public Set<ArticleDetails> sort(SortSettings sortSettings) {
        return null;
    }

    @Override
    public Set<ArticleDetails> search(String searchKeyword) {
        return null;
    }

    @Override
    public Set<ArticleDetails> filter(String facultyName) {
        return null;
    }
}