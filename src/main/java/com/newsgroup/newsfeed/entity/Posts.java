package com.newsgroup.newsfeed.entity;

import com.newsgroup.newsfeed.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@Builder
public class Posts extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String content;
    private Long thumbsUpCount = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<PostComments> postCommentsList;

    public Posts(String email,
                 String content,
                 Long thumbsUpCount,
                 Users users
    ) {
        this.email = email;
        this.content = content;
        this.thumbsUpCount = thumbsUpCount;
        this.user = user;

    }

    public void update(String content) {
        this.content = content;
    }

    // 좋아요수 증가 메서드
    public void increaseThumbsUp() {
        this.thumbsUpCount++;
    }

    // 댓글 수를 반환하는 메서드
    public Long getCommentsCount() {
        return (long) postCommentsList.size();
    }

}
