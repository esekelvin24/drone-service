package com.musalasoft.droneservice.service;

import com.musalasoft.droneservice.models.Transaction;
import com.musalasoft.droneservice.requests.LoadDroneRequest;
import com.musalasoft.droneservice.requests.LoadedDroneMedication;

import java.util.List;


public interface TransactionService {
    Transaction loadDrone(LoadDroneRequest loadDroneRequest);
    Transaction getTransaction(String transactionId);
    Transaction getTransaction(Long droneId, Long medicationId);
    List<Transaction> checkLoadedMedication(LoadedDroneMedication loadedDroneMedication);
    List<Transaction> getTransactions();


}
