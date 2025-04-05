package com.kop.morning.domain.article.article.dto.responseDto;

import com.kop.morning.domain.article.article.entity.Article;
import com.kop.morning.domain.member.dto.responseDto.MemberResponseDto;
import lombok.Data;

@Data
public class ArticleListResponseDto {
    private Long id;
    private String title;
    private String category;
    private int likeCount;
    private MemberResponseDto member;

    public ArticleListResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.category = article.getCategory().toString();
        this.likeCount = article.getArticleLikes().size();
        this.member = new MemberResponseDto(article.getMember());
    }
}
