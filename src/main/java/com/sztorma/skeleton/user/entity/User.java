package com.sztorma.skeleton.user.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sztorma.skeleton.common.model.Identity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends Identity {

    @NonNull
    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @NonNull
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @NonNull
    @Column(nullable = false, length = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
