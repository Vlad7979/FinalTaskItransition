package com.vladbadey.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "chapter")
@Data
@NoArgsConstructor
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "composition_id")
    private Composition composition;

    public void setComposition(Composition composition) {
        this.composition = composition;
    }
}
