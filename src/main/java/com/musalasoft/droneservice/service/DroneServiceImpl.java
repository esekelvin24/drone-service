package com.musalasoft.droneservice.service;

import com.musalasoft.droneservice.models.Drone;
import com.musalasoft.droneservice.models.Model;
import com.musalasoft.droneservice.models.State;
import com.musalasoft.droneservice.repo.DroneRepo;
import com.musalasoft.droneservice.repo.ModelRepo;
import com.musalasoft.droneservice.repo.StateRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional @AllArgsConstructor @Slf4j
public class DroneServiceImpl implements DroneService {

    private final DroneRepo droneRepo;
    private final ModelRepo modelRepo;
    private final StateRepo stateRepo;

    @Override
    public Drone saveDrone(Drone drone) {
        log.info("saving new drone with SN {} to the database", drone.getSn());
        return droneRepo.save(drone);
    }

    @Override
    public Model saveModel(Model model) {
        log.info("saving new drone model ({}) to the database",model.getName());
        return modelRepo.save(model);
    }

    @Override
    public State saveState(State state) {
        log.info("saving new drone state ({}) to the database",state.getName());
        return stateRepo.save(state);
    }

    @Override
    public void addDroneModel(String sn, String modelName) {
         log.info("adding model {} to Drone Serial No {}", modelName, sn);
         Drone drone = droneRepo.findBySn(sn);
         Model model = modelRepo.findByName(modelName);
         drone.setModel(model);

    }

    @Override
    public void addDroneState(String sn, String stateName) {
           log.info("adding state {} to Drone Serial No {}", stateName, sn);
           Drone drone = droneRepo.findBySn(sn);
           State state = stateRepo.findByname(stateName);
           drone.setState(state);
    }

    @Override
    public Drone getDrone(String sn) {
        log.info("fetching details of drone {}", sn);
        return droneRepo.findBySn(sn);
    }

    @Override
    public State getState(String stateName) {
        return stateRepo.findByname(stateName);
    }

    @Override
    public List<State> getStates() {
        return stateRepo.findAll();
    }

    @Override
    public Model getModel(String modelName) {
        return modelRepo.findByName(modelName);
    }

    @Override
    public List<Model> getModels() {
        return modelRepo.findAll();
    }

    @Override
    public List<Drone> getDrones() {
        log.info("fetching all drone details");
        return droneRepo.findAll();
    }
}
