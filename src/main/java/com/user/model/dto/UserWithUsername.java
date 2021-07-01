package com.user.model.dto;

public class UserWithUsername {
    long id;
    String username;

    public UserWithUsername(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserWithUsername() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
