package com.esekelvin.droneservice.service;

import com.esekelvin.droneservice.models.Model;
import com.esekelvin.droneservice.models.Drone;
import com.esekelvin.droneservice.models.DroneBatteryAuditLog;
import com.esekelvin.droneservice.models.State;
import com.esekelvin.droneservice.requests.DroneRequest;
import com.esekelvin.droneservice.requests.SaveLogRequest;

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
