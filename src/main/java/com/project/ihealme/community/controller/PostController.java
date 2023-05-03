package com.project.ihealme.community.controller;

import com.project.ihealme.community.dto.*;
import com.project.ihealme.community.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class PostController {

    private final PostService postService;

    @GetMapping
    public String posts(@ModelAttribute PostPageRequestDTO postPageRequestDTO, Model model) {
        model.addAttribute("result", postService.getPostList(postPageRequestDTO));

        return "community/posts";
    }

    @GetMapping("/{postNo}")
    public String post(@ModelAttribute PostPageRequestDTO postPageRequestDTO, @PathVariable Long postNo, Model model) {
        Map<String, Object> map = model.asMap();

        boolean addHitCount = true;

        for (String modelKey : map.keySet()) {
            if (modelKey.equals("addHitCount")) {
                addHitCount = (Boolean) map.get(modelKey);
            }
        }

        PostResponseDTO postResponseDTO = postService.getPost(postNo, addHitCount);
        model.addAttribute("dto", postResponseDTO);

        return "community/post";
    }

    @GetMapping("/write")
    public String writeForm() {
        return "community/writePost";
    }

    @PostMapping("/write")
    public String writePost(@ModelAttribute InsertPostRequestDTO insertPostRequestDTO, RedirectAttributes redirectAttributes) {
        Long postNo = postService.writePost(insertPostRequestDTO);
        redirectAttributes.addAttribute("postNo", postNo);
        return "redirect:/community/{postNo}";
    }

    @GetMapping("/{postNo}/edit")
    public String editForm(@PathVariable Long postNo, @ModelAttribute PostPageRequestDTO postPageRequestDTO, Model model) {
        PostResponseDTO postResponseDTO = postService.getPost(postNo, false);
        model.addAttribute("dto", postResponseDTO);

        return "community/editPost";
    }

    @PostMapping("/{postNo}/edit")
    public String editPost(@PathVariable Long postNo,
                           @ModelAttribute EditPostRequestDTO editPostRequestDTO,
                           @ModelAttribute PostPageRequestDTO postPageRequestDTO,
                           RedirectAttributes redirectAttributes) {

        editPostRequestDTO.setPostNo(postNo);
        postService.edit(editPostRequestDTO);

        redirectAttributes.addAttribute("postNo", postNo);
        redirectAttributes.addAttribute("page", postPageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", postPageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", postPageRequestDTO.getKeyword());
        redirectAttributes.addFlashAttribute("addHitCount", false);

        return "redirect:/community/{postNo}";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long postNo) {
        postService.deleteWithReplies(postNo);

        return "redirect:/community";
    }

    @PostMapping("/report")
    public String report(@RequestParam Long postNo, @ModelAttribute PostPageRequestDTO postPageRequestDTO, RedirectAttributes redirectAttributes) {

        postService.addReport(postNo);

        redirectAttributes.addAttribute("postNo", postNo);
        redirectAttributes.addAttribute("page", postPageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", postPageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", postPageRequestDTO.getKeyword());
        redirectAttributes.addFlashAttribute("addHitCount", false);

        return "redirect:/community/{postNo}";
    }
}
