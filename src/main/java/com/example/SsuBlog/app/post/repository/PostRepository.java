package com.example.SsuBlog.app.post.repository;

import com.example.SsuBlog.app.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findBySubject(String subject);

    Post findBySubjectAndContent(String subject, String content);

    List<Post> findBySubjectLike(String s);
}
