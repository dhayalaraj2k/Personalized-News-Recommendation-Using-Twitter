package com.example.interactivearc.Repositories;

import com.example.interactivearc.Entities.Interest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends CrudRepository<Interest, Long> {
}
