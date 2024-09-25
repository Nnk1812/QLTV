package com.practice.QLTV.Mapper;

import com.practice.QLTV.DTO.Request.UserRequest;
import com.practice.QLTV.DTO.Response.UserResponse;
import com.practice.QLTV.Entity.User;
import org.mapstruct.MappingTarget;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    User toUser(UserRequest request);
    void updateUser(@MappingTarget User user, UserRequest request);
    UserResponse toUserResponse(User user);
    List<User> toUserRes(List<UserResponse> response);
}
