package com.project.ihealme.community.service;

import com.project.ihealme.community.domain.Post;
import com.project.ihealme.community.dto.EditPostRequestDto;
import com.project.ihealme.community.dto.InsertPostRequestDto;
import com.project.ihealme.community.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAllByOrderByPostNoDesc();
    }

    public Post getPost(Long postNo) {
        Post post = postRepository.findById(postNo).get();
        return addHitCount(post);
    }

    private Post addHitCount(Post post) {
        post.addHitCount();
        return postRepository.save(post);
    }

    public Post insertPost(InsertPostRequestDto insertPostRequestDto) {
        Post post = new Post(insertPostRequestDto);
        return postRepository.save(post);
    }

    public Post updatePost(Long postNo, EditPostRequestDto editPostRequestDto) {
        Optional<Post> post = postRepository.findById(postNo);

        post.get().editPost(editPostRequestDto);
        return postRepository.save(post.get());
    }
}
