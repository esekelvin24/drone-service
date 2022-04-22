package com.musalasoft.droneservice.service;

import com.musalasoft.droneservice.models.AppUser;
import com.musalasoft.droneservice.models.Role;
import com.musalasoft.droneservice.repo.AppUserRepo;
import com.musalasoft.droneservice.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepo appUserRepo;
    private final RoleRepo roleRepo;

    @Override
    public AppUser saveUser(AppUser appUser) {
        log.info("saving user {} to the database", appUser.getUsername());
        return appUserRepo.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("adding Role {} to User {}", username, roleName);
        AppUser appUser = appUserRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        appUser.getRoles().add(role);

    }

    @Override
    public AppUser getUser(String username) {
        log.info("fetching user {} details from the database", username);
        return appUserRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("fetching all users from the database");
        return appUserRepo.findAll();
    }
}
