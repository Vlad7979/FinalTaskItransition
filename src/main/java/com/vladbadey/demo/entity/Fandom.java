package com.vladbadey.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "fandoms")
@Data
@NoArgsConstructor
public class Fandom {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 250)
    private String image;
}
