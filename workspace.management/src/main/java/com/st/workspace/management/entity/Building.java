package com.st.workspace.management.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "spam_building")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int buildingId;

    private String buildingName;

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;
}
