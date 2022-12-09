package com.example.SsuBlog.app.base.initData;

import com.example.SsuBlog.app.comment.Service.CommentService;
import com.example.SsuBlog.app.member.service.MemberService;
import com.example.SsuBlog.app.post.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevInitData implements InitDataBefore {
    @Bean
    CommandLineRunner initData(MemberService memberService,
                               PostService postService,
                               CommentService commentService) {
        return args -> {
            before(memberService, postService, commentService);
        };
    }
}
