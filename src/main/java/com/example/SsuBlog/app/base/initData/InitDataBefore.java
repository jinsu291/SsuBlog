package com.example.SsuBlog.app.base.initData;

import com.example.SsuBlog.app.member.entity.Member;
import com.example.SsuBlog.app.member.service.MemberService;
import com.example.SsuBlog.app.post.service.PostService;

public interface InitDataBefore {
    default void before(MemberService memberService, PostService postService) {
        Member member1 = memberService.join("user1", "1234", "user1@test.com");
        Member member2 = memberService.join("user2", "1234", "user2@test.com");
    }
}
