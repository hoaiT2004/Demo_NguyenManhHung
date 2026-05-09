package com.example.demobtl.service;

import com.example.demobtl.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    /**
     * Lọc danh sách Model theo nhiều tiêu chí filter
     */
    public List<com.example.demobtl.model.Model> getFilteredModels(
            String txtName, String txtVersion,
            Double txtAccuracy, Double txtPrecision, Double txtRecall,
            String createdAt, String txtTrainConfig) {

        LocalDateTime fromDate = null;
        if (createdAt != null && !createdAt.trim().isEmpty()) {
            try {
                fromDate = LocalDate.parse(createdAt).atStartOfDay();
            } catch (Exception ignored) {}
        }

        return modelRepository.getFilteredModelsNative(
                txtName, txtVersion, txtAccuracy, txtPrecision, txtRecall, fromDate, txtTrainConfig);
    }
}
