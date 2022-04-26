package com.musalasoft.droneservice.repo;

import com.musalasoft.droneservice.models.Transaction;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan("com.musalasoft.droneservice.service")
class TransactionRepoTest {

    @Autowired
    DroneRepo droneRepo;

    @Test
    @Disabled
    void itShouldFindByTransactionId() {

    }

    @Test
    @Disabled
    void findByCodeSn() {
    }

    @Test
    @Disabled
    void checkLoadedMedication() {
    }
}