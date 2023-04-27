package com.project.ihealme.community.controller;

import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.dto.EditPostRequestDto;
import com.project.ihealme.community.dto.InsertPostRequestDto;
import com.project.ihealme.community.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<Post> posts(Model model) {
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);

        return posts;
//        return "community/posts";
    }

    @GetMapping("/{postNo}")
    public Post post(@PathVariable Long postNo, Model model) {
        Post post = postService.getPost(postNo);
        model.addAttribute("post", post);

        return post;
//        return "community/post";
    }

    @GetMapping("/write")
    public String writeForm() {
        return "community/writePost";
    }

    @PostMapping("/write")
    public Post writePost(InsertPostRequestDto insertPostRequestDto) {
        return postService.insertPost(insertPostRequestDto);
//        return "community/post";
    }

    @GetMapping("/{postNo}/edit")
    public Post editForm(@PathVariable Long postNo, Model model) {
        Post post = postService.getPost(postNo);
        model.addAttribute("post", post);

        return post;
//        return "community/editPost";
    }

    @PostMapping("/{postNo}/edit")
    public Post editPost(@PathVariable Long postNo, EditPostRequestDto editPostRequestDto) {
        Post updatedPost = postService.updatePost(postNo, editPostRequestDto);

        return updatedPost;
//        return "community/post";
    }
}
