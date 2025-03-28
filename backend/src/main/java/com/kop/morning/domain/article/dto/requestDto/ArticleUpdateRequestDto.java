package com.kop.morning.domain.article.dto.requestDto;

import lombok.Data;

@Data
public class ArticleUpdateRequestDto {
    private String title;
    private String content;
}
