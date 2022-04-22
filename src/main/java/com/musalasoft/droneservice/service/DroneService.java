package com.musalasoft.droneservice.service;

import com.musalasoft.droneservice.models.Drone;
import com.musalasoft.droneservice.models.Model;
import com.musalasoft.droneservice.models.State;

import java.util.List;

public interface DroneService {
    Drone saveDrone(Drone drone);
    Model saveModel(Model model);
    State saveState(State state);

    void addDroneModel(String sn, String modelName);
    void addDroneState(String sn, String stateName);

    Drone getDrone(String sn); //get a single drone with a drone serial number
    List<Drone> getDrones(); //get all drones in a list
}
