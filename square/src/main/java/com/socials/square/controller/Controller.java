package com.socials.square.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String index() {
        return "HI! There, Welcome to SocialsSquare";
    }

}
