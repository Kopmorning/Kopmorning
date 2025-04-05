package com.kop.morning.domain.article.article.service;

import com.kop.morning.domain.article.article.dto.responseDto.ArticleListResponseDto;
import com.kop.morning.domain.article.article.dto.responseDto.ArticleResponseDto;
import com.kop.morning.domain.article.article.dto.requestDto.ArticleUpdateRequestDto;
import com.kop.morning.domain.article.article.dto.requestDto.ArticleRequestDto;
import com.kop.morning.domain.article.article.entity.Article;
import com.kop.morning.domain.article.article.repository.ArticleRepository;
import com.kop.morning.domain.member.entity.Member;
import com.kop.morning.domain.member.repository.MemberRepository;
import com.kop.morning.global.Utils.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    // 다건 조회
    public List<ArticleListResponseDto> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(ArticleListResponseDto::new)
                .toList();
    }

    public ArticleResponseDto getArticleById(Long id) {
        // 예외처리 리펙토링
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        return new ArticleResponseDto(article);
    }

    public void save(ArticleRequestDto articleRequestDto) {
        // 예외처리 리펙토링
        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentMemberEmail());
        Article article = new Article(articleRequestDto, member);
        articleRepository.save(article);
    }

    public void delete(Long articleId) {
        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentMemberEmail());
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        if(!Objects.equals(member.getId(), article.getMember().getId())) {
            throw new RuntimeException("게시물에 권한이 없습니다.");
        }

        articleRepository.deleteById(articleId);
    }

    @Transactional
    public void update(ArticleUpdateRequestDto requestDto, Long articleId) {
        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentMemberEmail());
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        if(!Objects.equals(member.getId(), article.getMember().getId())) {
            throw new RuntimeException("게시물에 권한이 없습니다.");
        }

        article.update(requestDto);
    }
}
