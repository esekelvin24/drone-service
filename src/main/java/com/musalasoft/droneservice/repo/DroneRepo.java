package com.musalasoft.droneservice.repo;

import com.musalasoft.droneservice.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DroneRepo extends JpaRepository<Drone, Long> {
    Drone findBySn(String sn);

    @Query("SELECT d FROM Drone d WHERE state_id = 5 or state_id = 6")
    List<Drone> getAvailableDrone();
}
