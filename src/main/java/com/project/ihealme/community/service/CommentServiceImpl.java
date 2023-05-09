package com.project.ihealme.community.service;

import com.project.ihealme.community.domain.Comment;
import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.dto.CommentDto;
import com.project.ihealme.community.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public Long save(CommentDto commentDto) {
        Comment comment = toEntitiy(commentDto);
        commentRepository.save(comment);

        return comment.getCommNo();
    }

    @Override
    public List<CommentDto> getList(Long postNo) {
        List<Comment> comments = commentRepository
                .findByPost(Post.builder().postNo(postNo).build());

        return comments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void update(CommentDto commentDto) {
        Comment comment = toEntitiy(commentDto);

        commentRepository.save(comment);
    }

    @Override
    public void delete(Long commNo) {
        commentRepository.deleteById(commNo);
    }


  /*  private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<CommentResponseDto> findAllComments(Long postNo){
        Post post = postRepository.findById(postNo)
                .orElseThrow(PostNotFoundException::new);
        List<Comment> comments = commentRepository.findByPost(post);

        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveComment(CommentRequestDto dto, Long postNo, User user){
        Post post = postRepository.findById(postNo)
                .orElseThrow(PostNotFoundException::new);

        Comment comment = new Comment(dto.getContent(), user, post);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long commNo, User user, CommentRequestDto dto){
        Comment comment = commentRepository.findById(commNo)
                .orElseThrow(CommentNotFoundException::new);

        validateComment(comment, user);
        comment.setContent(dto.getContent());
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
*/
}
