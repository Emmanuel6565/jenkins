package com.jcx.JXC;

import java.util.UUID;

import com.jcx.JXC.Repositorie.ArticleRepository;
import com.jcx.JXC.Service.ArticleService;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepo;

    @Testable
    public void saveArticle(){
        articleRepo.createArticle("title test", "tag test", "content test");
        articleRepo.findAll();
    }


}