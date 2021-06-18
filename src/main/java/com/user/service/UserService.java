package com.user.service;

import com.user.model.dto.FollowerDto;
import com.user.model.dto.UserReduced;
import com.user.model.dto.UserReducedList;
import com.user.model.mapper.UserMapper;
import com.user.model.tables.User;
import com.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        User userToReturn = userRepository.save(user);
        return userMapper.userToUserDto(userToReturn);
    }

    public UserReduced modifyUser(long id, String username) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User userUpdated = user.get();
            userUpdated.setUsername(username);
            User userToReturn = userRepository.save(userUpdated);
            return userMapper.userToUserDto(userToReturn);
        } throw new Exception("User not present");

    }

    public UserReduced modifyPassword(User userUpdate) throws Exception {
        Optional<User> user = userRepository.findById(userUpdate.getId());
        if (user.isPresent()) {
            User userUpdated = user.get();
            userUpdated.setPassword(userUpdate.getPassword());
            User userToReturn = userRepository.save(userUpdated);
            return userMapper.userToUserDto(userToReturn);
        } throw new Exception("User not present");
    }

    public Long deleteUser(long userId) {
        userRepository.deleteById(userId);
        return userId;
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public UserReducedList searchUser(String username) {
        List<User> users = userRepository.findAllByUsernameContainingOrEmailContaining(username, username);
        List<UserReduced> userReduced = users.stream().map(userMapper::userToUserDto).collect(Collectors.toList());
        return new UserReducedList(userReduced);
    }

//    public long addFollowerFollowingRelation(FollowerFollowing followerFollowing) {
//        followingFollowerRepository.save(followerFollowing);
//        return followerFollowing.getFollower().getId();
//    }
//
//    public long removeFollowerFollowingRelation(FollowerDto followerFollowing) {
//        followingFollowerRepository.deleteByFollower_IdAndFollowing_Id(followerFollowing.getFollower(), followerFollowing.getFollowing());
//        return followerFollowing.getFollower();
//    }
//
//    public List<UserReduced> getFollowers(long userId) {
//       List<FollowerFollowing> followerFollowingList =  followingFollowerRepository.findAllByFollower_Id(userId);
//       List<UserReduced> followers = followerFollowingList.stream().map(f -> userMapper.userToUserDto(f.getFollower())).collect(Collectors.toList());
//       return followers;
//    }
//
//    public List<UserReduced> getFollowing(long userId) {
//        List<FollowerFollowing> followerFollowingList =  followingFollowerRepository.findAllByFollowing_Id(userId);
//        List<UserReduced> followers = followerFollowingList.stream().map(f -> userMapper.userToUserDto(f.getFollowing())).collect(Collectors.toList());
//        return followers;
//    }

}
