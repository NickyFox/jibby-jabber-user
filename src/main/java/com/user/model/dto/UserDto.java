package com.user.model.dto;

public class UserDto {

    private final long id;
    private final String username;
    private final String email;


    public UserDto(long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }
}
