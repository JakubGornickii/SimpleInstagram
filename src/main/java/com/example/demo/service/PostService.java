package com.example.demo.service;

import com.example.demo.model.Post;

import java.util.List;

public interface PostService {
    public void savePost(Post post, List<String> hashtags);
}
