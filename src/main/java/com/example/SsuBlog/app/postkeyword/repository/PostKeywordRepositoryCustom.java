package com.example.SsuBlog.app.postkeyword.repository;

import com.example.SsuBlog.app.postkeyword.entity.PostKeyword;

import java.util.List;

public interface PostKeywordRepositoryCustom {
    List<PostKeyword> getQslAllByAuthorId(Long authorId);
}
