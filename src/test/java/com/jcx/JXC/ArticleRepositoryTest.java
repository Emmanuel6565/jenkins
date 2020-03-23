package com.jcx.JXC;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.jcx.JXC.Entity.Article;
import com.jcx.JXC.Repositorie.ArticleRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleRepositoryTest {
    
    @MockBean private ArticleRepository articleRepository;

    @Test
    public void getAllArticle() throws Exception {
        List<Article> articles;
        Article article, article2, article3;
        articles = new ArrayList<>();
        article = new Article("110e8400-e29b-11d4-a716-446655440000", "title", "tag", "content");
        article2 = new Article("110e8400-e29b-11d4-a716-446655440001", "title", "tag", "content");
        articles.add(article);
        articles.add(article2);
        when(articleRepository.findAll()).thenReturn(articles);
        assertEquals(articleRepository.findAll().size(),2);

        //unit test 
        //unit test
        article3 = new Article("110e8400-e29b-11d4-a716-446655440002", "title", "tag", "content");
        articles.add(article3);
        when(articleRepository.findAll()).thenReturn(articles);
        //verify(articleRepository).findAll();
        assertEquals(articleRepository.findAll().size(), 3);
    }
    
    @Test
    public void getAllArticleById() {
        Article art, art2;
        art = new Article("110e8400-e29b-11d4-a716-446655440000", "title", "tag", "content");
        art2 = new Article("110e8400-e29b-11d4-a716-446655440001", "title", "tag", "content");
        when(articleRepository.getArticleById("110e8400-e29b-11d4-a716-446655440000")).thenReturn(art);
        assertEquals(art, articleRepository.getArticleById("110e8400-e29b-11d4-a716-446655440000"));
        verify(articleRepository).getArticleById("110e8400-e29b-11d4-a716-446655440000");

        when(articleRepository.getArticleById("110e8400-e29b-11d4-a716-446655440001")).thenReturn(art2);
        assertEquals(art2, articleRepository.getArticleById("110e8400-e29b-11d4-a716-446655440001"));
        verify(articleRepository).getArticleById("110e8400-e29b-11d4-a716-446655440001");
    }
}