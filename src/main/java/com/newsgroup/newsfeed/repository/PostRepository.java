package com.newsgroup.newsfeed.repository;

import com.newsgroup.newsfeed.entity.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Long> {
}
