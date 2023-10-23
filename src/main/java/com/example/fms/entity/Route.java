package com.example.fms.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_airport_id")
    private Airport source;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destination;
    private double distanceInMiles;
}
