package com.project.ihealme.community.controller;

import com.project.ihealme.community.dto.*;
import com.project.ihealme.community.service.PostService;
import com.project.ihealme.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class PostController {

    private final PostService postService;

    @ModelAttribute("user")
    public User temporaryUser() {
        User user = new User();
        user.setUserId(1L);
        user.setEmail("longlee@naver.com");

        return user;
    }

    @GetMapping
    public String posts(@ModelAttribute("postPageReq") PostPageRequestDTO postPageRequestDTO, Model model) {
        Map<String, String> searchTypes = new LinkedHashMap<>();
        searchTypes.put("h", "병원명");
        searchTypes.put("t", "제목");
        searchTypes.put("u", "작성자");

        model.addAttribute("result", postService.getPostList(postPageRequestDTO));
        model.addAttribute("searchTypes", searchTypes);

        return "community/posts";
    }

    @GetMapping("/{postNo}")
    public String post(@ModelAttribute("user") User user,
                       @PathVariable Long postNo,
                       @ModelAttribute("postPageReq") PostPageRequestDTO postPageRequestDTO,
                       @CookieValue(value = "postView", required = false) Cookie postViewCookie,
                       HttpServletResponse response,
                       Model model) {

        //조회수 중복 방지를 위한 쿠키 사용
        boolean addHitCount = updateHitCount(postNo, user, postViewCookie, response);
        PostResponseDTO postResponseDTO = postService.getPost(postNo, addHitCount);

        model.addAttribute("postRes", postResponseDTO);
        //model.addAttribute("user", user);

        return "community/post";
    }

    @PostMapping("/write")
    public String writePost(@ModelAttribute("user") User user,
                            @Valid @ModelAttribute("postWriteReq") PostWriteRequestDTO postWriteRequestDTO,
                            RedirectAttributes redirectAttributes) {

        Long postNo = postService.writePost(user, postWriteRequestDTO);
        redirectAttributes.addAttribute("postNo", postNo);

        return "redirect:/community/{postNo}";
    }

    @GetMapping("/{postNo}/edit")
    public String editForm(@ModelAttribute("user") User user,
                           @PathVariable Long postNo,
                           @ModelAttribute("postPageReq") PostPageRequestDTO postPageRequestDTO,
                           Model model) {

        model.addAttribute("postRes", postService.getPostEditForm(postNo, user));

        return "community/editPost";
    }

    @PostMapping("/{postNo}/edit")
    public String editPost(@ModelAttribute User user,
                           @PathVariable Long postNo,
                           @Valid @ModelAttribute("postEditReq") PostEditRequestDTO postEditRequestDTO,
                           @ModelAttribute("postPageReq") PostPageRequestDTO postPageRequestDTO,
                           RedirectAttributes redirectAttributes) {

        postService.edit(postNo, user, postEditRequestDTO);

        Map<String, String> redirectAttrMap = createRedirectAttrMap(postNo, postPageRequestDTO);
        redirectAttributes.addAllAttributes(redirectAttrMap);

        return "redirect:/community/{postNo}";
    }


    @PostMapping("/{postNo}/delete")
    public String delete(@ModelAttribute("user") User user,
                         @PathVariable Long postNo) {

        postService.deleteWithReplies(postNo, user);

        return "redirect:/community";
    }

    @PostMapping("/{postNo}/report")
    public String report(@ModelAttribute("user") User user,
                         @PathVariable Long postNo,
                         @ModelAttribute PostPageRequestDTO postPageRequestDTO,
                         RedirectAttributes redirectAttributes) {

        postService.addReport(postNo, user);

        Map<String, String> redirectAttrMap = createRedirectAttrMap(postNo, postPageRequestDTO);
        redirectAttributes.addAllAttributes(redirectAttrMap);

        return "redirect:/community/{postNo}";
    }

    private boolean updateHitCount(Long postNo, User user, Cookie postViewCookie, HttpServletResponse response) {
        if (postService.checkUserOfPost(postNo, user)) {
            return false;
        }

        return checkPostViewCookie(postNo, postViewCookie, response);
    }

    private boolean checkPostViewCookie(Long postNo, Cookie postViewCookie, HttpServletResponse response) {
        String postNoStr = postNo + "/";

        if (postViewCookie != null) {
            if (!postViewCookie.getValue().contains(postNoStr)) {
                postViewCookie.setValue(postViewCookie.getValue() + postNoStr);
                postViewCookie.setPath("/");
                postViewCookie.setMaxAge(60 * 60);
                response.addCookie(postViewCookie);

                postService.addHitCount(postNo);
                return true;
            }
        } else {
            Cookie newPostViewCookie = new Cookie("postView", postNoStr);
            newPostViewCookie.setPath("/");
            newPostViewCookie.setMaxAge(60 * 60);
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
