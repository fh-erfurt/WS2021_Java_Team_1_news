package de.fherfurt.news;

import java.util.List;

public class News {
    private List<Article> articleList;

    public void addArticle(Article article){this.articleList.add(article);}
    public void getArticle(int articleIndex){this.articleList.get(articleIndex);}
    public void clearArticleList(){this.articleList.clear();}
    public void setArticleList(List<Article> articleList){this.articleList = articleList;}
    public List<Article> getArticleList(){return this.articleList;}
}
