package com.hieu.identity_service.mapper;

import com.hieu.identity_service.dto.request.UserCreationRequest;
import com.hieu.identity_service.dto.request.UserUpdateRequest;
import com.hieu.identity_service.dto.response.UserResponse;
import com.hieu.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    @Mapping(target = "firstname",ignore = false)
    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);

}
