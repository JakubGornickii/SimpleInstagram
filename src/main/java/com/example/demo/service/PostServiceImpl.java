package com.example.demo.service;

import com.example.demo.model.Hashtag;
import com.example.demo.model.Post;
import com.example.demo.repository.HashtagRepository;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("postService")

public class PostServiceImpl implements PostService {

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private PostRepository postRepository;


    @Override
    public void savePost(Post post, List<String> hashtags,String has) {
        List<Hashtag> postTags = new ArrayList<>();
        for (String hash : hashtags) {
            Hashtag hashtag = hashtagRepository.findByHashtag(hash);
            if (hashtag == null) {
                Hashtag hashtag1 = new Hashtag();
                hashtag1.setHashtag(hash);
                hashtagRepository.save(hashtag1);
                postTags.add(hashtag1);
            } else {
                postTags.add(hashtag);
            }
        }
        post.setHashtags(postTags);
        post.setHashString(has);
        postRepository.save(post);

    }

    @Override
    public List<Post> getAllPost() {
        return  postRepository.findAll();
    }
}
