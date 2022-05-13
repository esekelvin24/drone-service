package com.esekelvin.droneservice.service;

import com.esekelvin.droneservice.models.Drone;
import com.esekelvin.droneservice.models.DroneBatterProcessType;
import com.esekelvin.droneservice.models.DroneBatteryAuditLog;
import com.esekelvin.droneservice.repo.DroneBatteryAuditRepo;
import com.esekelvin.droneservice.repo.DroneRepo;
import com.esekelvin.droneservice.requests.SaveLogRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

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
