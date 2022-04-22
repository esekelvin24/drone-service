package com.musalasoft.droneservice.repo;

import com.musalasoft.droneservice.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepo extends JpaRepository<Model, Long> {

    Model findByName(String name);
}
