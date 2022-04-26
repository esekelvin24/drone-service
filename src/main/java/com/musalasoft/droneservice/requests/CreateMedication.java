package com.musalasoft.droneservice.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CreateMedication {
    private String medicationName;
    private String medicationCode;
    private String medicationImageUrl;
    private int medicationWeight;


}
