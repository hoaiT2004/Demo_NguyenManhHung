package com.example.demobtl.controller;

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
    public String showStatisticModel(
            @RequestParam(required = false) String txtName,
            @RequestParam(required = false) String txtVersion,
            @RequestParam(required = false) Double txtAccuracy,
            @RequestParam(required = false) Double txtPrecision,
            @RequestParam(required = false) Double txtRecall,
            @RequestParam(required = false) String txtTrainConfig,
            @RequestParam(required = false) String createdAt,
            Model model, HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        // Giữ lại giá trị filter trên form
        model.addAttribute("txtName", txtName);
        model.addAttribute("txtVersion", txtVersion);
        model.addAttribute("txtAccuracy", txtAccuracy);
        model.addAttribute("txtPrecision", txtPrecision);
        model.addAttribute("txtRecall", txtRecall);
        model.addAttribute("txtTrainConfig", txtTrainConfig);
        model.addAttribute("createdAt", createdAt);

        // Kiểm tra có filter nào được nhập không
        boolean hasFilter = (txtName != null && !txtName.trim().isEmpty())
                || (txtVersion != null && !txtVersion.trim().isEmpty())
                || txtAccuracy != null
                || txtPrecision != null
                || txtRecall != null
                || (createdAt != null && !createdAt.trim().isEmpty())
                || (txtTrainConfig != null && !txtTrainConfig.trim().isEmpty());

        if (hasFilter) {
            List<com.example.demobtl.model.Model> ListModel = modelService.getFilteredModels(
                    txtName, txtVersion, txtAccuracy, txtPrecision, txtRecall,
                    createdAt, txtTrainConfig);
            model.addAttribute("ListModel", ListModel);
            model.addAttribute("totalFiltered", ListModel.size());
        }

        return "statisticModel";
    }
}
