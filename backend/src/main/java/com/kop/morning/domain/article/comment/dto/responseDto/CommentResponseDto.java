package com.kop.morning.domain.article.comment.dto.responseDto;

import com.kop.morning.domain.article.comment.entity.Comment;
import com.kop.morning.domain.member.dto.responseDto.MemberResponseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDto {
    private Long id;
    private String comment;
    private MemberResponseDto member;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.member = new MemberResponseDto(comment.getMember());
        this.createdAt = comment.getCreatedAt();
    }
}
