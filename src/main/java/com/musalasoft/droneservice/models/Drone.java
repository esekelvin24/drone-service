package com.musalasoft.droneservice.models;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Data
public class Drone {

    @Id @GeneratedValue(strategy = AUTO)
    private String sn;
    private String weight;
    private int battery;

    @ManyToMany(fetch = EAGER)
    private Collection <Model> model = new ArrayList<>();

    @ManyToMany(fetch = EAGER)
    private Collection<State> states = new ArrayList<>();

}
