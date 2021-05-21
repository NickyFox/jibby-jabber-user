package com.user.controller;

import com.user.model.dto.UserDto;
import com.user.model.tables.User;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserModel(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) return user.get();
        return null;
    }

    @GetMapping("/available/{username}")
    public Boolean isUsernameAvailable(@PathVariable String username) {
        return !this.userService.usernameExists(username);
    }

    @PostMapping("/register")
    public UserDto createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PutMapping("/{id}/password")
    public UserDto changePassword(@RequestBody User user) {
        return userService.modifyPassword(user);
    }

    @PutMapping("/{id}/update")
    public UserDto updateUser(@RequestBody User user) {
        return userService.modifyUser(user);
    }

    @PutMapping("/{id}/delete")
    public Long updateUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }


}
