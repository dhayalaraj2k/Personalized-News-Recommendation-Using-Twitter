package com.example.interactivearc.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class tweet_topics {
    @Id
    private int id;

    private String topic;

    private String tweet;

    private String tweet_id;

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

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getTweet_id() {
        return tweet_id;
    }

    public void setTweet_id(String tweet_id) {
        this.tweet_id = tweet_id;
    }
}
