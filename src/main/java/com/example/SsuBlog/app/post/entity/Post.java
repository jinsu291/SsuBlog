package com.example.SsuBlog.app.post.entity;

import com.example.SsuBlog.app.member.entity.Member;
import com.example.SsuBlog.app.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
public class Post extends BaseEntity {
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

