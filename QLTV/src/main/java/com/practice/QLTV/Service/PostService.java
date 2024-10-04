package com.practice.QLTV.Service;

import com.practice.QLTV.DTO.Request.PostRequest;
import com.practice.QLTV.Entity.Post;
import com.practice.QLTV.Entity.User;
import com.practice.QLTV.Repository.PostRepository;
import com.practice.QLTV.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;
//    Post toPost(PostRequest request) {
//        Post post = new Post();
//        User user = userRepository.findById(request.getUserID()).orElseThrow(
//                () -> new RuntimeException("User Not Found")
//        );
//        post.setUser(user);
//        post.setTitle(request.getTitle());
//        post.setContent(request.getContent());
//        post.set
//    }
//
//    public Post createPost(PostRequest request) {
//
//    }
}
