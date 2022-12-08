package com.example.SsuBlog.app.comment.repository;

import com.example.SsuBlog.app.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
