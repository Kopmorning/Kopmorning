package com.kop.morning.domain.article.comment.dto.requestDto;

import lombok.Data;

@Data
public class CommentUpdateRequestDto {
    private Long commentId;
    private String comment;
}
