package com.musalasoft.droneservice.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoadDroneRequest {
    private String droneSn;
    private String medicationCode;
}
