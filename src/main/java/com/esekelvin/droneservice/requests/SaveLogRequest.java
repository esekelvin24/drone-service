package com.esekelvin.droneservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveLogRequest {
    private String droneSn;
    private String proccessType;
    private int previousBatteryPerc;
    private int currentBatteryPerc;
}
