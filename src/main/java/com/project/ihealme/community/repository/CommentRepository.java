package com.project.ihealme.community.repository;

import com.project.ihealme.community.domain.Comment;
import com.project.ihealme.community.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);

    @Override
    Optional<Comment> findById(Long commno);
}
