package com.st.workspace.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "spam_floor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "floor_id", nullable = false, updatable = false)
    private Long floorId;

    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cubical> cubicals;
}