package com.practice.QLTV.Repository;

import com.practice.QLTV.Entity.Author;
import com.practice.QLTV.Repository.projection.AuthorOverView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByEmail(String email);
    Optional<Author> findByAuthorID(int authorID);
    AuthorOverView findAuthorOverviewByAuthorID(int authorID);
}

