package com.example.SsuBlog.repository;

import com.example.SsuBlog.app.post.entity.Post;
import com.example.SsuBlog.app.post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostRepositoryTests {
    @Autowired
    private PostRepository postRepository;
    private static int lastSampleDataId;

    @BeforeEach
    void beforeEach() {
        clearData();
        createSampleData();
    }

    private void createSampleData() {
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

        lastSampleDataId = p2.getId();
    }

    private void clearData() {
        postRepository.disableForeignKeyChecks();
        postRepository.truncate();
        postRepository.enableForeignKeyChecks();
    }

    @Test
    void 저장() {
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

        assertThat(p1.getId()).isEqualTo(lastSampleDataId + 1);
        assertThat(p2.getId()).isEqualTo(lastSampleDataId + 2);
    }

    @Test
    void 삭제() {
        assertThat(postRepository.count()).isEqualTo(lastSampleDataId);

        Post p = this.postRepository.findById(1).get();
        postRepository.delete(p);

        assertThat(postRepository.count()).isEqualTo(lastSampleDataId - 1);
    }

    @Test
    void 수정() {
        Post p = this.postRepository.findById(1).get();
        p.setSubject("수정된 제목");
        postRepository.save(p);

        p = this.postRepository.findById(1).get();

        assertThat(p.getSubject()).isEqualTo("수정된 제목");
    }

    @Test
    void findAll() {
        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(lastSampleDataId);

        Post p = all.get(0);
        assertThat(p.getSubject()).isEqualTo("sbb가 무엇인가요?");
    }

    @Test
    void findBySubject() {
        Post p = postRepository.findBySubject("sbb가 무엇인가요?");
        assertThat(p.getId()).isEqualTo(1);
    }

    @Test
    void findBySubjectAndContent() {
        Post p = postRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
        assertThat(p.getId()).isEqualTo(1);
    }

    @Test
    void findBySubjectLike() {
        List<Post> qList = postRepository.findBySubjectLike("sbb%");
        Post p = qList.get(0);

        assertThat(p.getSubject()).isEqualTo("sbb가 무엇인가요?");
    }
}