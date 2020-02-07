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

    @GetMapping(value = "/article/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<ArticleDto> getArticleById(@PathVariable String id) {
        ArticleDto article = articleFacade.getArticleById(id);
        return new ResponseEntity<ArticleDto>(article, HttpStatus.OK);
    }

    @GetMapping(value="/article/")
    public @ResponseBody ResponseEntity<List<ArticleDto>> getAllArticle() {
        List<ArticleDto> articles = articleFacade.getAllArticles();
        return new ResponseEntity<List<ArticleDto>>(articles, HttpStatus.OK);
    }
    
    
}