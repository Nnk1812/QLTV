package com.practice.QLTV.Mapper;

import com.practice.QLTV.DTO.Request.BookRequest;
import com.practice.QLTV.DTO.Request.GenreRequest;
import com.practice.QLTV.DTO.Request.RoleRequest;
import com.practice.QLTV.DTO.Request.UserRequest;
import com.practice.QLTV.DTO.Response.BookResponse;
import com.practice.QLTV.DTO.Response.GenreResponse;
import com.practice.QLTV.DTO.Response.UserResponse;
import com.practice.QLTV.Entity.Book;
import com.practice.QLTV.Entity.Genre;
import com.practice.QLTV.Entity.Role;
import com.practice.QLTV.Entity.User;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

//    @Mapping(target = "role", source = "name")
    User toUser(UserRequest userRequest);

    // Phương thức ánh xạ tùy chỉnh từ String sang Role
    default Role map(String roleName) {
        Role role = new Role();
        role.setRoleName(roleName);
        return role;
    }

    public default UserResponse toUserResponse(User user) {
        if (user == null) {
            return null; // Kiểm tra null để tránh NullPointerException
        }

        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .passWord(user.getPassWord())
                .name(user.getName())
                .SDT(user.getSDT())
                .dob(user.getDob())
                .address(user.getAddress())
                .CCCD(user.getCCCD())
                .doc(user.getDoc())
                .roleName(user.getUserRole() != null ? user.getUserRole().getRoleName() : null) // Lấy tên vai trò từ Role
                .build();
    }


    void updateUser(@MappingTarget User user , UserRequest request);

    Role toRole(RoleRequest roleRequest);
    List<User> toUserRes(List<UserResponse> response);

    Genre toGenre(GenreRequest request);
    void updateGerne(@MappingTarget Genre genre , GenreRequest request);

    GenreResponse toGenreResponse(Genre genre);

    Book toBook(BookRequest bookRequest);
    void updateBook(@MappingTarget Book book , BookRequest request);

    BookResponse toBookResponse(Book book);
}
