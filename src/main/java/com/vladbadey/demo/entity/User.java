package com.vladbadey.demo.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    private boolean flag;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_fandoms",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "fandom_id"))
    private Set<Fandom> fandoms = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Composition> usersCompositions = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_favorites_compositions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "composition_id"))
    private Set<Composition> favoritesCompositions = new HashSet<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}