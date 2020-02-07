package com.jcx.JXC.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.jcx.JXC.Entity.Article;
import com.jcx.JXC.Repositorie.*;

@Service("ArticleService")
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepo;

    public Article getArticleById(String id){
        return articleRepo.getArticleById(id);
    }
    public void getArticleByTitle(String title) {

    }

    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }
}