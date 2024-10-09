package com.practice.QLTV.Controller;

import com.practice.QLTV.DTO.Request.CommentRequest;
import com.practice.QLTV.DTO.Response.APIResponse;
import com.practice.QLTV.Entity.Comment;
import com.practice.QLTV.Service.CommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cmt")
public class CommentsController {
    @Autowired
    CommentsService service;
    @PostMapping
    APIResponse<Comment> createComment(@RequestBody CommentRequest request) {
        APIResponse<Comment> apiResponse = new APIResponse<>();
        apiResponse.setData(service.create(request));
        return apiResponse;
    }
    @GetMapping
    List<Comment> getComments() {
        return service.getComments();
    }
    @GetMapping("/{id}")
    Comment getComment(@PathVariable int id) {
        return service.getComment(id);
    }
    @PutMapping("/{id}")
    Comment updateComment(@RequestBody CommentRequest request,@PathVariable int id) {
        return service.updateComment(id,request);
    }
    @DeleteMapping("/{id}")
    String deleteComment(@PathVariable int id) {
        return service.deleteComment(id);
    }
}
