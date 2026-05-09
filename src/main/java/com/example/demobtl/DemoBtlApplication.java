package com.example.demobtl;

import com.example.demobtl.model.Employee;
import com.example.demobtl.model.Fingerprint;
import com.example.demobtl.model.Model;
import com.example.demobtl.model.User;
import com.example.demobtl.repository.FingerprintRepository;
import com.example.demobtl.repository.ModelRepository;
import com.example.demobtl.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoBtlApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private FingerprintRepository fingerprintRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoBtlApplication.class, args);
    }

u//    @PostConstruct
//    public void initData() {
//        if (userRepository.count() > 0 || modelRepository.count() > 0) {
//            return;
//        }
//
//        // ====== Tạo 15 Model ======
//        String[][] modelData = {
//                {"FingerprintNet",   "1.0", "0.95", "0.93", "0.92", "{\"lr\":0.001,\"epochs\":50,\"batch_size\":32}"},
//                {"FingerprintNet",   "1.1", "0.96", "0.94", "0.93", "{\"lr\":0.001,\"epochs\":60,\"batch_size\":32}"},
//                {"FingerprintNet",   "2.0", "0.97", "0.96", "0.95", "{\"lr\":0.0005,\"epochs\":100,\"batch_size\":64}"},
//                {"MinutiaeDetector", "1.0", "0.91", "0.89", "0.88", "{\"lr\":0.01,\"epochs\":30,\"batch_size\":16}"},
//                {"MinutiaeDetector", "1.1", "0.93", "0.91", "0.90", "{\"lr\":0.005,\"epochs\":40,\"batch_size\":16}"},
//                {"MinutiaeDetector", "2.0", "0.94", "0.92", "0.91", "{\"lr\":0.005,\"epochs\":50,\"batch_size\":32}"},
//                {"DeepFinger",       "1.0", "0.88", "0.86", "0.85", "{\"lr\":0.001,\"epochs\":80,\"batch_size\":64}"},
//                {"DeepFinger",       "1.1", "0.90", "0.88", "0.87", "{\"lr\":0.001,\"epochs\":100,\"batch_size\":64}"},
//                {"DeepFinger",       "2.0", "0.92", "0.90", "0.89", "{\"lr\":0.0005,\"epochs\":120,\"batch_size\":128}"},
//                {"RidgeAnalyzer",    "1.0", "0.87", "0.85", "0.84", "{\"lr\":0.01,\"epochs\":25,\"batch_size\":16}"},
//                {"RidgeAnalyzer",    "1.1", "0.89", "0.87", "0.86", "{\"lr\":0.005,\"epochs\":35,\"batch_size\":32}"},
//                {"RidgeAnalyzer",    "2.0", "0.91", "0.90", "0.88", "{\"lr\":0.005,\"epochs\":45,\"batch_size\":32}"},
//                {"BioAuthModel",     "1.0", "0.94", "0.92", "0.91", "{\"lr\":0.001,\"epochs\":70,\"batch_size\":32}"},
//                {"BioAuthModel",     "1.1", "0.95", "0.93", "0.92", "{\"lr\":0.001,\"epochs\":90,\"batch_size\":64}"},
//                {"BioAuthModel",     "2.0", "0.97", "0.95", "0.94", "{\"lr\":0.0005,\"epochs\":110,\"batch_size\":128}"}
//        };
//
//        List<Model> models = new ArrayList<>();
//        for (int i = 0; i < modelData.length; i++) {
//            Model m = new Model();
//            m.setName(modelData[i][0]);
//            m.setVersion(modelData[i][1]);
//            m.setAccuracy(Double.parseDouble(modelData[i][2]));
//            m.setPrecision(Double.parseDouble(modelData[i][3]));
//            m.setRecall(Double.parseDouble(modelData[i][4]));
//            m.setTrainConfig(modelData[i][5]);
//            m.setCreatedAt(LocalDateTime.of(2025, 1 + (i % 12), 10 + (i % 15), 8, 30));
//            models.add(modelRepository.save(m));
//        }
//
//        // ====== Tạo 10 User + 10 Employee + 10 Fingerprint ======
//        String[][] employeeData = {
//                {"admin",    "123456", "Nguyễn Văn An",    "Nam",  "1990", "Hà Nội",           "Phòng IT"},
//                {"user01",   "123456", "Trần Thị Bình",    "Nữ",   "1992", "Hải Phòng",        "Phòng Nhân sự"},
//                {"user02",   "123456", "Lê Hoàng Cường",   "Nam",  "1988", "Đà Nẵng",          "Phòng Kế toán"},
//                {"user03",   "123456", "Phạm Thị Dung",    "Nữ",   "1995", "TP Hồ Chí Minh",   "Phòng Marketing"},
//                {"user04",   "123456", "Hoàng Văn Em",     "Nam",  "1991", "Nghệ An",          "Phòng IT"},
//                {"user05",   "123456", "Vũ Thị Phương",    "Nữ",   "1993", "Bắc Ninh",         "Phòng Nhân sự"},
//                {"user06",   "123456", "Đỗ Minh Giang",    "Nam",  "1989", "Quảng Ninh",       "Phòng Kỹ thuật"},
//                {"user07",   "123456", "Ngô Thị Hạnh",     "Nữ",   "1994", "Hưng Yên",         "Phòng Kế toán"},
//                {"user08",   "123456", "Bùi Đức Inh",      "Nam",  "1987", "Thanh Hóa",        "Phòng Kỹ thuật"},
//                {"user09",   "123456", "Lý Thị Kim",       "Nữ",   "1996", "Nam Định",         "Phòng Marketing"}
//        };
//
//        String[] fingerIndexes = {
//                "Ngón trỏ phải", "Ngón cái phải", "Ngón giữa phải",
//                "Ngón trỏ trái", "Ngón cái trái", "Ngón giữa trái",
//                "Ngón trỏ phải", "Ngón cái phải", "Ngón trỏ trái", "Ngón cái trái"
//        };
//
//        for (int i = 0; i < employeeData.length; i++) {
//            // Tạo User
//            User user = new User();
//            user.setUsername(employeeData[i][0]);
//            user.setPassword(employeeData[i][1]);
//            user = userRepository.save(user);
//
//            // Tạo Employee
//            Employee emp = new Employee();
//            emp.setUser(user);
//            emp.setFullName(employeeData[i][2]);
//            emp.setGender(employeeData[i][3]);
//            emp.setBirthYear(Integer.parseInt(employeeData[i][4]));
//            emp.setAddress(employeeData[i][5]);
//            emp.setDepartment(employeeData[i][6]);
//
//            // Tạo Fingerprint
//            Fingerprint fp = new Fingerprint();
//            fp.setFingerIndex(fingerIndexes[i]);
//            fp.setVectorData("vector_" + String.format("%04d", i + 1) + "_" + generateRandomVector());
//            fp.setEmployee(emp);
//            fp.setModel(models.get(i % models.size()));
//
//            emp.setFingerprint(fp);
//            user.setEmployee(emp);
//            userRepository.save(user);
//        }
//    }
//
//    private String generateRandomVector() {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 10; i++) {
//            if (i > 0) sb.append(",");
//            sb.append(String.format("%.4f", Math.random()));
//        }
//        return sb.toString();
//    }
}
