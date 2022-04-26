package com.musalasoft.droneservice.service;

import com.musalasoft.droneservice.exception.ApiRequestException;
import com.musalasoft.droneservice.models.Medication;
import com.musalasoft.droneservice.repo.MedicationRepo;
import com.musalasoft.droneservice.requests.CreateMedication;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.musalasoft.droneservice.utilities.FieldValidator.validateMedicationCode;
import static com.musalasoft.droneservice.utilities.FieldValidator.validateMedicationName;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepo medicationRepo;

    @Override
    public Medication saveMedication(CreateMedication createMedication) {

        if (validateMedicationName(createMedication.getMedicationName()) == false)
        {
            throw new ApiRequestException("Medication name can only be letters, numbers, ‘-‘, ‘_’" );
        }

        if (validateMedicationCode(createMedication.getMedicationCode()) == false)
        {
            throw new ApiRequestException("Medication Code can only be UPPERCASE letters, numbers, ‘-‘, ‘_’" );
        }

        return medicationRepo.save(new Medication(null, createMedication.getMedicationName(), createMedication.getMedicationWeight(), createMedication.getMedicationCode(), createMedication.getMedicationImageUrl(), null));
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
