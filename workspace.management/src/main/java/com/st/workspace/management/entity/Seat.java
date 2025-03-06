package com.st.workspace.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "spam_seat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seatId;

    @ManyToOne
    @JoinColumn(name = "cubical_id")
    private Cubical cubical;

    private String seatNumber;
    private String seatAreaType; // Restricted, Unrestricted, Not Applicable
    private String seatStatus; // Vacant, In-use, Reserved
    private Date lastUpdated;
}
