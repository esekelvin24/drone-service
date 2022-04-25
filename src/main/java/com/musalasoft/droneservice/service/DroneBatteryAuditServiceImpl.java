package com.musalasoft.droneservice.service;

import com.musalasoft.droneservice.models.Drone;
import com.musalasoft.droneservice.models.DroneBatterProcessType;
import com.musalasoft.droneservice.models.DroneBatteryAuditLog;
import com.musalasoft.droneservice.repo.DroneBatteryAuditRepo;
import com.musalasoft.droneservice.repo.DroneRepo;
import com.musalasoft.droneservice.requests.SaveLogRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class DroneBatteryAuditServiceImpl implements DroneBatteryAuditService{

   private final DroneBatteryAuditRepo droneBatteryAuditRepo;
   private final DroneRepo droneRepo;

    @Override
    public DroneBatteryAuditLog saveRechargeLog(SaveLogRequest saveLogRequest) {
        Drone drone = droneRepo.findBySn(saveLogRequest.getDroneSn());
        return droneBatteryAuditRepo.save(new DroneBatteryAuditLog(null, drone , DroneBatterProcessType.CHARGE, drone.getState(), saveLogRequest.getPreviousBatteryPerc(), saveLogRequest.getCurrentBatteryPerc(), new Date()));
    }


    @Override
    public DroneBatteryAuditLog saveDischargeLog(SaveLogRequest saveLogRequest) {
        Drone drone = droneRepo.findBySn(saveLogRequest.getDroneSn());
        return droneBatteryAuditRepo.save(new DroneBatteryAuditLog(null, drone , DroneBatterProcessType.DISCHARGE, drone.getState(), saveLogRequest.getPreviousBatteryPerc(), saveLogRequest.getCurrentBatteryPerc(), new Date()));
    }


}
