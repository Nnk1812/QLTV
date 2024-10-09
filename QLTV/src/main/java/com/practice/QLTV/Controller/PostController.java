package com.practice.QLTV.Controller;

import com.practice.QLTV.DTO.Request.PostRequest;
import com.practice.QLTV.DTO.Response.APIResponse;
import com.practice.QLTV.Entity.Post;
import com.practice.QLTV.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;
    @PostMapping
    APIResponse<Post> createPost(@RequestBody @Valid PostRequest request) {
        APIResponse<Post> postAPIResponse = new APIResponse<>();
        postAPIResponse.setData(postService.createPost(request));
        return postAPIResponse;
    }

    @GetMapping
    List<Post> getPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    Post getPost(@PathVariable int id) {
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    Post updatePost(@PathVariable int id, @RequestBody PostRequest request) {
        return postService.updatePost(request, id);
    }

    @DeleteMapping("/{id}")
    String deletePost(@PathVariable int id) {
        return postService.deletePost(id);
    }
}
