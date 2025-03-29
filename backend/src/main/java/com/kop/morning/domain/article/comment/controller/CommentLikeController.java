package com.kop.morning.domain.article.comment.controller;

import com.kop.morning.domain.article.comment.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment/like")
@RequiredArgsConstructor
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    @PostMapping("/{commentId}")
    public ResponseEntity<String> likeHandler(@PathVariable(name = "commentId") Long commentId) {
        return ResponseEntity.ok(commentLikeService.likeHandler(commentId));
    }
}
