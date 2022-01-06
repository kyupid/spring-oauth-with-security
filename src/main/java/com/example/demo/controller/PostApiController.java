package com.example.demo.controller;

import com.example.demo.dto.PostListResponseDto;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.dto.PostSaveRequestDto;
import com.example.demo.dto.PostUpdateRequestDto;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @GetMapping("/api/v1/posts")
    public List<PostListResponseDto> findAll() {
        return postService.findAllDesc();
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody
            PostUpdateRequestDto requestDto) {

        return postService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDto findById (@PathVariable Long id) {
        return postService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }
}
