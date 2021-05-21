package com.user.service;

import com.user.model.dto.UserDto;
import com.user.model.mapper.UserMapper;
import com.user.model.tables.User;
import com.user.repository.UserRepository;
import com.user.security.PasswordEncoderConfig;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoderConfig passwordEncoderConfig, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoderConfig = passwordEncoderConfig;
        this.userMapper = userMapper;
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public UserDto registerUser(User user) {
        user.setPassword(passwordEncoderConfig.encoder().encode(user.getPassword()));
        User userToReturn = userRepository.save(user);
        return userMapper.userDtoToUser(userToReturn);
    }

    public UserDto login(String email, String password) {
        Optional<User> bdUser = userRepository.findByEmail(email);
        if (bdUser.isPresent()) {
            String encodedPassword = passwordEncoderConfig.encoder().encode(password);
            System.out.println(bdUser.get());
            if (bdUser.get().getPassword().equals(encodedPassword)) userMapper.userDtoToUser(bdUser.get());
        }
        return null;
    }

    public UserDto modifyUser(User user) {
        if (userRepository.existsById(user.getId())) {
            User userToReturn = userRepository.save(user);
            return userMapper.userDtoToUser(userToReturn);
        }
        //TODO como lo devuelvo sino?
        return null;
    }

    public UserDto modifyPassword(User user) {
        if (userRepository.existsById(user.getId())) {
            user.setPassword(passwordEncoderConfig.encoder().encode(user.getPassword()));
            User userToReturn = userRepository.save(user);
            return userMapper.userDtoToUser(userToReturn);
        }
        //TODO
        return null;
    }

    public Long deleteUser(long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return userId;
        }
        // TODO arreglarlo
        return null;

    }
}
