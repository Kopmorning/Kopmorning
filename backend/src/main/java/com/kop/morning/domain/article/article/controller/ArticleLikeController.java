package com.kop.morning.domain.article.article.controller;

import com.kop.morning.domain.article.article.service.ArticleLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/article/like")
@RequiredArgsConstructor
public class ArticleLikeController {
    private final ArticleLikeService articleLikeService;

    @PostMapping("/{articleId}")
    public ResponseEntity<String> likeHandler(@PathVariable(name = "articleId") Long articleId) {
        return ResponseEntity.ok(articleLikeService.likeHandler(articleId));
    }
}
