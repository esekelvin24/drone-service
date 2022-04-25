package com.musalasoft.droneservice.api;

import com.musalasoft.droneservice.models.*;
import com.musalasoft.droneservice.requests.DroneRequest;
import com.musalasoft.droneservice.requests.LoadedDroneMedication;
import com.musalasoft.droneservice.requests.SaveLogRequest;
import com.musalasoft.droneservice.service.DroneService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DroneController {
    private final DroneService droneService;

    @GetMapping("/drones")
    public ResponseEntity<List<Drone>> getDrones()
    {
        return ResponseEntity.ok().body(droneService.getDrones());
    }

    @GetMapping("/states")
    public ResponseEntity<List<State>> getStates()
    {
        return ResponseEntity.ok().body((droneService.getStates()));
    }

    @GetMapping("/models")
    public ResponseEntity<List<Model>> getModels()
    {
        return ResponseEntity.ok().body((droneService.getModels()));
    }

    @PostMapping("/drone/save")
    public ResponseEntity<Drone> saveDrone(@RequestBody DroneRequest drone)
    {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/drone/save").toUriString());
        return ResponseEntity.created(uri).body(droneService.saveDrone(drone));
    }

    @PostMapping("/drone/battery")
    public ResponseEntity<Drone> checkBatteryLevel(@RequestBody LoadedDroneMedication drone)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/drone/battery").toUriString());
        return ResponseEntity.created(uri).body(droneService.getDrone(drone.getDroneSn()));
    }

    @PostMapping("/drone/battery/auditlog")
    public ResponseEntity<List<DroneBatteryAuditLog>> getDroneAuditLog(@RequestBody LoadedDroneMedication drone)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/drone/battery/auditlog").toUriString());
        return ResponseEntity.created(uri).body(droneService.getDroneAuditLog(drone.getDroneSn()));
    }
    @PostMapping("/drone/battery/auditlog/proccesstype")
    public ResponseEntity<List<DroneBatteryAuditLog>> getDroneAuditLogProcessType(@RequestBody SaveLogRequest saveLogRequest )
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/drone/battery/auditlog/proccesstype").toUriString());
        return ResponseEntity.created(uri).body(droneService.getLogByDroneSnProcessType(saveLogRequest));
    }

    @GetMapping("/drones/loading/available")
    public ResponseEntity<List<Drone>> getAvailableDrone()
    {
        return ResponseEntity.ok().body(droneService.getAvailableDrone());
    }



    @PostMapping("/model/save")
    public ResponseEntity<Model> saveModel(@RequestBody Model model)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/model/save").toUriString());
        return ResponseEntity.created(uri).body(droneService.saveModel(model));
    }

    @PostMapping("/state/save")
    public ResponseEntity<State> saveState(@RequestBody State state)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/state/save").toUriString());
        return ResponseEntity.created(uri).body(droneService.saveState(state));
    }

    @PostMapping("/model/addtodrone")
    public ResponseEntity<Role> addModelToDrone(@RequestBody ModelToDroneForm form)
    {
        droneService.addDroneModel(form.getSn(), form.getModelName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/state/addtodrone")
    public ResponseEntity<Role> addStateToDrone(@RequestBody StateToDroneForm form)
    {
        droneService.addDroneModel(form.getSn(), form.getStateName());
        return ResponseEntity.ok().build();
    }
}

@Data
class StateToDroneForm
{
    private String sn;
    private String stateName;
}

@Data
class ModelToDroneForm
{
    private String sn;
    private String modelName;
}
