package com.example.SsuBlog.app.postTag.entity;

import com.example.SsuBlog.app.member.entity.Member;
import com.example.SsuBlog.app.post.entity.Post;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.example.SsuBlog.app.base.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class PostTag extends BaseEntity {
    @ManyToOne
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @ManyToOne
    @ToString.Exclude
    private Member member;
}
