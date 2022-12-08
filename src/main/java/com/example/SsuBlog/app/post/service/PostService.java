package com.example.SsuBlog.app.post.service;

import com.example.SsuBlog.app.post.entity.Post;
import com.example.SsuBlog.app.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post findById(int id) {
        Post p1 = postRepository.findById(2).get();
        Post p2 = postRepository.findById(2).get();
        System.out.println(p2.getAnswerList());

        return p2;
    }
}
