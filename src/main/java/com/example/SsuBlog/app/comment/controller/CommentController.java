package com.example.SsuBlog.app.comment.controller;

import com.example.SsuBlog.app.comment.Service.CommentService;
import com.example.SsuBlog.app.comment.entity.Comment;
import com.example.SsuBlog.app.comment.form.CommentForm;
import com.example.SsuBlog.app.member.entity.Member;
import com.example.SsuBlog.app.member.service.MemberService;
import com.example.SsuBlog.app.post.entity.Post;
import com.example.SsuBlog.app.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/comment")
@Controller
@RequiredArgsConstructor
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;
    private final MemberService memberService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String detail(Principal principal, Model model, @PathVariable long id, @Valid CommentForm commentForm, BindingResult bindingResult) {
        Post post = this.postService.getPost(id);

        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "post/detail";
        }

        Member author = memberService.getMember(principal.getName());

        // 답변 등록 시작
        Comment comment = commentService.create(post, commentForm.getContent(), author);
        // 답변 등록 끝

        return "redirect:/question/detail/%d#answer_%d".formatted(id, comment.getId());
    }
}
