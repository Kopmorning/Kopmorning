package com.kop.morning.domain.article.comment.repository;

import com.kop.morning.domain.article.comment.entity.Comment;
import com.kop.morning.domain.article.comment.entity.CommentLike;
import com.kop.morning.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    CommentLike findByCommentAndMember(Comment comment, Member member);

    int findByCommentId(Long commentId);
}
