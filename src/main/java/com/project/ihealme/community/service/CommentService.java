package com.project.ihealme.community.service;

import com.project.ihealme.community.domain.Comment;
import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.domain.User;
import com.project.ihealme.community.dto.CommentCreateRequest;
import com.project.ihealme.community.dto.CommentDto;
import com.project.ihealme.community.dto.CommentUpdateRequest;
import com.project.ihealme.community.exception.CommentNotFoundException;
import com.project.ihealme.community.exception.MemberNotEqualsException;
import com.project.ihealme.community.exception.PostNotFoundException;
import com.project.ihealme.community.repository.CommentRepository;
import com.project.ihealme.community.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<CommentDto> findAllComments(Long postNo){
        Post post = postRepository.findById(postNo)
                .orElseThrow(PostNotFoundException::new);
        List<Comment> comments = commentRepository.findByPost(post);

        return comments.stream()
                .map(CommentDto::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createComment(CommentCreateRequest req, User user){
        Post post = postRepository.findById(req.getPostNo())
                .orElseThrow(PostNotFoundException::new);

        Comment comment = new Comment(req.getContent(), user, post, now());
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long commNo, User user, CommentUpdateRequest req){

        Comment comment = commentRepository.findById(commNo)
                .orElseThrow(CommentNotFoundException::new);

        validateComment(comment, user);
        comment.setContent(req.getContent());
    }

    @Transactional
    public void deleteComment(Long commNo, User user) {
        Comment comment = commentRepository.findById(commNo)
                .orElseThrow(CommentNotFoundException::new);

        validateComment(comment, user);
        commentRepository.delete(comment);
    }

    private void validateComment(Comment comment, User user) {
        if (!comment.isOwnComment(user)) {
            throw new MemberNotEqualsException();
        }
    }

}
