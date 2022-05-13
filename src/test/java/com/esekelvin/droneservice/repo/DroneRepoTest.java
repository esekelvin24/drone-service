package com.esekelvin.droneservice.repo;

import com.esekelvin.droneservice.models.Model;
import com.esekelvin.droneservice.models.Drone;
import com.esekelvin.droneservice.models.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

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