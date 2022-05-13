package com.esekelvin.droneservice.utilities;

import com.esekelvin.droneservice.models.Drone;
import com.esekelvin.droneservice.models.DroneBatterProcessType;
import com.esekelvin.droneservice.service.DroneBatteryAuditService;
import com.esekelvin.droneservice.service.DroneService;
import com.esekelvin.droneservice.requests.SaveLogRequest;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.TimerTask;


@AllArgsConstructor
public class DroneBatteryDrainer extends TimerTask {

    private final DroneService droneService;
    private final DroneBatteryAuditService droneBatteryAuditService;

    @Override
    public void run() {


        List<Drone> drones = droneService.getDrones();

        for(Drone drone : drones)
        {
            String state = drone.getState().getName();
            if ((!state.equals("DELIVERING") ) && drone.getBattery() > 5)
            {
                int prevBattery = drone.getBattery();
                int currBattery = prevBattery - 5;
                int save = droneService.saveBatteryPercentage(drone.getSn(), currBattery); //If the drone is idle remove 5% from its battery life
                droneBatteryAuditService.saveDischargeLog(new SaveLogRequest(drone.getSn(), DroneBatterProcessType.DISCHARGE.toString(),prevBattery, currBattery ));
            }


            if ((state.equals("DELIVERING") || state.equals("RETURNING"))  && drone.getBattery() > 20)
            {
                int prevBattery = drone.getBattery();
                int currBattery = prevBattery - 20;
                int save = droneService.saveBatteryPercentage(drone.getSn(), currBattery); //If the drone is delivering remove 20% from its battery life
                droneBatteryAuditService.saveDischargeLog(new SaveLogRequest(drone.getSn(), DroneBatterProcessType.DISCHARGE.toString(),prevBattery, currBattery ));
            }
        }

    }
}
