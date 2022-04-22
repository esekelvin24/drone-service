package com.musalasoft.droneservice.repo;

import com.musalasoft.droneservice.models.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepo extends JpaRepository {

    State findByname(String name);
}
