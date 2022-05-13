package com.esekelvin.droneservice.repo;

import com.esekelvin.droneservice.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

}
