package com.project.ihealme.community.repository;

import com.project.ihealme.community.domain.User;
import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.dto.PostResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void insertPosts() {
        IntStream.rangeClosed(1, 101).forEach(i -> {
            User user = User.builder()
                    .userEmail("user" + i + "@naver.com")
                    .build();

            userRepository.save(user);
        });

        LongStream.rangeClosed(1L, 101L).forEach(i -> {
            User user = User.builder().userId(i).build();

            Post post = Post.builder()
                    .title("title" + i)
                    .resNo((int) i)
                    .hptName("새롬소아청소년과의원")
                    .user(user)
                    .content("content" + i)
                    .build();

            postRepository.save(post);
        });

        /**
         * INSERT INTO userreservation(useremail, hptname, txlist)
         * VALUES ('longlee@daum.net', '새롬소아청년과의원', '영유아 검진');
         *
         *
         * INSERT INTO userreservation(resno, useremail, hptname, txlist)
         * VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '새롬소아청년과의원', '영유아 검진');
         *
         * INSERT INTO userreservation(resno, useremail, hptname, txlist, currentStatus)
         * VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '새롬소아청년과의원', '영유아 검진', '진료 전');
         *
         * INSERT INTO userreservation(resno, useremail, hptname, txlist, currentStatus)
         * VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '새롬소아청년과의원', '영유아 검진', '진료 완료');
         *
         * INSERT INTO userreservation(resno, useremail, hptname, txlist, currentStatus)
         * VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '새롬소아청년과의원', '영유아 검진', '후기작성완료');
         */
    }

    @Test
    void insertPost() {
        User user = User.builder().userId(55L).build();

        Post post = Post.builder()
                .title("제목")
                .resNo((int) 103)
                .hptName("새롬소아청소년과의원")
                .user(user)
                .content("내용용")
                .build();

        Post savedPost = postRepository.save(post);
        System.out.println(savedPost);
    }

    /*@Test
    void findPostWithUser() {
        Long postNo = 100L;

        Object result = postRepository.findPostWithUser(postNo);
        Object[] arr = (Object[]) result;

        System.out.println("------------------");
        System.out.println(Arrays.toString(arr));
    }*/

    @Test
    void findPostWithDetails() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("postNo").descending());
        Page<Object[]> result = postRepository.findPostWithDetails(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    void findPostByPostNo() {
        Object result = postRepository.findPostByPostNo(100L);

        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void findByHptNameContaining() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("postNo").descending());
        Page<Object[]> result = postRepository.findByHptNameContaining("새롬", pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });

        System.out.println(result.getTotalElements());
        System.out.println(result.getTotalPages());
//        assertThat(result.getTotalElements()).isEqualTo(100);
//        assertThat(result.getTotalPages()).isEqualTo(10);
    }

    @Test
    void findByTitleContaining() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("postNo").descending());
        Page<Object[]> result = postRepository.findByTitleContaining("수정", pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });

        System.out.println(result.getTotalElements());
        System.out.println(result.getTotalPages());
    }

    @Test
    void findByUserEmailContaining() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("postNo").descending());
        Page<Object[]> result = postRepository.findByUserEmailContaining("55", pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });

        System.out.println(result.getTotalElements());
        System.out.println(result.getTotalPages());
    }

}