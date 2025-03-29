package com.kop.morning.domain.article.article.dto.requestDto;

import lombok.Data;

@Data
public class ArticleRequestDto {
    private String title;
    private String content;
    private String category;
}
