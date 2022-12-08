package com.example.SsuBlog;

import com.example.SsuBlog.app.comment.repository.CommentRepository;
import com.example.SsuBlog.app.post.entity.Post;
import com.example.SsuBlog.app.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PostTests {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testJpa() {
        Post p1 = new Post();
        p1.setSubject("sbb가 무엇인가요?");
        p1.setContent("sbb에 대해서 알고 싶습니다.");
        p1.setCreateDate(LocalDateTime.now());
        postRepository.save(p1);

        Post p2 = new Post();
        p2.setSubject("스프링부트 모델 질문입니다.");
        p2.setContent("id는 자동으로 생성되나요?");
        p2.setCreateDate(LocalDateTime.now());
        postRepository.save(p2);

        assertThat(p1.getId()).isGreaterThan(0);
        assertThat(p2.getId()).isGreaterThan(p1.getId());
    }

    @Test
    void testJpa2() {
        // SELECT * FROM question
        List<Post> all = postRepository.findAll();
        assertEquals(2, all.size());

        Post p = all.get(0);
        assertEquals("sbb가 무엇인가요?", p.getSubject());
    }

    @Test
    void testJpa3() {
        // SELECT * FROM question
        Post p =  postRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, p.getId());
    }

    @Test
    void testJpa4() {
        Post p = this.postRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
        assertEquals(1, p.getId());
    }

    @Test
    void testJpa5() {
        List<Post> pList = this.postRepository.findBySubjectLike("sbb%");
        Post p = pList.get(0);
        assertEquals("sbb가 무엇인가요?", p.getSubject());
    }

    @Test
    void testJpa6() {
        Optional<Post> op = this.postRepository.findById(1);
        assertTrue(op.isPresent());
        Post q = op.get();
        q.setSubject("수정된 제목");
        this.postRepository.save(q); // UPDATE
    }

    @Test
    void testJpa7() {
        assertEquals(2, this.postRepository.count());
        Optional<Post> oq = this.postRepository.findById(1);
        assertTrue(oq.isPresent());
        Post q = oq.get();

        this.postRepository.delete(q);

        assertEquals(1, this.postRepository.count());
    }
}