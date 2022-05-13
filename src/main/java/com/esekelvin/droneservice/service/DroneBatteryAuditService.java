package com.esekelvin.droneservice.service;

import com.esekelvin.droneservice.models.DroneBatteryAuditLog;
import com.esekelvin.droneservice.requests.SaveLogRequest;


public interface DroneBatteryAuditService {
    DroneBatteryAuditLog saveRechargeLog(SaveLogRequest saveLogRequest);
    DroneBatteryAuditLog saveDischargeLog(SaveLogRequest saveLogRequest);

}
