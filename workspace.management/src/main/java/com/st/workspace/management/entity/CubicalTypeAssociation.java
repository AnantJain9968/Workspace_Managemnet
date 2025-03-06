package com.st.workspace.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "spam_cubical_type_association")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CubicalTypeAssociation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String jobGrade; // JT-1, JT-2, ..., JT-10
    private String cubicalType; // Enclosed room, Open cubical of 1 seat, Open cubical of 2 seats, Open cubical of 4 seats
}