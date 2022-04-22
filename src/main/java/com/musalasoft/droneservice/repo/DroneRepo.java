package com.musalasoft.droneservice.repo;

import com.musalasoft.droneservice.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepo extends JpaRepository<Drone, Long> {
    Drone findBySn(String sn);
}
