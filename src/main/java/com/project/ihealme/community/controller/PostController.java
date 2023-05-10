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
    public String posts(@ModelAttribute PostPageRequestDTO postPageRequestDTO, Model model) {
        model.addAttribute("result", postService.getPostList(postPageRequestDTO));

        return "community/posts";
    }

    @GetMapping("/{postNo}")
    public String post(@RequestParam(name = "add", defaultValue = "false") boolean addHitCount,
                       @ModelAttribute PostPageRequestDTO postPageRequestDTO,
                       @PathVariable Long postNo,
                       Model model) {

        PostResponseDTO postResponseDTO = postService.getPost(postNo, addHitCount);
        model.addAttribute("dto", postResponseDTO);

        return "community/post";
    }

    @PostMapping("/write")
    public String writePost(@ModelAttribute PostWriteRequestDTO postWriteRequestDTO, RedirectAttributes redirectAttributes) {
        Long postNo = postService.writePost(postWriteRequestDTO);
        redirectAttributes.addAttribute("postNo", postNo);

        return "redirect:/community/{postNo}";
    }

    @GetMapping("/{postNo}/edit")
    public String editForm(@PathVariable Long postNo, @ModelAttribute PostPageRequestDTO postPageRequestDTO, Model model) {
        PostResponseDTO postResponseDTO = postService.getPost(postNo);
        model.addAttribute("dto", postResponseDTO);

        return "community/editPost";
    }

    @PostMapping("/{postNo}/edit")
    public String editPost(@PathVariable Long postNo,
                           @ModelAttribute PostEditRequestDTO postEditRequestDTO,
                           @ModelAttribute PostPageRequestDTO postPageRequestDTO,
                           RedirectAttributes redirectAttributes) {

        postEditRequestDTO.setPostNo(postNo);
        postService.edit(postEditRequestDTO);

        redirectAttributes.addAttribute("postNo", postNo);
        redirectAttributes.addAttribute("page", postPageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", postPageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", postPageRequestDTO.getKeyword());

        return "redirect:/community/{postNo}";
    }

    @PostMapping("/{postNo}/delete")
    public String delete(@PathVariable Long postNo) {
        postService.deleteWithReplies(postNo);

        return "redirect:/community";
    }

    @PostMapping("/{postNo}/report")
    public String report(@PathVariable Long postNo,
                         @ModelAttribute PostPageRequestDTO postPageRequestDTO,
                         RedirectAttributes redirectAttributes) {

        postService.addReport(postNo);

        redirectAttributes.addAttribute("postNo", postNo);
        redirectAttributes.addAttribute("page", postPageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", postPageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", postPageRequestDTO.getKeyword());
        redirectAttributes.addFlashAttribute("report", true);

        return "redirect:/community/{postNo}";
    }
}
