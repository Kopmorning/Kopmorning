package com.kop.morning.domain.article.article.dto.requestDto;

import com.kop.morning.domain.article.article.entity.Article;
import lombok.Data;

@Data
public class ArticleResponseDto {
    private Long id;
    private String title;
    private String content;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
