package com.project.ihealme.community.service;

import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.domain.User;
import com.project.ihealme.community.dto.PageRequestDTO;
import com.project.ihealme.community.dto.PageResultDTO;
import com.project.ihealme.community.dto.PostDTO;
import com.project.ihealme.community.repository.CommentRepository;
import com.project.ihealme.community.repository.PostRepository;
import com.project.ihealme.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public Long write(PostDTO postDTO) {
        Post post = dtoToEntity(postDTO);
        Post savedPost = postRepository.save(post);

        return savedPost.getPostNo();
    }

    public PageResultDTO<PostDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        Function<Object[], PostDTO> fn = (en -> entityToDTO((Post) en[0], (User) en[1], 0));

        Page<Object[]> result = null;
        String type = pageRequestDTO.getType();
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("postNo").descending());

        if (type == null || type.equals("")) {
            result = postRepository.findPostWithUser(pageable);
        } else {
            String keyword = pageRequestDTO.getKeyword();

            switch (type) {
                case "h":
                    result = postRepository.findByHptNameContaining(keyword, pageable);
                    break;
                case "t":
                    result = postRepository.findByTitleContaining(keyword, pageable);
                    break;
                case "u":
                    result = postRepository.findByUserEmailContaining(keyword, pageable);
                    break;
            }
        }

        return new PageResultDTO<>(result, fn);
    }

    public PostDTO get(Long postNo) {
        Object result = postRepository.findPostByPostNo(postNo);
        Object[] arr = (Object[]) result;
        Post post = (Post) arr[0];

        post.addHitCount();
        postRepository.save(post);

        return entityToDTO(post, (User) arr[1], 0);
    }

    @Transactional
    public void deleteWithReplies(Long postNo) {
        // comment 삭제
        postRepository.deleteById(postNo);
    }

    public void edit(PostDTO postDTO) {
        Post post = postRepository.findById(postDTO.getPostNo()).get();
        post.changeTitle(postDTO.getTitle());
        post.changeContent(postDTO.getContent());

        postRepository.save(post);
    }

    public void addReport(Long postNo) {
        Post post = postRepository.findById(postNo).get();
        post.addReportCount();

        postRepository.save(post);
    }

    public Post dtoToEntity(PostDTO postDTO) {
        User user = User.builder().userEmail(postDTO.getUserEmail()).build();

        Post post = Post.builder()
                .postNo(postDTO.getPostNo())
                .resNo(postDTO.getResNo())
                .user(userRepository.findByUserEmail(postDTO.getUserEmail()))
                .hptName(postDTO.getHptName())
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .hit(postDTO.getHit())
                .report(postDTO.getReport())
                .build();

        return post;
    }

    public PostDTO entityToDTO(Post post, User user, int commentCount) {
        PostDTO postDTO = PostDTO.builder()
                .postNo(post.getPostNo())
                .resNo(post.getResNo())
                .hptName(post.getHptName())
                .title(post.getTitle())
                .content(post.getContent())
                .userEmail(user.getUserEmail())
                .regDate(post.getRegdate())
                .hit(post.getHit())
                .report(post.getReport())
                .commentCount(commentCount)
                .build();

        return postDTO;
    }
}
