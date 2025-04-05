package com.kop.morning.domain.article.comment.service;

import com.kop.morning.domain.article.article.entity.Article;
import com.kop.morning.domain.article.article.repository.ArticleRepository;
import com.kop.morning.domain.article.comment.dto.requestDto.CommentRequestDto;
import com.kop.morning.domain.article.comment.dto.requestDto.CommentUpdateRequestDto;
import com.kop.morning.domain.article.comment.entity.Comment;
import com.kop.morning.domain.article.comment.repository.CommentRepository;
import com.kop.morning.domain.member.entity.Member;
import com.kop.morning.domain.member.repository.MemberRepository;
import com.kop.morning.global.Utils.SecurityUtil;
import jakarta.transaction.Transactional;
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

    public void delete(Long commentId) {
        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentMemberEmail());
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        if (!comment.getMember().equals(member)) {
            throw new RuntimeException("댓글에 대한 권한이 없습니다.");
        }

        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void update(CommentUpdateRequestDto requestDto, Long commentId) {
        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentMemberEmail());
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        if (!comment.getMember().equals(member)) {
            throw new RuntimeException("댓글에 대한 권한이 없습니다.");
        }

        comment.update(requestDto.getComment());
    }
}
