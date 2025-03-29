package com.kop.morning.domain.article.article.repository;

import com.kop.morning.domain.article.article.entity.Article;
import com.kop.morning.domain.article.article.entity.ArticleLike;
import com.kop.morning.domain.member.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface ArticleLikeRepository extends CrudRepository<ArticleLike, Long> {
    ArticleLike findByMemberAndArticle(Member member, Article article);
}
