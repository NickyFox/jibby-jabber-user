package com.user.model.dto;

public class FollowerDto {

    private long following;
    private long follower;

    public FollowerDto() {
    }

    public FollowerDto(long following, long follower) {
        this.following = following;
        this.follower = follower;
    }

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following = following;
    }

    public long getFollower() {
        return follower;
    }

    public void setFollower(long follower) {
        this.follower = follower;
    }
}
