package com.hasibur.hellojava.controller;

import com.hasibur.hellojava.model.Laptop;
import com.hasibur.hellojava.model.Student;
import com.hasibur.hellojava.service.LaptopService;
import com.hasibur.hellojava.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private LaptopService laptopService;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("laptop_form", new Laptop());
        model.addAttribute("student_form", new Student());
        model.addAttribute("laptops", laptopService.allLaptop());

        return "index.html";
    }

    // LAPTOP -----------------------

    @PostMapping("/addlaptop")
    public String addLaptop(@ModelAttribute Laptop laptop){

        laptopService.saveLaptop(laptop);

        return "redirect:/";
    }

    @GetMapping("/alllaptop")
    public String allLaptop(Model model){

        model.addAttribute("laptops", laptopService.allLaptop());

        return "laptop.html";
    }

    @GetMapping("/laptop/edit/{id}")
    public String editLaptop(@PathVariable("id") Long id, Model model){

        Laptop laptop = laptopService.findLaptop(id);

        model.addAttribute("laptop_form", laptop);

        return "laptopedit.html";
    }

    @GetMapping("/laptop/delete/{id}")
    public String deleteLaptop(@PathVariable("id") Long id){

        laptopService.deleteLaptop(id);

        return "redirect:/alllaptop";
    }



    // STUDENT -----------------------

    @PostMapping("/addstudent")
    public String addStudent(@ModelAttribute Student student){

        studentService.saveStudent(student);

        return "redirect:/";
    }

    @GetMapping("/allstudent")
    public String allStudent(Model model){

        model.addAttribute("students", studentService.getAllStudents());

        return "student.html";
    }

    @GetMapping("/student/edit/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model){

        Student student = studentService.findStudent(id);

        model.addAttribute("student_form", student);
        model.addAttribute("laptops", laptopService.allLaptop());

        return "studentedit.html";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id){

        studentService.deleteStudent(id);

        return "redirect:/allstudent";
    }


}
