package com.example.demobtl.repository;

import com.example.demobtl.model.Fingerprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FingerprintRepository extends JpaRepository<Fingerprint, Long> {
}
