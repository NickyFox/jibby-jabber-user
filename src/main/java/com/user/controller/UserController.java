package com.user.controller;

import com.user.model.dto.FollowerDto;
import com.user.model.dto.UserReduced;
import com.user.model.dto.UserReducedList;
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
//    private final FollowerMapper followerMapper;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserModel(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) return new ResponseEntity<>(user.get(), HttpStatus.OK);
        return new ResponseEntity("El usuario no esta registrado", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        if (user.isPresent()) return new ResponseEntity<>(user.get(), HttpStatus.OK);
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

    @PostMapping("/{id}/password")
    public ResponseEntity<UserReduced> modifyPassword(@RequestBody String password, @PathVariable long id) {
        try {
            User user = new User();
            user.setId(id);
            user.setPassword(password);
            return new ResponseEntity(userService.modifyPassword(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/update")
    public ResponseEntity updateUser(@RequestBody String username, @PathVariable long id) {
        try {
            return new ResponseEntity(userService.modifyUser(id, username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        if (userService.existsById(id)) return new ResponseEntity(userService.deleteUser(id), HttpStatus.OK);
        return new ResponseEntity("El usuario no esta registrado", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<UserReducedList> searchUsername(@PathVariable String username) {
        return new ResponseEntity<>(this.userService.searchUser(username), HttpStatus.OK);
    }
//    @PostMapping("/follow")
//    public ResponseEntity followUser(@RequestBody FollowerDto followerDto) {
//        FollowerFollowing followerFollowing = followerMapper.followerDtoToFollower(followerDto);
//        userService.addFollowerFollowingRelation(followerFollowing);
//        return ResponseEntity.ok("Follow succesfully");
//    }

}
