package com.example.interactivearc.Repositories;

import com.example.interactivearc.Entities.tweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends CrudRepository<tweet, Long>{
}
