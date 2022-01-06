package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.dto.PostListResponseDto;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.dto.PostSaveRequestDto;
import com.example.demo.dto.PostUpdateRequestDto;
import com.example.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post posts = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostResponseDto findById (Long id) {
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAll().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Post posts = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다 id=" + id));

        postRepository.delete(posts);
    }
}
