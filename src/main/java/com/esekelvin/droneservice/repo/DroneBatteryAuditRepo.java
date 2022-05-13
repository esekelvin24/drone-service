package com.esekelvin.droneservice.repo;

import com.esekelvin.droneservice.models.DroneBatteryAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DroneBatteryAuditRepo extends JpaRepository<DroneBatteryAuditLog, Long> {
    List<DroneBatteryAuditLog> findByDroneSn(String droneSn);

    @Query("SELECT a FROM DroneBatteryAuditLog a WHERE drone_id = ?1 and process_type = ?2")
    List<DroneBatteryAuditLog> findByDroneSnProcessType(String droneSn, String processType);

}
