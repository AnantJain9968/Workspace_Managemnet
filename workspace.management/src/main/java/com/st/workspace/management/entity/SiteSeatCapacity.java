package com.st.workspace.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "spam_site_seat_capacity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteSeatCapacity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String building;
    private String floor;
    private Integer totalSeats;
    private Integer enclosedRoom;
    private Integer openCubicalOf1Seat;
    private Integer openCubicalOf2Seats;
    private Integer openCubicalOf4Seats;
}
