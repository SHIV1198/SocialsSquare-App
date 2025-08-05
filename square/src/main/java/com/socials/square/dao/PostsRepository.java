package com.socials.square.dao;

import com.socials.square.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Integer> {
    Post findByUserId(String userId);

    List<Post> findAllByUserId(String userId);

    void deleteByUserIdAndContent(String userId, String content);
}
