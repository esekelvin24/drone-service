package com.esekelvin.droneservice.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoadedDroneMedication {
    private String droneSn;
    private String transactionStatus;
}
