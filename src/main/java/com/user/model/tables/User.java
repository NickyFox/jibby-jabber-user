package com.user.model.tables;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

//    @ManyToMany
//    @JoinTable(name="follower_following",
//            joinColumns=@JoinColumn(name="following"),
//            inverseJoinColumns=@JoinColumn(name="follower")
//    )
//    private List<User> following;
//
//    @ManyToMany
//    @JoinTable(name="follower_following",
//            joinColumns=@JoinColumn(name="follower"),
//            inverseJoinColumns=@JoinColumn(name="following")
//    )
//    private List<User> followers;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
