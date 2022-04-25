package com.musalasoft.droneservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "drone_id", referencedColumnName = "id")
    private Drone drone;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "medication_id", referencedColumnName = "id")
    private Medication medication;

    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;




}


