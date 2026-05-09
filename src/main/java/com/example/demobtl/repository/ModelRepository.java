package com.example.demobtl.repository;

import com.example.demobtl.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    // Sử dụng Native Query để tìm kiếm trực tiếp dưới DB
    @Query(value = "SELECT * FROM Model WHERE " +
            "(:txtName IS NULL OR :txtName = '' OR LOWER(name) LIKE LOWER(CONCAT('%', :txtName, '%'))) AND " +
            "(:txtVersion IS NULL OR :txtVersion = '' OR LOWER(version) LIKE LOWER(CONCAT('%', :txtVersion, '%'))) AND " +
            "(:txtAccuracy IS NULL OR accuracy >= :txtAccuracy) AND " +
            "(:txtPrecision IS NULL OR precision_val >= :txtPrecision) AND " +
            "(:txtRecall IS NULL OR recall >= :txtRecall) AND " +
            "(:createdAt IS NULL OR created_at >= :createdAt) AND " +
            "(:txtTrainConfig IS NULL OR :txtTrainConfig = '' OR LOWER(train_config) LIKE LOWER(CONCAT('%', :txtTrainConfig, '%')))", 
            nativeQuery = true)
    List<Model> getFilteredModelsNative(
            @Param("txtName") String txtName,
            @Param("txtVersion") String txtVersion,
            @Param("txtAccuracy") Double txtAccuracy,
            @Param("txtPrecision") Double txtPrecision,
            @Param("txtRecall") Double txtRecall,
            @Param("createdAt") java.time.LocalDateTime createdAt,
            @Param("txtTrainConfig") String txtTrainConfig);
}
