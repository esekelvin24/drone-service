package com.esekelvin.droneservice.models;

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
@AllArgsConstructor
@NoArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private int weight;
    private String code;
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "medication")
    private Set<Transaction> transactions = new HashSet<>();


}
