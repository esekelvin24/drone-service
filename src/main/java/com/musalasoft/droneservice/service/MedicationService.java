package com.musalasoft.droneservice.service;

import com.musalasoft.droneservice.models.Medication;
import com.musalasoft.droneservice.requests.CreateMedication;

import java.util.List;


public interface MedicationService {
    Medication saveMedication(CreateMedication createMedication);
    Medication getMedication(String code);
    List<Medication> getMedications();
}
