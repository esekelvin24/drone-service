package com.esekelvin.droneservice.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DroneRequest {
    private String sn;
    private int weight;
    private int battery;
    private String model;
}
