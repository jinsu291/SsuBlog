package com.example.SsuBlog.app.postTag.service;

import com.example.SsuBlog.app.member.entity.Member;
import com.example.SsuBlog.app.post.entity.Post;
import com.example.SsuBlog.app.postTag.entity.PostTag;
import com.example.SsuBlog.app.postTag.repository.PostTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostTagService {
    private final PostTagRepository postTagRepository;

    public void applyPostTags(Post post, String postTagContents) {
        List<PostTag> oldPostTags = getPostTags(post);

        List<String> postKeywordContents = Arrays.stream(postTagContents.split("#"))
                .map(String::trim)
                .filter(s -> s.length() > 0)
                .collect(Collectors.toList());

        List<PostTag> needToDelete = new ArrayList<>();

        needToDelete.forEach(postTag -> postTagRepository.delete(postTag));
    }

    public List<PostTag> getPostTags(Post post) {
        return postTagRepository.findAllByPostId(post.getId());
    }

    public List<PostTag> getPostTagsByPostIdIn(long[] ids) {
        return postTagRepository.findAllByPostIdIn(ids);
    }

    public List<PostTag> getPostTags(Member member, String postKeywordContent) {
        return postTagRepository.findAllByMemberIdAndPostKeyword_contentOrderByPost_idDesc(member.getId(), postKeywordContent);
    }

    public List<PostTag> getPostTags(long authorId, long postKeywordId) {
        return postTagRepository.findAllByMemberIdAndPostKeywordIdOrderByPost_idDesc(authorId, postKeywordId);
    }
}
