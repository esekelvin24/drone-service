package com.esekelvin.droneservice.service;

import com.esekelvin.droneservice.models.AppUser;
import com.esekelvin.droneservice.models.Role;
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
