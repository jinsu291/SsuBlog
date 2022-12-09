package com.example.SsuBlog.app.post.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
public class PostForm {
    @NotEmpty
    private String subject;
    @NotBlank
    private String content;
    @NotBlank
    private String contentHtml;
}
