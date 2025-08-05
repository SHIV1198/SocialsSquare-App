package com.socials.square.dao;

import com.socials.square.models.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("DaoRepository")
public interface PostsDAO {

    List<Post> findAllPostsByUserId(String userId);

    void createNewPost(Post newPost);
}
