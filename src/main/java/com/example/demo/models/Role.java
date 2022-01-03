package com.example.demo.models;

import com.fasterxml.jackson.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private List<User> usersList;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public List<User> getUser() {
//        return usersList;
//    }
//
    public void listUser(List<User> user) {
        this.usersList = user;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public String toString() {
        return name.replaceAll("ROLE_","");
    }
}
