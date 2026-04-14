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
    public String showLogin(Model model) {
        return "login";
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

    @GetMapping("/manageEmployee")
    public String showListEmployee(@RequestParam(required = false) String fullName, Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        if (fullName != null && !fullName.trim().isEmpty()) {
            List<Employee> employeeList = userService.getListEmployee(fullName);
            model.addAttribute("employees", employeeList);
            model.addAttribute("search", fullName);
        }
        return "manageEmployee";
    }

    @GetMapping("/confirmDialog")
    public String showDialogConfirm(@RequestParam Long userId, Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        model.addAttribute("userId", userId);
        return "confirmDialog";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Long userId, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        try {
            userService.deleteUser(userId);
            redirectAttributes.addFlashAttribute("success", "Xóa nhân viên thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi xóa nhân viên");
        }
        return "redirect:/manageEmployee";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
