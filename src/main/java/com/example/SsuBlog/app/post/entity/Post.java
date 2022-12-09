package com.example.SsuBlog.app.post.entity;

import com.example.SsuBlog.app.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
public class Post {
    @ManyToOne(fetch = LAZY)
    private Member author;

    @Column(length = 200) // varchar(200)
    private String subject;

    @Column(columnDefinition = "LONGTEXT")
    private String content;
    @Column(columnDefinition = "LONGTEXT")
    private String contentHtml;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;


    public String getForPrintContentHtml() {
        return contentHtml.replaceAll("toastui-editor-ww-code-block-highlighting", "");
    }
}

