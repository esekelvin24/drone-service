package com.esekelvin.droneservice.service;

import com.esekelvin.droneservice.models.Transaction;
import com.esekelvin.droneservice.requests.LoadDroneRequest;
import com.esekelvin.droneservice.requests.LoadedDroneMedication;

import java.util.List;


public interface TransactionService {
    Transaction loadDrone(LoadDroneRequest loadDroneRequest);
    Transaction getTransaction(String transactionId);
    Transaction getTransaction(Long droneId, Long medicationId);
    List<Transaction> checkLoadedMedication(LoadedDroneMedication loadedDroneMedication);
    List<Transaction> getTransactions();


}
