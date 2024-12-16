package com.love_dating_site.love_dating_site.service.impl;

import com.love_dating_site.love_dating_site.entity.UserEntity;
import com.love_dating_site.love_dating_site.repository.UserRepository;
import com.love_dating_site.love_dating_site.dto.UserOperationsRequest;
import com.love_dating_site.love_dating_site.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserEntity createUser(UserOperationsRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        UserEntity user = new UserEntity();
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setBalance(request.getBalance());
        return userRepository.save(user);
    }

    @Override
    public UserEntity authenticateUser(String username, String password) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Password doesn't match");
        }

        return user;
    }

    @Override
    public UserEntity adminLogin(String username, String password) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Password doesn't match");
        }

        if (!user.getUsername().equals("super.admin@gmail.com")) {
            throw new RuntimeException("User doesn't have admin privileges");
        }

        return user;
    }

    @Override
    @Transactional
    public UserEntity updateUser(Long userId, UserOperationsRequest request) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getBalance() != null) {
            user.setBalance(request.getBalance());
        }
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getPassword() != null) {
            user.setPassword(request.getPassword());
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userRepository.delete(user);
    }

    @Override
    public UserEntity getUserById(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> result = new ArrayList<>();
        userRepository.findAll().forEach(result::add);
        return result;
    }
}
