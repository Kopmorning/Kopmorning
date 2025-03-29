package com.kop.morning.domain.article.comment.service;

import com.kop.morning.domain.article.comment.entity.Comment;
import com.kop.morning.domain.article.comment.entity.CommentLike;
import com.kop.morning.domain.article.comment.repository.CommentLikeRepository;
import com.kop.morning.domain.article.comment.repository.CommentRepository;
import com.kop.morning.domain.member.entity.Member;
import com.kop.morning.domain.member.repository.MemberRepository;
import com.kop.morning.global.Utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public String likeHandler(Long commentId) {
        Member member = memberRepository.findByEmail(SecurityUtil.getCurrentMemberEmail());
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("comment not found"));

        CommentLike commentLike = commentLikeRepository.findByCommentAndMember(comment, member);
        if (commentLike != null) {
            commentLikeRepository.deleteById(commentLike.getId());
            return "comment like delete";
        } else {
            CommentLike newCommentLike = new CommentLike(comment, member);
            commentLikeRepository.save(newCommentLike);
            return "comment like create";
        }
    }

    public int getLikeCount(Long commentId) {
        return commentLikeRepository.findByCommentId(commentId);
    }
}
