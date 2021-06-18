package com.user.service;

import com.user.model.dto.FollowerDto;
import com.user.model.dto.UserReduced;
import com.user.model.dto.UserReducedList;
import com.user.model.mapper.FollowerMapper;
import com.user.model.mapper.UserMapper;
import com.user.model.tables.Followers;
import com.user.model.tables.User;
import com.user.repository.FollowersRepository;
import com.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FollowerMapper followerMapper;
    private final FollowersRepository followersRepository;


    public UserService(UserRepository userRepository, UserMapper userMapper, FollowerMapper followerMapper, FollowersRepository followersRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.followerMapper = followerMapper;
        this.followersRepository = followersRepository;
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

    public Followers addFollowerFollowingRelation(FollowerDto followerDto) {
        Followers followers = followerMapper.followerDtoToFollower(followerDto);
        return followersRepository.save(followers);
    }

    public long unfollowUser(FollowerDto followerFollowing) {
        followersRepository.deleteByFromIdAndToId(followerFollowing.getTo(), followerFollowing.getFrom());
        return followerFollowing.getTo();
    }

    public UserReducedList getFollowers(long userId) {
       List<Followers> followerFollowingList =  followersRepository.findAllByToId(userId);
        if (followerFollowingList.isEmpty()) return new UserReducedList(new ArrayList<>());
        List<UserReduced> followers = followerFollowingList.stream().map(f -> userMapper.userToUserDto(f.getFrom())).collect(Collectors.toList());
        return new UserReducedList(followers);
    }

    public UserReducedList getFollowings(long userId) {
        List<Followers> followerFollowingList =  followersRepository.findAllByFromId(userId);
        if (followerFollowingList.isEmpty()) return new UserReducedList(new ArrayList<>());
        List<UserReduced> followings = followerFollowingList.stream().map(f -> userMapper.userToUserDto(f.getFrom())).collect(Collectors.toList());
        return new UserReducedList(followings);
    }
//
//    public List<UserReduced> getFollowing(long userId) {
//        List<FollowerFollowing> followerFollowingList =  followingFollowerRepository.findAllByFollowing_Id(userId);
//        List<UserReduced> followers = followerFollowingList.stream().map(f -> userMapper.userToUserDto(f.getFollowing())).collect(Collectors.toList());
//        return followers;
//    }

}
