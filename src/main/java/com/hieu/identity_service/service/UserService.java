package com.hieu.identity_service.service;

import com.hieu.identity_service.dto.request.UserCreationRequest;
import com.hieu.identity_service.dto.request.UserUpdateRequest;
import com.hieu.identity_service.dto.response.UserResponse;
import com.hieu.identity_service.entity.User;
import com.hieu.identity_service.exception.AppException;
import com.hieu.identity_service.exception.ErrorCode;
import com.hieu.identity_service.mapper.UserMapper;
import com.hieu.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder; // use config password encoder from configuration

    public User createUser(UserCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        // map data
        User user = userMapper.toUser(request); // user không được tạo các trường thông tin tự chọn -> làm theo kiến trúc sau để user tạo yêu cầu lên mà không được phép chọn các trường không có phép

        user.setPassword(passwordEncoder.encode(request.getPassword())); // hashing password -> bcrypt

        return userRepository.save(user);
    }

    public List<UserResponse> getUsers(){
        return userMapper.toUserResponseList(userRepository.findAll());
    }

    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse updateUser(String userID, UserUpdateRequest request){
        User user = userRepository.findById(userID).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)); // take a user

        userMapper.updateUser(user, request); // use a method -> map request to user
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
