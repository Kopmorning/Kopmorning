package com.kop.morning.domain.article.comment.controller;

import com.kop.morning.domain.article.comment.dto.requestDto.CommentRequestDto;
import com.kop.morning.domain.article.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CommentRequestDto requestDto) {
        commentService.create(requestDto);
        return ResponseEntity.ok("Comment created");
    }
}
