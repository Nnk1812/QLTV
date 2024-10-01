package com.practice.QLTV.Service;

import com.practice.QLTV.DTO.Request.AuthorRequest;
import com.practice.QLTV.Entity.Author;
import com.practice.QLTV.Mapper.Mapper;
import com.practice.QLTV.Repository.AuthorRepository;
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
public class AuthorService {
    private AuthorRepository authorRepository;
    private Mapper mapper;
    public Author createAuthor(AuthorRequest request) {
        Author author = mapper.toAuthor(request);
        return authorRepository.save(author);
    }
    public List<Author> getall() {
        return authorRepository.findAll();
    }
}
