package com.example.interactivearc.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class news_topics {
    @Id
    private int id;

    private String topic;

    private String headline;

    private String article;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
