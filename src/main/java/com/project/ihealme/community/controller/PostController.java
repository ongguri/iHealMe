package com.project.ihealme.community.controller;

import com.project.ihealme.community.domain.SearchType;
import com.project.ihealme.community.dto.*;
import com.project.ihealme.community.service.PostService;
import com.project.ihealme.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class PostController {

    private final PostService postService;

    @GetMapping
    public String posts(@ModelAttribute("postPageReq") PostPageRequestDTO postPageRequestDTO,
                        Model model) {
        model.addAttribute("postPageRes", postService.getPostList(postPageRequestDTO));
        model.addAttribute("searchTypes", SearchType.values());

        return "community/posts";
    }

    @GetMapping("/{postNo}")
    public String post(@AuthenticationPrincipal User user,
                       @PathVariable Long postNo,
                       @ModelAttribute("postPageReq") PostPageRequestDTO postPageRequestDTO,
                       @CookieValue(value = "postView", required = false) Cookie postViewCookie,
                       HttpServletResponse response,
                       Model model) {

        //조회수 중복 방지를 위한 쿠키 사용
        boolean addHitCount = updateHitCount(postNo, user, postViewCookie, response);
        PostResponseDTO postResponseDTO = postService.getPost(postNo, addHitCount);

        model.addAttribute("postRes", postResponseDTO);
        model.addAttribute("user", user);

        return "community/post";
    }

    @PostMapping("/write")
    public String writePost(@AuthenticationPrincipal User user,
                            @Valid @ModelAttribute("postWriteReq") PostWriteRequestDTO postWriteRequestDTO,
                            RedirectAttributes redirectAttributes) {

        Long postNo = postService.writePost(user, postWriteRequestDTO);
        redirectAttributes.addAttribute("postNo", postNo);

        return "redirect:/community/{postNo}";
    }

    @GetMapping("/{postNo}/edit")
    public String editForm(@AuthenticationPrincipal User user,
                           @PathVariable Long postNo,
                           @ModelAttribute("postPageReq") PostPageRequestDTO postPageRequestDTO,
                           Model model) {

        model.addAttribute("postRes", postService.getPostEditForm(postNo, user));

        return "community/editPost";
    }

    @PostMapping("/{postNo}/edit")
    public String editPost(@AuthenticationPrincipal User user,
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
    public String delete(@AuthenticationPrincipal User user,
                         @PathVariable Long postNo) {

        postService.deleteWithReplies(postNo, user);
        return "redirect:/community";
    }

    @PostMapping("/{postNo}/report")
    public ResponseEntity<String> report(@AuthenticationPrincipal User user,
                                 @PathVariable Long postNo,
                                 @ModelAttribute PostPageRequestDTO postPageRequestDTO,
                                 @CookieValue(value = "reportedPost", required = false) Cookie reportedPostCookie,
                                 HttpServletResponse response,
                                 RedirectAttributes redirectAttributes) {

        //신고 중복 방지를 위한 쿠키 사용
        boolean addReportCount = updateReportCount(postNo, reportedPostCookie, response);
        ResponseEntity responseEntity = new ResponseEntity("notAddReport", HttpStatus.ALREADY_REPORTED);

        if (addReportCount) {
            postService.addReport(postNo, user);
            responseEntity = new ResponseEntity("addReport", HttpStatus.OK);
        }

        Map<String, String> redirectAttrMap = createRedirectAttrMap(postNo, postPageRequestDTO);
        redirectAttributes.addAllAttributes(redirectAttrMap);

        return responseEntity;
    }

    private boolean updateHitCount(Long postNo, User user, Cookie postViewCookie, HttpServletResponse response) {
        if (postService.isWriterOfPost(postNo, user)) {
            return false;
        }

        String cookieName = "postViewCookie";
        int maxAgeHour = 1;

        return addCookie(postNo, postViewCookie, cookieName, maxAgeHour, response);
    }

    private boolean updateReportCount(Long postNo, Cookie reportedPostCookie, HttpServletResponse response) {
        String cookieName = "reportedPostCookie";
        int maxAgeHour = 24;

        return addCookie(postNo, reportedPostCookie, cookieName, maxAgeHour, response);
    }

/*    private boolean checkPostViewCookie(Long postNo, Cookie postViewCookie, HttpServletResponse response) {
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
    }*/

    private boolean addCookie(Long postNo, Cookie cookie, String cookieName, int maxAgeHour, HttpServletResponse response) {
        String postNoStr = postNo + "/";

        if (cookie != null) {
            if (!cookie.getValue().contains(postNoStr)) {
                cookie.setValue(cookie.getValue() + postNoStr);
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * maxAgeHour);
                response.addCookie(cookie);
                return true;
            }
        } else {
            Cookie newCookie = new Cookie(cookieName, postNoStr);
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * maxAgeHour);
            response.addCookie(newCookie);
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
