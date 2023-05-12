package com.project.ihealme.community.repository;

import com.project.ihealme.community.domain.Comment;
import com.project.ihealme.community.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.post =:post order by c.commNo desc")
    List<Comment> findByPost(@Param("post") Post post);

    @Override
    Optional<Comment> findById(Long commno);

    @Modifying
    @Query("delete from Comment c where c.post.postNo =:postNo ")
    void deleteByPostNo(@Param("postNo") Long postNo);

    @Query(value = "SELECT c FROM Comment c " +
            "WHERE c.post = :post " +
            "AND ( " +
            "SELECT COUNT(c2) FROM Comment c2 " +
            "WHERE c2.post = :post " +
            "AND c2.commNo <= c.commNo " +
            ") > (:pageNum - 1) * :amount " +
            "AND ( " +
            "SELECT COUNT(c2) FROM Comment c2 " +
            "WHERE c2.post = :post " +
            "AND c2.commNo <= c.commNo " +
            ") <= :pageNum * :amount " +
            "ORDER BY c.commNo")
    List<Comment> getListWithPaging(@Param("amount")int amount, @Param("pageNum") int pageNum, @Param("post")Post post);

    @Query(value = "select count(*) from comments c where c.postNo =:postNo", nativeQuery = true)
    Integer countByPostNo(@Param("postNo")Long postNo);
}