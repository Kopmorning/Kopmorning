package com.kop.morning.domain.article.comment.repository;

import com.kop.morning.domain.article.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
