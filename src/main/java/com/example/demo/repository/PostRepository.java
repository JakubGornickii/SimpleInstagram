package com.example.demo.repository;

import com.example.demo.model.Hashtag;
import com.example.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    public List<Post> findByHashtagsContains(Hashtag hashtag);

}
