package com.musalasoft.droneservice.service;

import com.musalasoft.droneservice.models.DroneBatteryAuditLog;
import com.musalasoft.droneservice.requests.SaveLogRequest;


import java.util.List;


public interface DroneBatteryAuditService {
    DroneBatteryAuditLog saveRechargeLog(SaveLogRequest saveLogRequest);
    DroneBatteryAuditLog saveDischargeLog(SaveLogRequest saveLogRequest);

}
