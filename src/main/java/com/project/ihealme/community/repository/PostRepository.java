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


    @Query("select p, u from Post p left join p.user u where p.postNo =:postNo")
    Object findPostWithUser(@Param("postNo") Long postNo);

    /**
     * 임시 화면에 필요한 데이터: Post, UserEntity
     */
    @Query(value = "select p, u from Post p left join p.user u",
            countQuery = "select count(p) from Post p")
    Page<Object[]> findPostWithUser(Pageable pageable);

    @Query("select p, u from Post p left join p.user u where p.postNo =:postNo")
    Object findPostByPostNo(@Param("postNo") Long postNo);

    /**
     * 검색
     */
    @Query(value = "select p, u from Post p left join p.user u where p.hptName like %:hptName%",
            countQuery = "select count(p) from Post p where p.hptName like %:hptName%")
    Page<Object[]> findByHptNameContaining(@Param("hptName") String keyword, Pageable pageable);

    @Query(value = "select p, u from Post p left join p.user u where p.title like %:title%",
            countQuery = "select count(p) from Post p where p.title like %:title%")
    Page<Object[]> findByTitleContaining(@Param("title") String keyword, Pageable pageable);

    @Query("select p, u from Post p left join p.user u where u.userEmail like %:userEmail%")
    Page<Object[]> findByUserEmailContaining(@Param("userEmail") String keyword, Pageable pageable);


    /**
     * 나중에 사용할 목록 화면에 필요한 데이터: Post, UserEntity, Comment
     */
    /*@Query(value = "select p, u, count(c) from Post p " +
            "left join p.user u " +
            "left join Comment c on c.post = p " +
            "group by p",
            countQuery = "select count(p) from Post p")
    Page<Object[]> findPostWithCommentCount(Pageable pageable);*/

    /*@Query("select p, u, count(c) from Post p left join p.user u " +
            "left join Comment c on c.post = p " +
            "where p.postNo =:postNo")
    Object findPostByPostNo(@Param("postNo") Long postNo);*/
}
