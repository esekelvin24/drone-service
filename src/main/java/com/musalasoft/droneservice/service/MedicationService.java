package com.musalasoft.droneservice.service;

import com.musalasoft.droneservice.models.Medication;

import java.util.List;


public interface MedicationService {
    Medication saveMedication(Medication medication);
    Medication getMedication(String code);
    List<Medication> getMedications();
}
