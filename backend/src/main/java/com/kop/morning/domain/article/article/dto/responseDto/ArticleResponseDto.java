package com.kop.morning.domain.article.article.dto.responseDto;

import com.kop.morning.domain.article.article.entity.Article;
import com.kop.morning.domain.article.comment.dto.responseDto.CommentResponseDto;
import com.kop.morning.domain.member.dto.responseDto.MemberResponseDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ArticleResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private MemberResponseDto member;
    private List<CommentResponseDto> comments;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.member = new MemberResponseDto(article.getMember());
        this.comments = article.getComments().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
