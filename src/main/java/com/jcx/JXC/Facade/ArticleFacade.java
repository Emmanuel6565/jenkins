package com.jcx.JXC.Facade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcx.JXC.Service.*;

import java.util.List;
import java.util.stream.Collectors;

import com.jcx.JXC.Dto.*;
import com.jcx.JXC.Entity.Article;

@Service
public class ArticleFacade {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ModelMapper ModelMapper;

    public ArticleDto getArticleById(String id){
        return this.convertToArticleDto(articleService.getArticleById(id));
    }

    public List<ArticleDto> getAllArticles() {
        return articleService.getAllArticles()
            .stream()
            .map(this::convertToArticleDto)
            .collect(Collectors.toList());
    }

    public ArticleDto convertToArticleDto(Article article){
        return ModelMapper.map(article, ArticleDto.class);
    }
}