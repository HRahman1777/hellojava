package com.hasibur.hellojava.controller;

import com.hasibur.hellojava.model.Laptop;
import com.hasibur.hellojava.model.Student;
import com.hasibur.hellojava.model.User;
import com.hasibur.hellojava.service.LaptopService;
import com.hasibur.hellojava.service.StudentService;
import com.hasibur.hellojava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Controller
public class MainController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private UserService userService;

    // USER --------------------

    @GetMapping("user")
    public String userIndex(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("UNAME", auth.getName());
        return "user/index.html";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        model.addAttribute("user", new User());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        if (Objects.equals(username, "anonymousUser")){
            return "user/signup_form.html";
        }else {
            return "user/logoutWarn.html";
        }

    }


    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.saveUser(user);

        return "user/register_success.html";
    }

    @GetMapping("/login")
    public String cusLogin() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        if (Objects.equals(username, "anonymousUser")){
            return "user/login.html";
        }else {
            return "user/logoutWarn.html";
        }

    }

    @GetMapping("/accessdenied")
    public String customAccessdenied() {

        return "user/restrict.html";
    }

    @GetMapping("/user/home")
    public String listUsers(Model model) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<User> listUsers = userService.findAll();
        model.addAttribute("users", listUsers);
        model.addAttribute("UNAME", auth.getName());

        return "user/userDetails.html";
    }

    @PostMapping("/user/logout")
    public String customLogout(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication); // <= This is the call you are looking for.
        }
        return "redirect:/";
    }

    // -----------------------------------------------

    @GetMapping("/")
    public String home(Model model) {

        //from user auth- need to pass this every page
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("laptop_form", new Laptop());
        model.addAttribute("student_form", new Student());
        model.addAttribute("laptops", laptopService.allLaptop());
        model.addAttribute("UNAME", auth.getName());

        return "index.html";
    }

    // LAPTOP -----------------------

    @PostMapping("/addlaptop")
    public String addLaptop(@ModelAttribute Laptop laptop) {

        laptopService.saveLaptop(laptop);

        return "redirect:/";
    }

    @GetMapping("/alllaptop")
    public String allLaptop(Model model) {

        model.addAttribute("laptops", laptopService.allLaptop());

        return "laptop.html";
    }

    @GetMapping("/laptop/edit/{id}")
    public String editLaptop(@PathVariable("id") Long id, Model model) {

        Laptop laptop = laptopService.findLaptop(id);

        model.addAttribute("laptop_form", laptop);

        return "laptopedit.html";
    }

    @GetMapping("/laptop/delete/{id}")
    public String deleteLaptop(@PathVariable("id") Long id) {

        laptopService.deleteLaptop(id);

        return "redirect:/alllaptop";
    }


    // STUDENT -----------------------

    @PostMapping("/addstudent")
    public String addStudent(@ModelAttribute Student student) {

        studentService.saveStudent(student);

        return "redirect:/";
    }

    @GetMapping("/allstudent")
    public String allStudent(Model model) {

        model.addAttribute("students", studentService.getAllStudents());

        return "student.html";
    }

    @GetMapping("/student/edit/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model) {

        Student student = studentService.findStudent(id);

        model.addAttribute("student_form", student);
        model.addAttribute("laptops", laptopService.allLaptop());

        return "studentedit.html";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {

        studentService.deleteStudent(id);

        return "redirect:/allstudent";
    }


}
