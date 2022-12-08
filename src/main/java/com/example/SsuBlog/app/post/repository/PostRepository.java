package com.example.SsuBlog.app.post.repository;

import com.example.SsuBlog.app.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findBySubject(String subject);

    Post findBySubjectAndContent(String subject, String content);

    List<Post> findBySubjectLike(String s);

    @Transactional
    @Modifying
    @Query(value = "truncate post", nativeQuery = true)
    void truncate();

    @Transactional
    @Modifying
    @Query(value = "SET FOREIGN_KEY_CHECKS = 0", nativeQuery = true)
    void disableForeignKeyChecks();

    @Transactional
    @Modifying
    @Query(value = "SET FOREIGN_KEY_CHECKS = 1", nativeQuery = true)
    void enableForeignKeyChecks();
}
