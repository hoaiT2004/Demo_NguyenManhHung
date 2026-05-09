package com.example.demobtl.controller;

import com.example.demobtl.model.Employee;
import com.example.demobtl.model.User;
import com.example.demobtl.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User u, RedirectAttributes redirectAttributes) {
        if (userService.registerUser(u)) {
            redirectAttributes.addFlashAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "redirect:/register";
        }
    }

    @PostMapping("/login")
    public String checkLogin(@ModelAttribute User u, HttpSession session, RedirectAttributes redirectAttributes) {
        if (userService.checkLogin(u)) {
            session.setAttribute("loggedInUser", u.getUsername());
            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("error", "Thông báo đăng nhập lỗi");
            return "redirect:/login";
        }
    }

    @GetMapping("/home")
    public String showHome(HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
