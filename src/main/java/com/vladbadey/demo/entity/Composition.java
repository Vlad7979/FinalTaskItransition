package com.vladbadey.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "composition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Composition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "date")
    private Date date;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Chapter> chapters = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable( name = "composition_fandom",
            joinColumns = @JoinColumn(name = "composition_id"),
            inverseJoinColumns = @JoinColumn(name = "fandom_id"))
    private Fandom fandom;

    public void setUser(User user) {
        this.user = user;
    }
}
