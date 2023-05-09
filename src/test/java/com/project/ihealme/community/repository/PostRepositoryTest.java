package com.project.ihealme.community.repository;

import com.project.ihealme.community.domain.User;
import com.project.ihealme.community.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserTempRepository userTempRepository;

    @Test
    void insertPosts() {
        IntStream.rangeClosed(1, 101).forEach(i -> {
            User user = User.builder()
                    .userEmail("user" + i + "@naver.com")
                    .build();

            userTempRepository.save(user);
        });

        LongStream.rangeClosed(1L, 101L).forEach(i -> {
            User user = User.builder().userId(i).build();

            Post post = Post.builder()
                    .title("title" + i)
                    .user(user)
                    .content("content" + i)
                    .build();

            postRepository.save(post);
        });

    }

    @Test
    void insertPost() {
        User user = User.builder().userId(55L).build();

        Post post = Post.builder()
                .title("제목")
                .user(user)
                .content("내용용")
                .build();

        Post savedPost = postRepository.save(post);
        System.out.println(savedPost);
    }

    @Test
    void findByPostNo() {
        Post post = postRepository.findByPostNo(3L);

        System.out.println(post);
        System.out.println(post.getUser());
    }

    @Test
    @Transactional
    void findPostById() {
        Post post = postRepository.findById(101L).get();

        System.out.println(post);
        System.out.println(post.getUser());
    }

    @Test
    void findAllByPage() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("postNo").descending());
        Page<Post> result = postRepository.findAllByPage(pageable);

        result.get().forEach(row -> {
            System.out.println(row);
            System.out.println(row.getUser());
        });

        System.out.println(result.getTotalElements());
        System.out.println(result.getTotalPages());
    }

    @Test
    void findByHptNameContaining() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("postNo").descending());
        Page<Post> result = postRepository.findByHptNameContaining("새롬", pageable);

        result.get().forEach(row -> {
            System.out.println(row);
            System.out.println(row.getUser());
        });

        System.out.println("개수: " + result.getTotalElements());
        System.out.println("페이지 수: " + result.getTotalPages());
    }

    @Test
    void findByTitleContaining() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("postNo").descending());
        Page<Post> result = postRepository.findByTitleContaining("1", pageable);

        result.get().forEach(row -> {
            System.out.println(row);
            System.out.println(row.getUser());
        });

        System.out.println("개수: " + result.getTotalElements());
        System.out.println("페이지 수: " + result.getTotalPages());
    }

    @Test
    void findByUserEmailContaining() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("postNo").descending());
        Page<Post> result = postRepository.findByUserEmailContaining("long", pageable);

        result.get().forEach(row -> {
            System.out.println(row);
            System.out.println(row.getUser());
        });

        System.out.println("개수: " + result.getTotalElements());
        System.out.println("페이지 수: " + result.getTotalPages());
    }
}