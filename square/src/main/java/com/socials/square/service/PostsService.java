package com.socials.square.service;

import com.socials.square.dao.PostsDAO;
import com.socials.square.dao.PostsRepository;
import com.socials.square.models.Post;
import com.socials.square.models.PostsMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostsService {
    public final Logger logger = LoggerFactory.getLogger(PostsService.class);

    private final PostsDAO postsDAO;

    public PostsService(@Qualifier("JPA_Dao") PostsDAO postsDAO) {
        this.postsDAO = postsDAO;
    }

    public List<PostsMessageDTO> getAllPostsByUserId(String userId) {
        logger.info("Getting all posts for user: {}", userId);
        List<Post> posts = postsDAO.findAllPostsByUserId(userId);

        if (posts.isEmpty()) {
            logger.info("No posts found for user: {}", userId);
            return List.of();
        }
        List<PostsMessageDTO> userPosts = posts.stream()
                .map(post -> new PostsMessageDTO(post.getUserId(), post.getContent()))
                .collect(Collectors.toList());
        return userPosts;
    }


    public void createPostByUser(PostsMessageDTO post) {
        Post newPost = Post.builder()
                .userId(post.userId())
                .content(post.content())
                .build();
        postsDAO.createNewPost(newPost);
        logger.info("Post created successfully for user: {}", post.userId());
    }

    public void deleteUsersPost(PostsMessageDTO post) {
        postsDAO.deletePost(post);
        logger.info("Post deleted successfully for user: {}", post.userId());
    }
}
