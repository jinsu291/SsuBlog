package com.example.SsuBlog.app.post.service;

import com.example.SsuBlog.app.member.entity.Member;
import com.example.SsuBlog.app.post.entity.Post;
import com.example.SsuBlog.app.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public Post write(Member author, String subject, String content, String contentHtml) {
        Post post = Post.builder()
                .subject(subject)
                .content(content)
                .contentHtml(contentHtml)
                .author(author)
                .build();
        postRepository.save(post);

        return post;
    }

    public Optional<Post> findById(long postId) {
        return postRepository.findById(postId);
    }


    public void modify(Post post, String subject, String content, String contentHtml) {
        post.setSubject(subject);
        post.setContent(content);
        post.setContentHtml(contentHtml);
    }

    public boolean actorCanModify(Member author, Post post) {
        return author.getId().equals(post.getAuthor().getId());
    }

    public boolean actorCanRemove(Member author, Post post) {
        return actorCanModify(author, post);
    }

    public void remove(Post post) {
        postRepository.delete(post);
    }

    public Optional<Post> findForPrintById(long id) {
        Optional<Post> opPost = findById(id);

        if (opPost.isEmpty()) return opPost;

        return opPost;
    }

    public boolean actorCanSee(Member actor, Post post) {
        if ( actor == null ) return false;
        if ( post == null ) return false;

        return post.getAuthor().getId().equals(actor.getId());
    }
}
