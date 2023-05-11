package com.project.ihealme.community.controller;

import com.project.ihealme.community.dto.*;
import com.project.ihealme.community.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class PostController {

    private final PostService postService;

    @GetMapping
    public String posts(@ModelAttribute PostPageRequestDTO postPageRequestDTO, Model model) {
        Map<String, String> searchTypes = new LinkedHashMap<>();
        searchTypes.put("h", "병원명");
        searchTypes.put("t", "제목");
        searchTypes.put("u", "작성자");

        model.addAttribute("result", postService.getPostList(postPageRequestDTO));
        model.addAttribute("searchTypes", searchTypes);

        return "community/posts";
    }

    @GetMapping("/{postNo}")
    public String post(@PathVariable Long postNo,
                       @ModelAttribute PostPageRequestDTO postPageRequestDTO,
                       @CookieValue(value = "postView", required = false) Cookie postViewCookie,
                       HttpServletResponse response,
                       Model model) {

        //조회수 중복 방지를 위한 쿠키 사용
        boolean addHitCount = updateHitCount(postNo, postViewCookie, response);

        PostResponseDTO postResponseDTO = postService.getPost(postNo, addHitCount);
        model.addAttribute("post", postResponseDTO);

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
        model.addAttribute("post", postResponseDTO);

        return "community/editPost";
    }

    @PostMapping("/{postNo}/edit")
    public String editPost(@PathVariable Long postNo,
                           @ModelAttribute PostEditRequestDTO postEditRequestDTO,
                           @ModelAttribute PostPageRequestDTO postPageRequestDTO,
                           RedirectAttributes redirectAttributes) {

        postEditRequestDTO.setPostNo(postNo);
        postService.edit(postEditRequestDTO);

        Map<String, String> redirectAttrMap = createRedirectAttrMap(postNo, postPageRequestDTO);
        redirectAttributes.addAllAttributes(redirectAttrMap);

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

        Map<String, String> redirectAttrMap = createRedirectAttrMap(postNo, postPageRequestDTO);
        redirectAttributes.addAllAttributes(redirectAttrMap);

        return "redirect:/community/{postNo}";
    }

    private boolean updateHitCount(Long postNo, Cookie postViewCookie, HttpServletResponse response) {
        String postNoStr = postNo + "/";

        if (postViewCookie != null) {
            if (!postViewCookie.getValue().contains(postNoStr)) {
                postViewCookie.setValue(postViewCookie.getValue() + postNoStr);
                postViewCookie.setPath("/");
                postViewCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(postViewCookie);

                postService.addHitCount(postNo);
                return true;
            }
        } else {
            Cookie newPostViewCookie = new Cookie("postView", postNoStr);
            newPostViewCookie.setPath("/");
            newPostViewCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newPostViewCookie);

            postService.addHitCount(postNo);
            return true;
        }

        return false;
    }

    private Map<String, String> createRedirectAttrMap(Long postNo, PostPageRequestDTO postPageRequestDTO) {
        Map<String, String> redirectAttrMap = new HashMap<>();

        redirectAttrMap.put("postNo", String.valueOf(postNo));
        redirectAttrMap.put("page", String.valueOf(postPageRequestDTO.getPage()));
        redirectAttrMap.put("type", postPageRequestDTO.getType());
        redirectAttrMap.put("keyword", postPageRequestDTO.getKeyword());

        return redirectAttrMap;
    }
}
