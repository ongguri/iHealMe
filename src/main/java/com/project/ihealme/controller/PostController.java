package com.project.ihealme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
public class PostController {

    @GetMapping("/posts")
    public String posts() {
        return "/community/posts";
    }

    @GetMapping("/post")
    public String post() {
        return "/community/post";
    }

    @GetMapping("/edit")
    public String edit() {
        return "/community/editPost";
    }

    @GetMapping("/write")
    public String write() {
        return "/community/writePost";
    }

}
