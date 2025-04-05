package com.kop.morning.domain.article.article.service;

import com.kop.morning.domain.article.article.entity.Article;
import com.kop.morning.domain.article.article.entity.ArticleLike;
import com.kop.morning.domain.article.article.repository.ArticleLikeRepository;
import com.kop.morning.domain.article.article.repository.ArticleRepository;
import com.kop.morning.domain.member.entity.Member;
import com.kop.morning.domain.member.repository.MemberRepository;
import com.kop.morning.global.Utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleLikeService {
    private final ArticleLikeRepository articleLikeRepository;
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    public String likeHandler(Long articleId) {
        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentMemberEmail());
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("article not found"));
        ArticleLike articleLike = articleLikeRepository.findByMemberAndArticle(member, article);

        if (articleLike == null) {
            articleLikeRepository.save(new ArticleLike(article, member));
            return "article like success";
        } else {
            articleLikeRepository.delete(articleLike);
            return "article like delete";
        }
    }
}
