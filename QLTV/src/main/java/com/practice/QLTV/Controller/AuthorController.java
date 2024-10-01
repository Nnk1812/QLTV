package com.practice.QLTV.Controller;

import com.practice.QLTV.DTO.Request.AuthorRequest;
import com.practice.QLTV.DTO.Response.APIResponse;
import com.practice.QLTV.Entity.Author;
import com.practice.QLTV.Service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private  AuthorService authorService;

    @PostMapping
    APIResponse<Author> createAuthor(@RequestBody AuthorRequest request) {
        APIResponse<Author> apiResponse = new APIResponse<>();
        apiResponse.setData(authorService.createAuthor(request));
        return apiResponse;
    }

    @GetMapping
    List<Author> getall(){
        return authorService.getall();
    }
}
