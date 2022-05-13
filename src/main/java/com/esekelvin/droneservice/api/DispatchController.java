package com.esekelvin.droneservice.api;

import com.esekelvin.droneservice.models.Transaction;
import com.esekelvin.droneservice.requests.LoadDroneRequest;
import com.esekelvin.droneservice.requests.LoadedDroneMedication;
import com.esekelvin.droneservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DispatchController {

    final private TransactionService transactionService;

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions()
    {
        return ResponseEntity.ok().body(transactionService.getTransactions());
    }

    @PostMapping("/drone/load")
    public ResponseEntity<Transaction> loadTransactions(@RequestBody LoadDroneRequest loadDroneRequest)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/drone/save").toUriString());
        return ResponseEntity.created(uri).body(transactionService.loadDrone(loadDroneRequest));
    }

    @PostMapping("/drone/loaded/medications")
    public ResponseEntity<List<Transaction>> getLoadedMedication(@RequestBody LoadedDroneMedication loadedDroneMedication)
    {
        return ResponseEntity.ok().body(transactionService.checkLoadedMedication(loadedDroneMedication));
    }

}
