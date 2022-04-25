package com.musalasoft.droneservice.utilities;

import com.musalasoft.droneservice.models.Drone;
import com.musalasoft.droneservice.models.DroneBatterProcessType;
import com.musalasoft.droneservice.repo.DroneRepo;
import com.musalasoft.droneservice.requests.SaveLogRequest;
import com.musalasoft.droneservice.service.DroneBatteryAuditService;
import com.musalasoft.droneservice.service.DroneBatteryAuditServiceImpl;
import com.musalasoft.droneservice.service.DroneService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;


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
