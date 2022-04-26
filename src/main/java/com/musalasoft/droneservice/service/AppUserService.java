package com.musalasoft.droneservice.service;

import com.musalasoft.droneservice.models.AppUser;
import com.musalasoft.droneservice.models.Role;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AppUserService {

    AppUser saveUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
