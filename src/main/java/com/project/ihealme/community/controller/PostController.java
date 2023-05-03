package com.project.ihealme.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
public class PostController {


    @GetMapping("/posts")
    public String posts() {
        return "community/posts";
    }

    @GetMapping("/post")
    public String post() {
        return "community/post";
    }

    @GetMapping("/edit")
    public String editForm() {
        return "community/editPost";
    }

    @PostMapping("/edit")
    public String editPost() {
        return "community/post";
    }

    @GetMapping("/write")
    public String writeForm() {
        return "community/writePost";
    }

    @PostMapping("/write")
    public String writePost() {
        return "community/post";
    }

}
