package com.project.ihealme.community.controller;

import com.project.ihealme.community.dto.*;
import com.project.ihealme.community.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class PostController {

    private final PostService postService;

    @GetMapping
    public String posts(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", postService.getList(pageRequestDTO));

        return "community/posts";
    }

    @GetMapping("/{postNo}")
    public String post(@ModelAttribute PageRequestDTO pageRequestDTO, @PathVariable Long postNo, Model model) {
        PostDTO postDTO = postService.get(postNo);
        model.addAttribute("dto", postDTO);

        return "community/post";
    }

    @GetMapping("/write")
    public String writeForm() {
        return "community/writePost";
    }

    @PostMapping("/write")
    public String writePost(PostDTO postDTO, RedirectAttributes redirectAttributes) {
        Long postNo = postService.write(postDTO);
        redirectAttributes.addAttribute("postNo", postNo);
        return "redirect:/community/{postNo}";
    }

    @GetMapping("/{postNo}/edit")
    public String editForm(@ModelAttribute PageRequestDTO pageRequestDTO, @PathVariable Long postNo, Model model) {
        PostDTO postDTO = postService.get(postNo);
        model.addAttribute("dto", postDTO);

        return "community/editPost";
    }

    @PostMapping("/{postNo}/edit")
    public String editPost(PostDTO postDTO, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        postService.edit(postDTO);

        redirectAttributes.addAttribute("postNo", postDTO.getPostNo());
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());

        return "redirect:/community/{postNo}";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long postNo) {
        postService.deleteWithReplies(postNo);

        return "redirect:/community";
    }

    @PostMapping("/report")
    public String report(@RequestParam Long postNo, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        postService.addReport(postNo);

        redirectAttributes.addAttribute("postNo", postNo);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());

        return "redirect:/community/{postNo}";
    }
}
