package com.example.interactivearc.Repositories;

import com.example.interactivearc.Entities.news_topics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface newsTopicRepository extends CrudRepository<news_topics, Integer> {
    List<news_topics> findTop2ByTopic(String topic);
}
