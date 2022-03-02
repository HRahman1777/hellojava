package com.hasibur.hellojava.controller;

import com.hasibur.hellojava.model.Laptop;
import com.hasibur.hellojava.model.Student;
import com.hasibur.hellojava.service.LaptopService;
import com.hasibur.hellojava.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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


}
