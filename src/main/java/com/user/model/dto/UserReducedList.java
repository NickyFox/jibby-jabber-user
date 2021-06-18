package com.user.model.dto;

import java.util.List;

public class UserReducedList {
    List<UserReduced> users;

    public UserReducedList(List<UserReduced> users) {
        this.users = users;
    }

    public List<UserReduced> getUsers() {
        return users;
    }

    public void setUsers(List<UserReduced> users) {
        this.users = users;
    }
}
