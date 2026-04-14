package com.example.demobtl.controller;

import com.example.demobtl.model.Statistic;
import com.example.demobtl.service.ModelService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ModelController {

    @Autowired
    private ModelService modelService;

    @GetMapping("/statisticModel")
    public String showStatisticModel(@RequestParam(required = false) String field, Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        if (field != null && !field.trim().isEmpty()) {
            List<Statistic> statisticData = modelService.getListModelWithFilterContraints(field);
            model.addAttribute("field", field);
            model.addAttribute("statisticData", statisticData);
        }
        return "statisticModel";
    }
}
