package com.musalasoft.droneservice.repo;

import com.musalasoft.droneservice.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DroneRepo extends JpaRepository<Drone, Long> {
    Drone findBySn(String sn);

    @Query("SELECT d FROM Drone d WHERE state_id = 5 or state_id = 6")
    List<Drone> getAvailableDrone();
    @Modifying
    @Query("UPDATE Drone d SET d.battery = ?1 WHERE d.id = ?2")
    int saveDroneBatteryPerc(int battery, Long id);
}
