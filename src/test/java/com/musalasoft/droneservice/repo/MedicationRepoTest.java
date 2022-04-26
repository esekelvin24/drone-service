package com.musalasoft.droneservice.repo;

import com.musalasoft.droneservice.models.Medication;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan("com.musalasoft.droneservice.service")
class MedicationRepoTest {

    @Autowired
    MedicationRepo underTest;

    @Test
    void itShouldFindByMedicationCode() {
        //given
        String medicationCode = "593848";
        Medication medication = new Medication(null, "Medication for Ulcer", 120, medicationCode,"https://pharmaceutical-journal.com/wp-content/uploads/2021/03/How-to-counsel-cancer-patients-about-their-oral-chemotherapy.jpg", null);
        underTest.save(medication);

        //when
        Medication searched = underTest.findByCode(medicationCode);

        //then
        assertThat(searched.getName()).isEqualTo(medication.getName());

    }
}