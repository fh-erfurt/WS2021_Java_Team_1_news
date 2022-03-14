package de.fherfurt.news.core.persistance.dev;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import lombok.Getter;

import java.util.*;

/**
 *
 */
public class Database {

    //using SortedMap because its the dev implementation
    @Getter SortedMap<Integer, Article> map = new TreeMap<>();

    /**
     *
     * @param article Article which should be saved
     */
    public void save(Article article){
        if (article == null) return;
        if (!map.containsKey(article.getId()) || article.getId() == 0){
            article.setId(createNewId());
        }
        map.put(article.getId(),article);
    }

    private int createNewId() {
        if (map.size() == 0){
            return 1;
        }
        return map.lastKey()+1;
    }

    /**
     *
     * @param id id of Entry which should be deleted
     * @throws EntryNotFoundException if entry is not found
     */
    public void delete(int id) throws EntryNotFoundException {
        if(checkIfExists(id)){
            map.remove(id);
        }
        else throw new EntryNotFoundException(Database.class.getTypeName() +": id is invalid");
    }

    private boolean checkIfExists(int id){
        return map.containsKey(id);
    }

    public Article findBy(int id) throws EntryNotFoundException{
        try{
            return map.get(id);
        }
        catch (Exception e){
            throw new EntryNotFoundException("Entry not Found: " + id);
        }
    }

    public Set<Article> fetchAll() {
        return new HashSet<>(map.values());
    }


}
