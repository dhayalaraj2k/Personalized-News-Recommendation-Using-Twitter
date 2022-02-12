package com.example.interactivearc.Repositories;

import com.example.interactivearc.Entities.tweet_topics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface tweetTopicRepository extends CrudRepository<tweet_topics, Integer> {
    List<tweet_topics> findTop2ByTopic(String topic);
}
