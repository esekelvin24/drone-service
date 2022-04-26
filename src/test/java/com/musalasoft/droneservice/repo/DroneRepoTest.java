package com.musalasoft.droneservice.repo;

import com.musalasoft.droneservice.models.Drone;
import com.musalasoft.droneservice.models.Model;
import com.musalasoft.droneservice.models.State;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@ComponentScan("com.musalasoft.droneservice.service")
class DroneRepoTest {

    @Autowired
    private DroneRepo underTest;


    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private ModelRepo modelRepo;

    @BeforeEach
    void setUp()
    {

    }

    @Test
    void itShouldFindByDroneSn() {
        //given
        String sn = "596829321";
        State state = stateRepo.findByname("IDLE");
        Model model = modelRepo.findByName("Middleweight");
        Drone drone = new Drone(null,sn,200,100, state, model, null );
        underTest.save(drone);

        //when
        Drone searchedDrone = underTest.findBySn(sn);

        //then
        assertThat(searchedDrone.getSn()).isEqualTo(sn);


    }

    @Test
    void itShouldGetAvailableDrones() {

        //when
        List<Drone> searchedDrone = underTest.getAvailableDrone();

        //then
        assertTrue(searchedDrone.stream()
                .noneMatch(item -> "RETURNING".equals(item.getState().getName()) || "DELIVERED".equals(item.getState().getName()) || "DELIVERING".equals(item.getState().getName()) || "LOADED".equals(item.getState().getName()))

        );

    }

    @Test
    void itShouldSaveDroneBatteryPercentage() {

        //given
        String sn = "596829321";
        State state = stateRepo.findByname("IDLE");
        Model model = modelRepo.findByName("Middleweight");
        Drone drone = new Drone(null,sn,200,100, state, model, null );
        underTest.save(drone);

        //When
        underTest.saveDroneBatteryPerc(100, drone.getId());


        //then
        assertThat(underTest.findBySn(sn).getBattery()).isEqualTo(100);

    }
}