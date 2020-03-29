package com.jcx.JXC.Controller;

import java.util.List;

import com.jcx.JXC.Dto.ArticleDto;
import com.jcx.JXC.Facade.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class ArticleController {
    @Autowired
    private ArticleFacade articleFacade;

    public ArticleController(ArticleFacade articleFacade) {
        this.articleFacade = articleFacade;
    }
    @GetMapping(value = "/")
        public String name() {
            return "Hello, World, c'est une nouvelle fonctionnalité";
        }

    @GetMapping(value = "/article/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<ArticleDto> getArticleById(@PathVariable String id) {
        ArticleDto article = articleFacade.getArticleById(id);
        if(article != null){
            return new ResponseEntity<ArticleDto>(article, HttpStatus.OK);
        }
        else{
            ArticleDto dto = null;
            return new ResponseEntity<ArticleDto>(dto , HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value="/article/")
    public @ResponseBody ResponseEntity<List<ArticleDto>> getAllArticle() {
        List<ArticleDto> articles = articleFacade.getAllArticles();
        if(articles != null) {
            return new ResponseEntity<List<ArticleDto>>(articles, HttpStatus.OK);
        }
        else{
            List<ArticleDto> dto = null;
            return new ResponseEntity<List<ArticleDto>>(dto , HttpStatus.NO_CONTENT);
        }

    }
    
    
}
