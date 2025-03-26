package com.kop.morning.domain.article.entity;

import com.kop.morning.domain.article.dto.ArticleRequestDto;
import com.kop.morning.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Article(ArticleRequestDto articleRequestDto, Member member) {
        this.title = articleRequestDto.getTitle();
        this.content = articleRequestDto.getContent();
        this.member = member;
    }
}
