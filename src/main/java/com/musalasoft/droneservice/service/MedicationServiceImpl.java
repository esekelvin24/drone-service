package com.musalasoft.droneservice.service;

import com.musalasoft.droneservice.models.Medication;
import com.musalasoft.droneservice.repo.MedicationRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepo medicationRepo;

    @Override
    public Medication saveMedication(Medication medication) {
        return medicationRepo.save(medication);
    }

    @Override
    public Medication getMedication(String code) {
        return medicationRepo.findByCode(code);
    }

    @Override
    public List<Medication> getMedications() {
        return medicationRepo.findAll();
    }
}
