package com.musalasoft.droneservice.service;

import com.musalasoft.droneservice.models.Drone;
import com.musalasoft.droneservice.models.DroneBatteryAuditLog;
import com.musalasoft.droneservice.models.Model;
import com.musalasoft.droneservice.models.State;
import com.musalasoft.droneservice.requests.DroneRequest;
import com.musalasoft.droneservice.requests.SaveLogRequest;

import java.util.List;

public interface DroneService {
    Drone saveDrone(DroneRequest drone);
    Model saveModel(Model model);
    State saveState(State state);
    int saveBatteryPercentage(String droneSn, int battery);

    void addDroneModel(String sn, String modelName);
    void addDroneState(String sn, String stateName);

    Drone getDrone(String sn); //get a single drone with a drone serial number
    List<Drone> getDrones(); //get all drones in a list
    List<Drone> getAvailableDrone();

    List<DroneBatteryAuditLog> getDroneAuditLog(String droneSn);
    List<DroneBatteryAuditLog> getLogByDroneSnProcessType(SaveLogRequest saveLogRequest);

    State getState(String stateName);
    List<State> getStates();

    Model getModel(String modelName);
    List<Model> getModels();


}
