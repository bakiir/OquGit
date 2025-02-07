package com.example.books.model.task;

import com.example.books.model.Course;
import com.example.books.model.Module;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate deadline;
    private int tries;
    private Float grade;
    private Float mark;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Question> questions;


    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;






}
