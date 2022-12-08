package com.example.SsuBlog.app.post.repository;

import com.example.SsuBlog.app.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findBySubject(String subject);
}
