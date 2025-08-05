package com.socials.square.controller;

import com.socials.square.models.PostsMessageDTO;
import com.socials.square.service.PostsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/v1")
public class Controller {

    public final Logger logger = LoggerFactory.getLogger(Controller.class);

    private final PostsService postsService;

    public Controller(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/")
    public String index() {
        return "HI! There, Welcome to SocialsSquare";
    }


    @GetMapping("/posts/{userId}")
    public List<PostsMessageDTO> getAllPostsOfUser(String userId) {
        List<PostsMessageDTO> allPostsByUserId = postsService.getAllPostsByUserId(userId);
        return allPostsByUserId;
    }

    @PostMapping("/post/{userId}")
    public void createPost(@RequestBody PostsMessageDTO post) {
        postsService.createPostByUser(post);
    }

    @DeleteMapping("/post/delete")
    public void deletePost(@RequestBody PostsMessageDTO post) {
        postsService.deleteUsersPost(post);
    }

}
