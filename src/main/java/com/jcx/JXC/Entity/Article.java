package com.jcx.JXC.Entity;

public class Article {
    private String articleId;
    private String title;
    private String tag;
    private String content;

    public Article(String articleId, String title, String tag, String content) {
        this.articleId = articleId;
        this.title = title;
        this.tag = tag;
        this.content = content;
    }

    /**
     * Getters and Setters
     */

    public String getArticleId(){
        return this.articleId;
    }
    public String getTitle() {
        return this.title;
    }
    public String getTag() {
        return this.tag;
    }
    public String getContent() {
        return this.content;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public void setContent(String content) {
        this.content = content;
    }
}