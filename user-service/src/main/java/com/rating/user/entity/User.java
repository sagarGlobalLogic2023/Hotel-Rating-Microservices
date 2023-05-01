package com.rating.user.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "first_name", length = 15)
    private String firstname;
    @Column(name = "last_name", length = 15)
    private String lastname;
    @Column(name = "email")
    private String email;

    @Transient
    private List<Rating> ratings;
}
