package com.hasibur.hellojava.model;

import net.bytebuddy.implementation.bind.annotation.AllArguments;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student_table")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;

    public Laptop getLaptop() {
        return laptop;
    }

    public Student() {
    }

    public Student(Long id, String name, String email, Laptop laptop) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.laptop = laptop;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }
}
