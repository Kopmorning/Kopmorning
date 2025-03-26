package com.kop.morning.domain.article.controller;

import com.kop.morning.domain.article.dto.ArticleRequestDto;
import com.kop.morning.domain.article.dto.ArticleResponseDto;
import com.kop.morning.domain.article.entity.Article;
import com.kop.morning.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<ArticleResponseDto>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponseDto> getArticle(@PathVariable Long articleId) {
        return ResponseEntity.ok(articleService.getArticleById(articleId));
    }

    // Message 관리 클래스 필요
    @PostMapping("/create")
    public ResponseEntity<String> createArticle(@RequestBody ArticleRequestDto articleRequestDto) {
        articleService.save(articleRequestDto);
        return ResponseEntity.ok("Article created");
    }

    // Message 관리 클래스 필요
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteArticle(@RequestParam Long articleId) {
        articleService.delete(articleId);
        return ResponseEntity.ok("Article deleted");
    }
}
