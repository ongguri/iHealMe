package com.project.ihealme.community.controller;

import com.project.ihealme.community.dto.EditPostRequestDTO;
import com.project.ihealme.community.dto.InsertPostRequestDTO;
import com.project.ihealme.community.dto.PageRequestDTO;
import com.project.ihealme.community.dto.PostResponseDTO;
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
    public String posts(@ModelAttribute PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", postService.getList(pageRequestDTO));

        return "community/posts";
    }

    @GetMapping("/{postNo}")
    public String post(@ModelAttribute PageRequestDTO pageRequestDTO, @PathVariable Long postNo, Model model) {
        PostResponseDTO postResponseDTO = postService.get(postNo);
        model.addAttribute("dto", postResponseDTO);



        return "community/post";
    }

    @GetMapping("/write")
    public String writeForm() {
        return "community/writePost";
    }

    @PostMapping("/write")
    public String writePost(@ModelAttribute InsertPostRequestDTO insertPostRequestDTO, RedirectAttributes redirectAttributes) {
        Long postNo = postService.write(insertPostRequestDTO);
        redirectAttributes.addAttribute("postNo", postNo);
        return "redirect:/community/{postNo}";
    }

    @GetMapping("/{postNo}/edit")
    public String editForm(@PathVariable Long postNo, @ModelAttribute PageRequestDTO pageRequestDTO, Model model) {
        PostResponseDTO postResponseDTO = postService.get(postNo);
        model.addAttribute("dto", postResponseDTO);

        return "community/editPost";
    }

    @PostMapping("/{postNo}/edit")
    public String editPost(@PathVariable Long postNo,
                           @ModelAttribute EditPostRequestDTO editPostRequestDTO,
                           @ModelAttribute PageRequestDTO pageRequestDTO,
                           RedirectAttributes redirectAttributes) {

        editPostRequestDTO.setPostNo(postNo);
        postService.edit(editPostRequestDTO);

        redirectAttributes.addAttribute("postNo", postNo);
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
    public String report(@RequestParam Long postNo, @ModelAttribute PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        postService.addReport(postNo);

        redirectAttributes.addAttribute("postNo", postNo);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());

        return "redirect:/community/{postNo}";
    }
}