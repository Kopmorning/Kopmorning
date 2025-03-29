package com.kop.morning.domain.article.article.controller;

import com.kop.morning.domain.article.article.dto.requestDto.ArticleUpdateRequestDto;
import com.kop.morning.domain.article.article.dto.responseDto.ArticleRequestDto;
import com.kop.morning.domain.article.article.dto.requestDto.ArticleResponseDto;
import com.kop.morning.domain.article.article.controller.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ArticleResponseDto> getArticle(@PathVariable(name = "articleId") Long articleId) {
        return ResponseEntity.ok(articleService.getArticleById(articleId));
    }

    // Message 관리 클래스 필요
    @PostMapping
    public ResponseEntity<String> createArticle(@RequestBody ArticleRequestDto requestDto) {
        articleService.save(requestDto);
        return ResponseEntity.ok("Article created");
    }

    // Message 관리 클래스 필요
    @DeleteMapping
    public ResponseEntity<String> deleteArticle(@RequestParam(name = "articleId") Long articleId) {
        articleService.delete(articleId);
        return ResponseEntity.ok("Article deleted");
    }

    // Message 관리 클래스 필요
    @PatchMapping
    public ResponseEntity<String> updateArticle(
            @RequestBody ArticleUpdateRequestDto requestDto,
            @RequestParam(name = "articleId") Long articleId) {
        articleService.update(requestDto, articleId);
        return ResponseEntity.ok("Article updated");
    }
}
