package com.musalasoft.droneservice.utilities;

import com.musalasoft.droneservice.models.Drone;
import com.musalasoft.droneservice.service.DroneService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;


@AllArgsConstructor
@Transactional
public class DroneBatteryDrainer extends TimerTask {

    DroneService droneService;

    @Override
    public void run() {


        List<Drone> drones = droneService.getDrones();

        for(Drone drone : drones)
        {
            String state = drone.getState().getName();
            if (state.equals("IDLE") && drone.getBattery() > 5)
                drone.setBattery(drone.getBattery() - 5); //If the drone is idle remove 5% from its battery life

            if (state.equals("DELIVERING") && drone.getBattery() > 20)
                drone.setBattery(drone.getBattery() - 20); //If the drone is idle remove 20% from its battery life
        }

    }
}
