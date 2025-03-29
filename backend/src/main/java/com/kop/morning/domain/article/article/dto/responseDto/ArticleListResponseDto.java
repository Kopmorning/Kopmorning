package com.kop.morning.domain.article.article.dto.responseDto;

import com.kop.morning.domain.article.article.entity.Article;
import com.kop.morning.domain.member.dto.responseDto.MemberResponseDto;
import lombok.Data;

@Data
public class ArticleListResponseDto {
    private Long id;
    private String title;
    private MemberResponseDto member;

    public ArticleListResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.member = new MemberResponseDto(article.getMember());
    }
}
