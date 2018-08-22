package com.example.demo.repository;

import com.example.demo.model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag,Integer> {
    public Hashtag findByHashtag(String hashtag);
}
