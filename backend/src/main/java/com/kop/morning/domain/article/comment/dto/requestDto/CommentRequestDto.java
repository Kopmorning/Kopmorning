package com.kop.morning.domain.article.comment.dto.requestDto;

import lombok.Data;

@Data
public class CommentRequestDto {
    private Long articleId;
    private String comment;
}
