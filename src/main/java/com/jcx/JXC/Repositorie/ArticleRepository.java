package com.jcx.JXC.Repositorie;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import com.jcx.JXC.Entity.*;

import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {

    private List<Article> articles;

    public ArticleRepository() {
        this.articles = new ArrayList<>();
        this.articles.add(new Article("110e8400-e29b-11d4-a716-446655440000", "title","tag", "content"));
    }

    public Article getArticleById(String id) {
        for(Article article: articles){
            if (article.getArticleId().equals(id)) {
                return article;
            }
        }
        return null;
    }

    public List<Article> findAllPage() {
        return this.findAll(0, 5);
    }

    /**
     * offset: the number of elements to skip in the beginning of the list
     * limit: the number of elements to take
     */
    public List<Article> findAll() {
        return this.articles;
    }

    public void createArticle(String title, String tag, String content) {
        this.articles.add(new Article(UUID.randomUUID().toString(), title, tag, content));
    }

}
