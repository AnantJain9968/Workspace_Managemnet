package com.st.workspace.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "spam_cubical")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cubical {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cubicalId;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;

    private String cubicalType; // Enclosed room, Open cubical of 1 seat, Open cubical of 2 seats, Open cubical of 4 seats

    @OneToMany(mappedBy = "cubical", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seat> seats;
}