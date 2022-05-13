package com.esekelvin.droneservice.repo;

import com.esekelvin.droneservice.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepo extends JpaRepository<Model, Long> {

    Model findByName(String name);
}
