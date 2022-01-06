package com.example.demo.dto;

import com.example.demo.entity.Post;
import lombok.Getter;

@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private String author;

    public PostListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
    }
}
