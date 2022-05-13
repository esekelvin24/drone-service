package com.esekelvin.droneservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

    @Id @GeneratedValue(strategy = AUTO)
    private Long id;
    private String sn;
    private int weight;
    private int battery;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private State state;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    Model model;

    @JsonIgnore
    @OneToMany(mappedBy = "drone")
    private Set<Transaction> transactions = new HashSet<>();


}
