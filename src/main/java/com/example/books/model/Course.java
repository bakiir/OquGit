package com.example.books.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Entity
@Table(name = "cources")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;


    @Column(length = 1000, nullable = false)
    private String description;


    private String instructor;

    private double price;

    private boolean isPublished;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", orphanRemoval = true)
    private List<Module> modules;

    public void addModule(Module module) {
        modules.add(module);
        module.setCourse(this); // Ensure bidirectional mapping
    }

   @ManyToOne
   @JoinColumn(name = "category_id", nullable = false)
   private Category category;


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

}
