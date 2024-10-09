package com.practice.QLTV.Service;

import com.practice.QLTV.DTO.Request.CommentRequest;
import com.practice.QLTV.Entity.Comment;
import com.practice.QLTV.Entity.Post;
import com.practice.QLTV.Entity.User;
import com.practice.QLTV.Repository.CommentRepository;
import com.practice.QLTV.Repository.PostRepository;
import com.practice.QLTV.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentsService {
    CommentRepository commentRepository;
    PostRepository postRepository;
    UserRepository userRepository;
    Comment toComment(CommentRequest request)
    {
        Comment cmt = new Comment();
        Post post = postRepository.findById(request.getIdPost()).orElseThrow(
                ()->new RuntimeException("Post not found"));
        cmt.setPost(post);
        User user = userRepository.findById(request.getIdUser()).orElseThrow(
                ()->new RuntimeException("User not found")
        );
        cmt.setUser(user);
        cmt.setText(request.getText());
        cmt.setDocmt(request.getDoCmt());
        return cmt;
    }
    void update(Comment cmt,CommentRequest request)
    {
        Post post = postRepository.findById(request.getIdPost()).orElseThrow(
                ()->new RuntimeException("Post not found"));
        cmt.setPost(post);
        User user = userRepository.findById(request.getIdUser()).orElseThrow(
                ()->new RuntimeException("User not found")
        );
        cmt.setUser(user);
        cmt.setText(request.getText());
        cmt.setDocmt(request.getDoCmt());
    }
    public Comment create(CommentRequest request){
        Comment cmt = toComment(request);
        return commentRepository.save(cmt);
    }
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }
    public Comment getComment(int id){
        return commentRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Comment not found")
        );
    }
    public Comment updateComment(int id, CommentRequest request){
        Comment cmt = commentRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Comment not found")
        );
        update(cmt,request);
        return commentRepository.save(cmt);
    }
    public String deleteComment(int id){
        Comment cmt = commentRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Comment not found")
        );
        commentRepository.delete(cmt);
        return "Comment deleted";
    }

}
