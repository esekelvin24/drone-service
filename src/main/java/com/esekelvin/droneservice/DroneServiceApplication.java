package com.esekelvin.droneservice;

import com.esekelvin.droneservice.models.AppUser;
import com.esekelvin.droneservice.models.Model;
import com.esekelvin.droneservice.models.Role;
import com.esekelvin.droneservice.models.State;
import com.esekelvin.droneservice.service.*;
import com.musalasoft.droneservice.models.*;
import com.esekelvin.droneservice.requests.CreateMedication;
import com.esekelvin.droneservice.requests.DroneRequest;
import com.esekelvin.droneservice.requests.LoadDroneRequest;
import com.musalasoft.droneservice.service.*;
import com.esekelvin.droneservice.utilities.DroneBatteryDrainer;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
@AllArgsConstructor
public class DroneServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(DroneServiceApplication.class, args);

	}

	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}


	@Bean
	CommandLineRunner run(AppUserService appUserService, DroneService droneService, MedicationService medicationService, TransactionService transactionService, DroneBatteryAuditService droneBatteryAuditService)
	{
		return args -> {



			//Saving Default Drone Model
			droneService.saveModel(new Model(null, "Lightweight", null ));
			droneService.saveModel(new Model(null, "Middleweight" , null));
			droneService.saveModel(new Model(null, "Cruiserweight" , null));
			droneService.saveModel(new Model(null, "Heavyweight" , null));

			//Populating the database with Default Drone State
			droneService.saveState(new State(null, "IDLE" , null));
			droneService.saveState(new State(null, "LOADING" , null));
			droneService.saveState(new State(null, "LOADED" , null));
			droneService.saveState(new State(null, "DELIVERING" , null));
			droneService.saveState(new State(null, "DELIVERED" , null));
			droneService.saveState(new State(null, "RETURNING" , null));

			//Adding a default drone
			State state = droneService.getState("IDLE");

			droneService.saveDrone(new DroneRequest("14921", 500, 100, "Heavyweight"));
			droneService.saveDrone(new DroneRequest("14928", 400, 100 , "Cruiserweight"));
			droneService.saveDrone(new DroneRequest("15928", 300, 100,"Middleweight"));
			droneService.saveDrone(new DroneRequest("16928", 250, 100 , "Lightweight"));


			//Adding Medication
			medicationService.saveMedication(new CreateMedication( "Medication For_Cough", "A4829113","https://www.assetpharmacy.com/wp-content/uploads/2019/08/D-KOFF-Cough-Expectorant-Syrup-100ml-1.jpg", 130));
			medicationService.saveMedication(new CreateMedication( "Medication for Blood Pressure", "5299113","https://www.sciencenews.org/wp-content/uploads/2020/04/042320_ac_covid-ace_feat.jpg", 500));
			medicationService.saveMedication(new CreateMedication( "Medication for Cancer", "5399115","https://pharmaceutical-journal.com/wp-content/uploads/2021/03/How-to-counsel-cancer-patients-about-their-oral-chemotherapy.jpg", 350));
			medicationService.saveMedication(new CreateMedication( "Medication for Body Pains", "4899115","https://pharmaceutical-journal.com/wp-content/uploads/2021/03/How-to-counsel-cancer-patients-about-their-oral-chemotherapy.jpg", 50));
			medicationService.saveMedication(new CreateMedication( "Medication for Ulcer", "9899115","https://pharmaceutical-journal.com/wp-content/uploads/2021/03/How-to-counsel-cancer-patients-about-their-oral-chemotherapy.jpg", 120 ));

            //Add Medication to drone

			transactionService.loadDrone(new LoadDroneRequest( "14921", "5399115"));



			//Populating the database with Application Roles
			appUserService.saveRole(new Role(null, "ROLE_USER"));
			appUserService.saveRole(new Role(null, "ROLE_ADMIN"));
			appUserService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			appUserService.saveRole(new Role(null, "ROLE_MANAGER"));

			//Creating Sample Super Admin User
			appUserService.saveUser(new AppUser(null, "Ese Kelvin Uvbiekpahor","ese" , "123456", new ArrayList<>()));
			appUserService.addRoleToUser("ese","ROLE_SUPER_ADMIN");
			appUserService.addRoleToUser("ese","ROLE_ADMIN");

			//Creating Sample Test User
			appUserService.saveUser(new AppUser(null, "Test User","user" , "123456", new ArrayList<>()));
			appUserService.addRoleToUser("user","ROLE_USER");

			//Creating Sample Admin User
			appUserService.saveUser(new AppUser(null, "Test Admin","admin" , "123456", new ArrayList<>()));
			appUserService.addRoleToUser("admin","ROLE_ADMIN");


            //Run a battery drainer that will drain the drone battery
			Timer timer = new Timer();
			TimerTask task = new DroneBatteryDrainer(droneService, droneBatteryAuditService);

			timer.scheduleAtFixedRate(task , 0l, 1 * (60*1000)); // Runs every 2 mins
		};
	}

}
