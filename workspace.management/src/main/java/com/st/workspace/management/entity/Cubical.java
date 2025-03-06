package com.st.workspace.management.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spam_cubical")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cubicalId")
public class Cubical {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cubicalId;

    @ManyToOne
    @JoinColumn(name = "row_id")
    private CubicalRow cubicalRow;

    private String cubicalType; // Enclosed room, Open cubical of 1 seat, Open cubical of 2 seats, Open cubical of 4 seats

    @OneToMany(mappedBy = "cubical", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seat> seats;
}