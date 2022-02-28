package de.fherfurt.news.core.persistance;

import de.fherfurt.news.articles.entity.ArticleDetails;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;
import lombok.Getter;

import java.util.*;


public class DevDatabase implements Database<ArticleDetails>{

    @Getter Map<Integer, ArticleDetails> map = new HashMap<>();
    @Override
    public void save(ArticleDetails article){
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
    public Optional<ArticleDetails> findBy(int id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public Set<ArticleDetails> sort(SortSettings sortSettings){
        List<ArticleDetails> sortedArticles = new ArrayList<>(map.values());
        sortedArticles.sort(new ArticleSortComparator(sortSettings));
        return new HashSet<>(sortedArticles);
    }

    @Override
    public Set<ArticleDetails> search(String searchKeyword){
        //preparing articles for SearchModule
        Set<ArticleDetails> articles = new HashSet<>(map.values());

        ArticleSearchModule searchingModule = new ArticleSearchModule(searchKeyword, articles);
        //returning the articles matching with the searchKeyword
        return searchingModule.search();
    }
    @Override
    public Set<ArticleDetails> filter(String facultyName){
        //preparing articles for FilterModule
        Set<ArticleDetails> articles = new HashSet<>(map.values());

        ArticleFilterModule filterModule = new ArticleFilterModule(facultyName, articles);

        //returning the articles with facultyName
        return filterModule.filter();
    }
    private boolean checkIfExists(int id){
        return map.containsKey(id);
    }
}
