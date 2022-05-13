package com.esekelvin.droneservice.service;

import com.esekelvin.droneservice.models.Drone;
import com.esekelvin.droneservice.models.Medication;
import com.esekelvin.droneservice.models.Transaction;
import com.esekelvin.droneservice.models.TransactionStatus;
import com.esekelvin.droneservice.repo.DroneRepo;
import com.esekelvin.droneservice.repo.TransactionRepo;
import com.esekelvin.droneservice.exception.ApiRequestException;
import com.musalasoft.droneservice.models.*;
import com.esekelvin.droneservice.repo.MedicationRepo;
import com.esekelvin.droneservice.repo.StateRepo;
import com.esekelvin.droneservice.requests.LoadDroneRequest;
import com.esekelvin.droneservice.requests.LoadedDroneMedication;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepo transactionRepo;
    private final DroneRepo droneRepo;
    private final MedicationRepo medicationRepo;
    private final StateRepo stateRepo;


    @Override
    public Transaction loadDrone(LoadDroneRequest loadDroneRequest) {
        Drone drone = droneRepo.findBySn(loadDroneRequest.getDroneSn());
        Medication medication = medicationRepo.findByCode(loadDroneRequest.getMedicationCode());

        //checking if drone exists
        if (drone == null)
        {
            throw new ApiRequestException("This drone with serial number "+loadDroneRequest.getDroneSn()+" does not exit");
        }

        //Change the drone to IDLE if battery is below or equal 25%
        if(drone.getBattery() <=25)
        {
            drone.setState(stateRepo.findByname("IDLE"));
            throw new ApiRequestException("Drone with serial number "+loadDroneRequest.getDroneSn()+" Battery is low, please charge");
        }

        //Check if the drone is loaded already
        if(drone.getState().getName().equals("LOADED"))
        {
            throw new ApiRequestException("This drone with serial number "+loadDroneRequest.getDroneSn()+" is loaded, please select a different drone");
        }

        //checking if the medication about to be loaded exists
        if (medication == null)
        {
            throw new ApiRequestException("This medication with code number "+loadDroneRequest.getMedicationCode()+" does not exit");
        }

        //checking if this medication item has been loaded before
        Transaction transactionExist = this.getTransaction(drone.getId(), medication.getId());

        if(transactionExist !=null)
        {
            throw new ApiRequestException("This medication has already been loaded on this drone");
        }

        //get loaded drone medications items
        List<Transaction> loadedDroneItems = transactionRepo.checkLoadedMedication(drone.getId(), "OPEN");

        //get the sum of the loaded item weight for the selected drone
        int totalLoadedItemWeight = loadedDroneItems.stream().filter(o -> o.getMedication().getWeight() > 10).mapToInt(o -> o.getMedication().getWeight()).sum();

        //preventing the drone from loading with more weight when the total weight already exceeds the drone weight limit
        if(drone.getWeight() < (medication.getWeight() + totalLoadedItemWeight))
        {
            //Change Drone State from Loading to Loaded
            drone.setState(stateRepo.findByname("LOADED"));
            throw new ApiRequestException("Could not load medication because drone ("+drone.getSn()+") has exceeded the maximum weight limit");
        }

        //Change Drone State from IDLE to loading
        if(drone.getState().getName().equals("IDLE"))
        {
            drone.setState(stateRepo.findByname("LOADING"));
        }


        return transactionRepo.save(new Transaction(null, drone, medication, new Date(), TransactionStatus.OPEN));
    }

    @Override
    public Transaction getTransaction(String transactionId) {
        return transactionRepo.findById(transactionId);
    }

    @Override
    public Transaction getTransaction(Long droneId, Long medicationId) {
        return transactionRepo.findByCodeSn(droneId,medicationId);
    }

    @Override
    public List<Transaction> checkLoadedMedication(LoadedDroneMedication loadedDroneMedication) {


        Drone drone = droneRepo.findBySn(loadedDroneMedication.getDroneSn());

        //checking if drone exists
        if (drone == null)
        {
            throw new ApiRequestException("This drone with serial number "+drone.getSn()+" does not exit");
        }
        return transactionRepo.checkLoadedMedication(drone.getId(), loadedDroneMedication.getTransactionStatus());
    }


    @Override
    public List<Transaction> getTransactions() {
        return transactionRepo.findAll();
    }
}
