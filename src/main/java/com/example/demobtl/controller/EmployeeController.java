package com.example.demobtl.controller;

import com.example.demobtl.model.Employee;
import com.example.demobtl.model.User;
import com.example.demobtl.service.EmployeeService;
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
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/manageEmployee")
    public String showListEmployee(@ModelAttribute Employee employee, Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        if (employee != null) {
            String fullName = employee.getFullName();
            if (fullName != null && !fullName.trim().isEmpty()) {
                List<Employee> employeeList = employeeService.getListEmployee(fullName);
                model.addAttribute("employees", employeeList);
                model.addAttribute("search", fullName);
            }
        }
        return "manageEmployee";
    }

    @GetMapping("/confirmDialog")
    public String showDialogConfirm(@ModelAttribute Employee employee, Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        model.addAttribute("userId", employee.getUserId());
        return "confirmDialog";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@ModelAttribute Employee employee, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        try {
            boolean success = employeeService.deleteEmployee(employee.getUserId());
            if (success) {
                redirectAttributes.addFlashAttribute("success", "Xóa nhân viên thành công");
            } else {
                redirectAttributes.addFlashAttribute("error", "Nhân viên không tồn tại!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi xóa nhân viên");
        }
        return "redirect:/manageEmployee";
    }
}
