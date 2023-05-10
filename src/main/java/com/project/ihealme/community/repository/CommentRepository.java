package com.project.ihealme.community.repository;

import com.project.ihealme.community.domain.Comment;
import com.project.ihealme.community.domain.Post;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);

    @Override
    Optional<Comment> findById(Long commno);

    @Modifying
    @Query("delete from Comment c where c.post.postNo =:postNo ")
    void deleteByPostNo(@Param("postNo") Long postNo);

    Integer countByPost(Post post);
}