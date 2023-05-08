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

    @Query("select distinct p, u from Post p " +
            "left join p.user u " +
            "where p.postNo =:postNo")
    List<Object[]> findPostAndUserByPostNo(@Param("postNo") Long postNo);

    @Query("select distinct p, u from Post p " +
            "left join p.user u ")
    Page<Object[]> findPostAndUserByPage(Pageable pageable);

    /**
     * 검색
     */
    @Query("select p, u from Post p left join p.user u where p.hptName like %:hptName%")
    Page<Object[]> findByHptNameContaining(@Param("hptName") String hptName, Pageable pageable);

    @Query("select p, u from Post p left join p.user u where p.title like %:title%")
    Page<Object[]> findByTitleContaining(@Param("title") String title, Pageable pageable);

    @Query("select p, u from Post p left join p.user u where u.userEmail like %:userEmail%")
    Page<Object[]> findByUserEmailContaining(@Param("userEmail") String userEmail, Pageable pageable);

}
