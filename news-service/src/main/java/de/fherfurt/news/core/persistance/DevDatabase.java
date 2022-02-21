package de.fherfurt.news.core.persistance;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;

import java.util.Map;
import java.util.Optional;

public class DevDatabase implements Database<Article>{

    Map<Integer, Article> map;
    @Override
    public void save(Article article){
        map.put(article.getId(),article);
    }
    @Override
    public void delete(int id) throws EntryNotFoundException {
        if(checkIfExists(id)){
            map.remove(id);
        }
        else throw new EntryNotFoundException(Database.class.getTypeName() +": id is invalid");
    }

    @Override
    public Optional<Article> findBy(int id) {
        return Optional.ofNullable(map.get(id));
    }

    private boolean checkIfExists(int id){
        return map.containsKey(id);
    }
}