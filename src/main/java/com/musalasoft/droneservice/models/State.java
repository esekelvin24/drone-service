package com.musalasoft.droneservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class State {

    @Id @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "state")
    private Set<Drone> drones = new HashSet<>();

}
