package com.musalasoft.droneservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static javax.persistence.FetchType.EAGER;
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


}
