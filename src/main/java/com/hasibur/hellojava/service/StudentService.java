package com.hasibur.hellojava.service;

import com.hasibur.hellojava.model.Laptop;
import com.hasibur.hellojava.model.Student;

import java.util.List;


public interface StudentService {
    public List<Student> getAllStudents();
    public Student saveStudent(Student student);
}
