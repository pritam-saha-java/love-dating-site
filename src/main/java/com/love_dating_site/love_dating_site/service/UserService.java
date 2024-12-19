package com.love_dating_site.love_dating_site.service;

import com.love_dating_site.love_dating_site.entity.UserEntity;
import com.love_dating_site.love_dating_site.dto.UserOperationsRequest;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserEntity createUser(UserOperationsRequest request);

    UserEntity authenticateUser(String username, String password);

    Map<String, String> adminLogin(String username, String password);

    UserEntity updateUser(Long userId, UserOperationsRequest request);

    void deleteUser(String username);

    UserEntity getUserById(String username);

    List<UserEntity> getAllUsers();
}
