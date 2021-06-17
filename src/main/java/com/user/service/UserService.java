package com.user.service;

import com.user.model.dto.UserReduced;
import com.user.model.mapper.UserMapper;
import com.user.model.tables.User;
import com.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public UserReduced registerUser(User user) {
//        user.setPassword(passwordEncoderConfig.encoder().encode(user.getPassword()));
        User userToReturn = userRepository.save(user);
        return userMapper.userToUserDto(userToReturn);
    }

    public UserReduced modifyUser(User user) {
        User userToReturn = userRepository.save(user);
        return userMapper.userToUserDto(userToReturn);
    }

    public UserReduced modifyPassword(User user) {
        User userToReturn = userRepository.save(user);
        return userMapper.userToUserDto(userToReturn);
    }

    public Long deleteUser(long userId) {
        userRepository.deleteById(userId);
        return userId;
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
