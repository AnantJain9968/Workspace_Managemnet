package com.st.workspace.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "spam_seat_allocation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long allocationId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
    
    @ManyToOne
    @JoinColumn(name = "sub_department_id")
    private SubDepartment subDepartment;

    private Date allocationDate;
    private String status; // Allocated, Vacant, Reserved
}