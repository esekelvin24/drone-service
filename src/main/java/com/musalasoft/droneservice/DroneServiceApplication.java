package com.musalasoft.droneservice;

import com.musalasoft.droneservice.models.*;
import com.musalasoft.droneservice.service.AppUserService;
import com.musalasoft.droneservice.service.DroneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
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
	CommandLineRunner run(AppUserService appUserService, DroneService droneService)
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
			Model model = droneService.getModel("Heavyweight");
			droneService.saveDrone(new Drone(null,"1", 500, 100,state , model));




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
		};
	}

}
