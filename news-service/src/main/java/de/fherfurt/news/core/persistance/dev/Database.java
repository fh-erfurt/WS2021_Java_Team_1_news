package de.fherfurt.news.core.persistance.dev;

import de.fherfurt.news.articles.entity.Article;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * this database class is a dev implementation to imitate the behaviour of a really simple database
 *
 * @author Maximilian Roehr <maximilian.roehr@fh-erfurt.de>
 */
public class Database {

    //using SortedMap because it's the dev implementation
    @Getter
    SortedMap<Integer, Article> map = new TreeMap<>();

    /**
     * @param article Article which should be saved
     */
    public void save(Article article) {
        if (article == null) return;
        if (map.containsKey(article.getId()) || article.getId() == 0) {
            article.setId(createNewId());
        }
        map.put(article.getId(), article);
    }

    protected int createNewId() {
        if (map.size() == 0) {
            return 1;
        }
        return map.lastKey() + 1;
    }

    /**
     * @param id id of Entry which should be deleted
     * @throws EntryNotFoundException if entry is not found
     */
    public void delete(int id) throws EntryNotFoundException {
        if (checkIfExists(id)) {
            map.remove(id);
        } else throw new EntryNotFoundException("Entry not found with the Id: " + id);
    }

    private boolean checkIfExists(int id) {
        return map.containsKey(id);
    }

    public Article findBy(int id) throws EntryNotFoundException {
        if (map.get(id) == null) throw new EntryNotFoundException("Entry not found with the Id: " + id);
        else {
            return map.get(id);
        }
    }

    public Set<Article> fetchAll() {
        if (map.size() == 0) return Collections.emptySet();
        return new HashSet<>(map.values());
    }


}
