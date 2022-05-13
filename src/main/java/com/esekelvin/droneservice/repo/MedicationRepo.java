package com.esekelvin.droneservice.repo;

import com.esekelvin.droneservice.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepo extends JpaRepository<Medication, Long> {
    Medication findByCode(String code);
}
