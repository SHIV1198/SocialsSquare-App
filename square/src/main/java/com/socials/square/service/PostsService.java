package com.socials.square.service;

import com.socials.square.dao.PostsDAO;
import com.socials.square.dao.PostsRepository;
import com.socials.square.models.Post;
import com.socials.square.models.PostsMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostsService {

    private final PostsDAO postsDAO;

    public PostsService(PostsDAO postsDAO) {
        this.postsDAO = postsDAO;
    }

    public List<PostsMessageDTO> getAllPostsByUserId(String userId) {
        List<Post> posts = postsDAO.findAllPostsByUserId(userId);
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
    }
}
