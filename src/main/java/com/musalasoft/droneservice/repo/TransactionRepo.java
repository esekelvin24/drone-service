package com.musalasoft.droneservice.repo;

import com.musalasoft.droneservice.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    Transaction findById(String id);


    @Query("SELECT t FROM Transaction t WHERE drone_id = ?1 and medication_id = ?2")
    Transaction findByCodeSn(Long droneId, Long medicationId);

    @Query("SELECT t FROM Transaction t WHERE drone_id = ?1 and transaction_status = ?2")
    List<Transaction> checkLoadedMedication(Long droneId, String status);
}
