package com.practice.QLTV.Repository;

import com.practice.QLTV.Entity.Comment;
import com.practice.QLTV.Entity.Post;
import com.practice.QLTV.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPost(Post post);
    List<Comment> findByUser(User user);
}

