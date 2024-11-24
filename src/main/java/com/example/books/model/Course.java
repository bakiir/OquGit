package com.example.books.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cources")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;


    @Column(length = 1000, nullable = false)
    private String description;


    private String instructor;

    private double price;

    private boolean isPublished;

    @ElementCollection
    private List<String> modules;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

}
