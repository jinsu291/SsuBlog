package com.example.SsuBlog.app.base.initData;

import com.example.SsuBlog.app.member.entity.Member;
import com.example.SsuBlog.app.member.service.MemberService;
import com.example.SsuBlog.app.post.service.PostService;

public interface InitDataBefore {
    default void before(MemberService memberService, PostService postService) {
        Member member1 = memberService.join("user1", "1234", "user1@test.com");
        Member member2 = memberService.join("user2", "1234", "user2@test.com");

        postService.write(
                member1,
                "자바를 우아하게 사용하는 방법",
                "# 내용 1",
                "<h1>내용 1</h1>",
                "#IT #자바 #카프카"
        );

        postService.write(
                member1,
                "자바스크립트를 우아하게 사용하는 방법",
                """
                        # 자바스크립트는 이렇게 쓰세요.
                                                
                        ```js
                        const a = 10;
                        console.log(a);
                        ```
                        """.stripIndent(),
                """
                        <h1>자바스크립트는 이렇게 쓰세요.</h1><div data-language="js" class="toastui-editor-ww-code-block-highlighting"><pre class="language-js"><code data-language="js" class="language-js"><span class="token keyword">const</span> a <span class="token operator">=</span> <span class="token number">10</span><span class="token punctuation">;</span>
                        <span class="token console class-name">console</span><span class="token punctuation">.</span><span class="token method function property-access">log</span><span class="token punctuation">(</span>a<span class="token punctuation">)</span><span class="token punctuation">;</span></code></pre></div>
                                                """.stripIndent(),
                "#IT #프론트엔드 #리액트"
        );
        postService.write(member2, "제목 3", "내용 3", "내용 3", "#IT# 프론트엔드 #HTML #CSS");
        postService.write(member2, "제목 4", "내용 4", "내용 4", "#IT #스프링부트 #자바");
        postService.write(member1, "제목 5", "내용 5", "내용 5", "#IT #자바 #카프카");
        postService.write(member1, "제목 6", "내용 6", "내용 6", "#IT #프론트엔드 #REACT");
        postService.write(member2, "제목 7", "내용 7", "내용 7", "#IT# 프론트엔드 #HTML #CSS");
        postService.write(member2, "제목 8", "내용 8", "내용 8", "#IT #스프링부트 #자바");
    }
}
