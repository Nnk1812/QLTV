package com.practice.QLTV.Mapper;

import com.practice.QLTV.DTO.Request.*;
import com.practice.QLTV.DTO.Response.BookResponse;
import com.practice.QLTV.DTO.Response.BorrowBookResponse;
import com.practice.QLTV.DTO.Response.GenreResponse;
import com.practice.QLTV.DTO.Response.UserResponse;
import com.practice.QLTV.Entity.*;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    User toUser(UserRequest userRequest);

    void updateUser(@MappingTarget User user , UserRequest request);

    Role toRole(RoleRequest roleRequest);
    List<User> toUserRes(List<UserResponse> response);

    Genre toGenre(GenreRequest request);
    void updateGerne(@MappingTarget Genre genre , GenreRequest request);

    GenreResponse toGenreResponse(Genre genre);

    Book toBook(BookRequest bookRequest);

    void updateBook(@MappingTarget Book book , BookRequest request);

    BookResponse toBookResponse(Book book);
    List<BookResponse> toBookRes(List<Book> books);

    BorrowBook toBorrowBook(BorrowBookRequest borrowBookRequest);
    BorrowBookResponse toBorrowBookResponse(BorrowBook book);
    void updateBorrowBook(@MappingTarget BorrowBook book , BorrowBookRequest request);

    ReturnBook toReturnBook(ReturnBookRequest returnBookRequest);
    void updateReturnBook(@MappingTarget ReturnBook returnBook , ReturnBookRequest request);

    Author toAuthor(AuthorRequest authorRequest);

    Publisher toPublisher(PublisherRequest publisherRequest);
}
