package com.kop.morning.domain.article.service;

import com.kop.morning.domain.article.dto.requestDto.ArticleResponseDto;
import com.kop.morning.domain.article.dto.requestDto.ArticleUpdateRequestDto;
import com.kop.morning.domain.article.dto.responseDto.ArticleRequestDto;
import com.kop.morning.domain.article.entity.Article;
import com.kop.morning.domain.article.repository.ArticleRepository;
import com.kop.morning.domain.member.entity.Member;
import com.kop.morning.domain.member.repository.MemberRepository;
import com.kop.morning.global.Utils.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final SecurityUtil securityUtil;

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
        Long memberId = securityUtil.getCurrentMember();

        // 예외처리 리펙토링
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Article article = new Article(articleRequestDto, member);
        articleRepository.save(article);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    @Transactional
    public void update(ArticleUpdateRequestDto requestDto, Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        article.update(requestDto);
    }
}
