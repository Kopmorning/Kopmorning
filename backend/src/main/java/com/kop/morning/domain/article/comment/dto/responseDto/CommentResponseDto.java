package com.kop.morning.domain.article.comment.dto.responseDto;

import com.kop.morning.domain.member.entity.Member;
import lombok.Data;

@Data
public class CommentResponseDto {
    private Long id;
    private Member member;
    private String comment;
}
