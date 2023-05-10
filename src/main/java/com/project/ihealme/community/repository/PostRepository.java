package com.project.ihealme.community.repository;

import com.project.ihealme.community.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT DISTINCT p FROM Post p "
            + "LEFT JOIN FETCH p.userReservation "
            + "LEFT JOIN FETCH p.user "
            + "LEFT JOIN FETCH p.comments " +
            "where p.postNo =:postNo")
    Post findByPostNo(@Param("postNo") Long postNo);

    @Query(value = "SELECT DISTINCT p FROM Post p "
            + "LEFT JOIN FETCH p.userReservation "
            + "LEFT JOIN FETCH p.user "
            + "LEFT JOIN FETCH p.comments ",
            countQuery = "select count(p) from Post p")
    Page<Post> findAllByPage(Pageable pageable);

    /**
     * 검색
     */
    @Query(value = "SELECT DISTINCT p FROM Post p "
            + "LEFT JOIN FETCH p.userReservation r "
            + "LEFT JOIN FETCH p.user "
            + "LEFT JOIN FETCH p.comments "
            + "WHERE r.name like %:name%",
            countQuery = "select count(p) from Post p")
    Page<Post> findByHptNameContaining(@Param("name") String hptName, Pageable pageable);

    @Query(value = "SELECT DISTINCT p FROM Post p "
            + "LEFT JOIN FETCH p.userReservation "
            + "LEFT JOIN FETCH p.user "
            + "LEFT JOIN FETCH p.comments "
            + "WHERE p.title like %:title%",
            countQuery = "select count(p) from Post p")
    Page<Post> findByTitleContaining(@Param("title") String title, Pageable pageable);

    @Query(value = "SELECT DISTINCT p FROM Post p "
            + "LEFT JOIN FETCH p.userReservation "
            + "LEFT JOIN FETCH p.user u "
            + "LEFT JOIN FETCH p.comments "
            + "WHERE u.email like %:email%",
            countQuery = "select count(p) from Post p")
    Page<Post> findByUserEmailContaining(@Param("email") String userEmail, Pageable pageable);

}
