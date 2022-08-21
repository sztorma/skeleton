package com.sztorma.skeleton.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.sztorma.skeleton.common.model.Identity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends Identity {

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ERole name;

}
