package com.hasibur.hellojava.controller;

import com.hasibur.hellojava.model.Laptop;
import com.hasibur.hellojava.model.Student;
import com.hasibur.hellojava.service.LaptopService;
import com.hasibur.hellojava.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainControllerRest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private LaptopService laptopService;

    // LAPTOP -----------------------
    @GetMapping("/laptop/all")
    public List<Laptop> allLaptops() {
        return laptopService.allLaptop();
    }

    @GetMapping("/laptop/{id}")
    public Laptop singleLaptop(@PathVariable Long id) {
        return laptopService.findLaptop(id);
    }

    @PostMapping("/laptop/add")
    public Laptop addLaptop(@RequestBody Laptop laptop) {

        return laptopService.saveLaptop(laptop);
    }

    @PostMapping("/laptop/edit")
    public Laptop editLaptop(@RequestBody Laptop laptop) {

        return laptopService.saveLaptop(laptop);
    }

    @GetMapping("/laptop/{id}/delete")
    public String deleteLaptop(@PathVariable Long id) {
        try {
            laptopService.deleteLaptop(id);
        } catch (Exception ex) {
            return "Failed!! exception msg: " + ex;
        }
        return "Deleted Successfully";
    }



    // STUDENT -----------------------
    @GetMapping("/student/all")
    public List<Student> allStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public Student singleStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @PostMapping("/student/add")
    public Student addStudent(@RequestBody Student student) {

        return studentService.saveStudent(student);
    }

    @PostMapping("/student/edit")
    public Student editStudent(@RequestBody Student student) {

        return studentService.saveStudent(student);
    }

    @GetMapping("/student/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
        } catch (Exception ex) {
            return "Failed!! exception msg: " + ex;
        }
        return "Deleted Successfully";
    }
}
