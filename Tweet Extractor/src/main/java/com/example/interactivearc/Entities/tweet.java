package com.example.interactivearc.Entities;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="tweet_seq", initialValue=1, allocationSize=1)
public class tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tweet_seq")
    private long id;

    private String author_id;

    private String tweet_id;

    private String link;

    @Column(length = 3000)
    private String tweetBody;

    private String retweetCount;

    private String replyCount;

    private String likeCount;

    private String quoteCount;

    private String tweetedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getTweet_id() {
        return tweet_id;
    }

    public void setTweet_id(String tweet_id) {
        this.tweet_id = tweet_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTweetBody() {
        return tweetBody;
    }

    public void setTweetBody(String tweetBody) {
        this.tweetBody = tweetBody;
    }

    public String getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(String retweetCount) {
        this.retweetCount = retweetCount;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getQuoteCount() {
        return quoteCount;
    }

    public void setQuoteCount(String quoteCount) {
        this.quoteCount = quoteCount;
    }

    public String getTweetedAt() {
        return tweetedAt;
    }

    public void setTweetedAt(String tweetedAt) {
        this.tweetedAt = tweetedAt;
    }

    public void printAll(){
        System.out.println(this.id+"\n"+this.tweet_id+" "+this.author_id+"\n"+this.link+"\n"+this.tweetBody+"\n"+
                "Public Metrics: \nRetweet Count: "+retweetCount+", Reply Count: "+replyCount+", Like Count: "+likeCount+", Quote Count: "+quoteCount+"\n");
    }
}