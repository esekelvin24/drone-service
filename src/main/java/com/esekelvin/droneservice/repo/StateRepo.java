package com.esekelvin.droneservice.repo;

import com.esekelvin.droneservice.models.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepo extends JpaRepository<State, Long> {

    State findByname(String name);
}
