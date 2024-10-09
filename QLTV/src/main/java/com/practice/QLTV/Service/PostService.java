package com.practice.QLTV.Service;

import com.practice.QLTV.DTO.Request.BookRequest;
import com.practice.QLTV.DTO.Request.PostRequest;
import com.practice.QLTV.Entity.Book;
import com.practice.QLTV.Entity.Post;
import com.practice.QLTV.Entity.User;
import com.practice.QLTV.Repository.BookRepository;
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
public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;
    BookRepository bookRepository;
     Post toPost(PostRequest request) {
        Post post = new Post();
        User user = userRepository.findById(request.getUserID()).orElseThrow(
                () -> new RuntimeException("User Not Found")
        );
        post.setUser(user);
        Book book = bookRepository.findByBookID(request.getBookID()).orElseThrow(
                ()-> new RuntimeException("Book Not Found")
        );
        post.setBook(book);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setDop(request.getDop());
        return post;
    }
    void updatePost(Post post,PostRequest request) {
        User user = userRepository.findById(request.getUserID()).orElseThrow(
                () -> new RuntimeException("User Not Found")
        );
        post.setUser(user);
        Book book = bookRepository.findByBookID(request.getBookID()).orElseThrow(
                ()-> new RuntimeException("Book Not Found")
        );
        post.setBook(book);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setDop(request.getDop());
    }

    public Post createPost(PostRequest request) {
        Post post = toPost(request);
        return postRepository.save(post);
    }

    public Post updatePost(PostRequest request,int id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Post Not Found")
        );
        updatePost(post,request);
        return postRepository.save(post);
    }
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    public Post getPostById(int id) {
        return postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Post Not Found")
        );
    }
    public String deletePost(int id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Post Not Found")
        );
        postRepository.delete(post);
        return "Post Deleted Successfully";
    }

}
