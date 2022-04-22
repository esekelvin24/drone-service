package com.musalasoft.droneservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "model")
    private Set<Drone> drones = new HashSet<>();


}
