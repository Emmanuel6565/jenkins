package com.jcx.JXC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.jcx.JXC.Repositorie.ArticleRepository;

@SpringBootApplication
public class JxcApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(JxcApplication.class, args);

		ArticleRepository articleRepository = context.getBean(ArticleRepository.class);
		articleRepository.createArticle("title", "essai", "content");
		articleRepository.createArticle("one", "one", "content 1");
		articleRepository.createArticle("two", "two", "content 2");
		articleRepository.createArticle("three", "three", "content 3");
		articleRepository.createArticle("four", "four", "content 4");
		articleRepository.createArticle("five", "five", "content 5");
		articleRepository.createArticle("six", "six", "content 6");
        articleRepository.createArticle("Test CI/CD", "TEST", "TEST CI/CD");
	}

}
