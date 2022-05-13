package com.esekelvin.droneservice.api;

import com.esekelvin.droneservice.models.Medication;
import com.esekelvin.droneservice.requests.CreateMedication;
import com.esekelvin.droneservice.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MedicationController {

    private final MedicationService medicationService;

    @GetMapping("/medications")
    public ResponseEntity<List<Medication>> getMedications()
    {
        return ResponseEntity.ok().body(medicationService.getMedications());
    }

    @PostMapping("/medication/save")
    public ResponseEntity<Medication> saveMedication(@RequestBody CreateMedication createMedication)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/medication/save").toUriString());
        return ResponseEntity.created(uri).body(medicationService.saveMedication(createMedication));
    }


}
