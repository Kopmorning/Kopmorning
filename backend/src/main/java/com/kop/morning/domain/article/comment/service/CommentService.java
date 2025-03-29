package com.kop.morning.domain.article.comment.service;

import com.kop.morning.domain.article.article.entity.Article;
import com.kop.morning.domain.article.article.repository.ArticleRepository;
import com.kop.morning.domain.article.comment.dto.requestDto.CommentRequestDto;
import com.kop.morning.domain.article.comment.entity.Comment;
import com.kop.morning.domain.article.comment.repository.CommentRepository;
import com.kop.morning.domain.member.entity.Member;
import com.kop.morning.domain.member.repository.MemberRepository;
import com.kop.morning.global.Utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    public void create(CommentRequestDto requestDto) {
        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentMemberEmail());
        Article article = articleRepository.findById(requestDto.getArticleId())
                .orElseThrow(() -> new RuntimeException("Article not found"));
        commentRepository.save(new Comment(requestDto, member, article));
    }
}
