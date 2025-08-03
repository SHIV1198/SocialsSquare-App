package com.socials.square.controller;

import com.socials.square.models.PostsMessageDTO;
import com.socials.square.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    private final PostsService postsService;

    public Controller(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/")
    public String index() {
        return "HI! There, Welcome to SocialsSquare";
    }
    @ResponseBody
    @GetMapping("/posts/{userId}")
    public List<PostsMessageDTO> getAllPostsOfUser(String userId) {
        List<PostsMessageDTO> allPostsByUserId = postsService.getAllPostsByUserId(userId);
        return allPostsByUserId;
    }

    @PostMapping("/post/{userId}")
    public void createPost(@RequestBody PostsMessageDTO post) {
        postsService.createPostByUser(post);
    }

}
