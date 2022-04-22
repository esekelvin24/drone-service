package com.musalasoft.droneservice.repo;

import com.musalasoft.droneservice.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

}
