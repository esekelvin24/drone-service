package com.esekelvin.droneservice.service;

import com.esekelvin.droneservice.models.Medication;
import com.esekelvin.droneservice.requests.CreateMedication;

import java.util.List;


public interface MedicationService {
    Medication saveMedication(CreateMedication createMedication);
    Medication getMedication(String code);
    List<Medication> getMedications();
}
