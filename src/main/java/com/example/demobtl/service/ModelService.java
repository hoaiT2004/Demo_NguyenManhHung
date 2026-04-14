package com.example.demobtl.service;

import com.example.demobtl.model.Statistic;
import com.example.demobtl.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public List<Statistic> getListModelWithFilterContraints(String param) {
        List<com.example.demobtl.model.Model> models = modelRepository.getListModelWithFilterContraints();
        Map<String, Long> countMap = new LinkedHashMap<>();

        for (com.example.demobtl.model.Model m : models) {
            String value = getFieldValue(m, param);
            countMap.merge(value, 1L, Long::sum);
        }

        long total = models.size();
        List<Statistic> result = new ArrayList<>();
        for (Map.Entry<String, Long> entry : countMap.entrySet()) {
            double percentage = total > 0 ? (entry.getValue() * 100.0 / total) : 0;
            result.add(new Statistic(entry.getKey(), entry.getValue(), Math.round(percentage * 100.0) / 100.0));
        }
        return result;
    }

    private String getFieldValue(com.example.demobtl.model.Model model, String field) {
        switch (field) {
            case "name":
                return model.getName() != null ? model.getName() : "N/A";
            case "version":
                return model.getVersion() != null ? model.getVersion() : "N/A";
            case "accuracy":
                return model.getAccuracy() != null ? String.valueOf(model.getAccuracy()) : "N/A";
            case "precision":
                return model.getPrecision() != null ? String.valueOf(model.getPrecision()) : "N/A";
            case "recall":
                return model.getRecall() != null ? String.valueOf(model.getRecall()) : "N/A";
            case "trainConfig":
                return model.getTrainConfig() != null ? model.getTrainConfig() : "N/A";
            default:
                return "N/A";
        }
    }
}
