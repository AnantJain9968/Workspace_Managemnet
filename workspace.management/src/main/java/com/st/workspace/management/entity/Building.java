package com.st.workspace.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "spam_building")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long buildingId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Floor> floors;
}