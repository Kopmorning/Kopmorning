package com.kop.morning.domain.article.service;

import com.kop.morning.domain.article.dto.ArticleRequestDto;
import com.kop.morning.domain.article.dto.ArticleResponseDto;
import com.kop.morning.domain.article.entity.Article;
import com.kop.morning.domain.article.repository.ArticleRepository;
import com.kop.morning.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<ArticleResponseDto> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(ArticleResponseDto::new)
                .toList();
    }

    public ArticleResponseDto getArticleById(Long id) {
        // 예외처리 리펙토링
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        return new ArticleResponseDto(article);
    }

    public void save(ArticleRequestDto articleRequestDto) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Article article = new Article(articleRequestDto, member);
        articleRepository.save(article);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}
