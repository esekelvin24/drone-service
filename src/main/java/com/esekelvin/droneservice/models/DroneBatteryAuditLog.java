package com.esekelvin.droneservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneBatteryAuditLog {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long Id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "drone_id", referencedColumnName = "id")
    private Drone drone;

    @Enumerated(EnumType.STRING)
    private DroneBatterProcessType processType;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "drone_state_id", referencedColumnName = "id")
    private State state;

    private int previousBatteryPercentage;
    private int currentBatteryPercentage;
    private Date timeStamp;


}
