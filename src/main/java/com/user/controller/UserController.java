package com.user.controller;

import com.user.model.dto.UserReduced;
import com.user.model.tables.User;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> getUserModel(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent())   return new ResponseEntity<>(user.get(), HttpStatus.OK);
        return new ResponseEntity("El usuario no esta registrado", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        System.out.println(email);
        Optional<User> user = userService.getUserByEmail(email);
        if (user.isPresent())   return new ResponseEntity<>(user.get(), HttpStatus.OK);
        return new ResponseEntity("El usuario no esta registrado", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/available/{username}")
    public ResponseEntity<Boolean> isUsernameAvailable(@PathVariable String username) {
        return new ResponseEntity<>(!this.userService.usernameExists(username), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Long> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.registerUser(user).getId(), HttpStatus.OK);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<UserReduced> modifyPassword(@RequestBody User user) {
        if (userService.existsById(user.getId()))
            return new ResponseEntity(userService.modifyPassword(user), HttpStatus.OK);
        return new ResponseEntity("El usuario no se encuentra registrado", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity updateUser(@RequestBody User user) {
        if (userService.existsById(user.getId())) return new ResponseEntity(userService.modifyUser(user), HttpStatus.OK);
        return new ResponseEntity("El usuario no esta registrado", HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/{id}/delete")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        if (userService.existsById(id)) return new ResponseEntity(userService.deleteUser(id), HttpStatus.OK);
        return new ResponseEntity("El usuario no esta registrado", HttpStatus.BAD_REQUEST);
    }


}
