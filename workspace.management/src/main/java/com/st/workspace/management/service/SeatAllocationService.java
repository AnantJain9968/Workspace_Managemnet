package com.st.workspace.management.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.workspace.management.entity.Building;
import com.st.workspace.management.entity.CubicalTypeAssociation;
import com.st.workspace.management.entity.Department;
import com.st.workspace.management.entity.Floor;
import com.st.workspace.management.entity.JobGrade;
import com.st.workspace.management.entity.Seat;
import com.st.workspace.management.entity.SeatAllocation;
import com.st.workspace.management.entity.SubDepartment;
import com.st.workspace.management.repository.BuildingRepository;
import com.st.workspace.management.repository.CubicalTypeAssociationRepository;
import com.st.workspace.management.repository.DepartmentRepository;
import com.st.workspace.management.repository.FloorRepository;
import com.st.workspace.management.repository.JobGradeRepository;
import com.st.workspace.management.repository.SeatAllocationRepository;
import com.st.workspace.management.repository.SeatRepository;
import com.st.workspace.management.repository.SubDepartmentRepository;

@Service
public class SeatAllocationService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SubDepartmentRepository subDepartmentRepository;

    @Autowired
    private JobGradeRepository jobGradeRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatAllocationRepository seatAllocationRepository;

    @Autowired
    private CubicalTypeAssociationRepository cubicalTypeAssociationRepository;

    public void allocateSeats() {
        List<Department> departments = departmentRepository.findAll();
        List<Building> buildings = buildingRepository.findAll();

        // Sort departments by count of job grades with "Unallocated" status in descending order
        departments.sort((d1, d2) -> Integer.compare(
                d2.getSubDepartments().stream()
                        .flatMap(subDepartment -> subDepartment.getJobGrades().stream())
                        .filter(jobGrade -> "Unallocated".equals(jobGrade.getStatus()))
                        .mapToInt(JobGrade::getHeadCount).sum(),
                d1.getSubDepartments().stream()
                        .flatMap(subDepartment -> subDepartment.getJobGrades().stream())
                        .filter(jobGrade -> "Unallocated".equals(jobGrade.getStatus()))
                        .mapToInt(JobGrade::getHeadCount).sum()
        ));

        // Sort buildings by number of vacant seats in descending order
        buildings.sort((b1, b2) -> Integer.compare(getTotalVacantSeats(b2), getTotalVacantSeats(b1)));

        for (Department department : departments) {
            allocateDepartmentToBuildings(department, buildings);
        }
    }

    private int getTotalVacantSeats(Building building) {
        return building.getFloors().stream()
                .mapToInt(floor -> seatRepository.countByFloorAndSeatStatus(floor, "Vacant"))
                .sum();
    }

    private void allocateDepartmentToBuildings(Department department, List<Building> buildings) {
        for (Building building : buildings) {
            List<Floor> floors = floorRepository.findByBuilding(building);
            for (Floor floor : floors) {
                allocateSubDepartmentsToFloor(department, floor);
            }
        }
    }

    private void allocateSubDepartmentsToFloor(Department department, Floor floor) {
        List<Seat> availableSeats = seatRepository.findByFloorAndSeatStatus(floor, "Vacant");

        if (availableSeats.isEmpty()) {
            return;
        }

        for (SubDepartment subDepartment : department.getSubDepartments()) {
            allocateJobGradesToSeats(subDepartment, availableSeats);
        }
    }

    private void allocateJobGradesToSeats(SubDepartment subDepartment, List<Seat> availableSeats) {
        for (JobGrade jobGrade : subDepartment.getJobGrades()) {
            if ("Unallocated".equals(jobGrade.getStatus())) {
                allocateSeatsForJobGrade(subDepartment, jobGrade, availableSeats);
            }
        }
    }

    private void allocateSeatsForJobGrade(SubDepartment subDepartment, JobGrade jobGrade, List<Seat> availableSeats) {
        int headCount = jobGrade.getHeadCount();
        String grade = jobGrade.getGrade();

        for (int i = 0; i < headCount; i++) {
            Seat seat = findOptimalSeatForJobGrade(grade, availableSeats);
            if (seat == null) {
                return;
            }

            SeatAllocation allocation = new SeatAllocation();
            allocation.setSeat(seat);
            allocation.setSubDepartment(subDepartment);
            allocation.setAllocationDate(new Date());
            allocation.setStatus("Reserved");
            seatAllocationRepository.save(allocation);

            seat.setSeatStatus("Reserved");
            seatRepository.save(seat);
        }

        jobGrade.setStatus("Reserved");
        jobGradeRepository.save(jobGrade);
    }

    private Seat findOptimalSeatForJobGrade(String grade, List<Seat> availableSeats) {
        List<CubicalTypeAssociation> associations = cubicalTypeAssociationRepository.findByJobGrade(grade);
        List<String> allowedCubicalTypes = associations.stream()
                .map(CubicalTypeAssociation::getCubicalType)
                .collect(Collectors.toList());

        return availableSeats.stream()
                .filter(seat -> allowedCubicalTypes.contains(seat.getCubical().getCubicalType()))
                .filter(seat -> "Vacant".equals(seat.getSeatStatus()))
                .findFirst()
                .orElse(null);
    }

    public void allocateNewDepartment(Department department) {
        List<Building> buildings = buildingRepository.findAll();

        // Sort buildings by number of vacant seats in descending order
        buildings.sort((b1, b2) -> Integer.compare(getTotalVacantSeats(b2), getTotalVacantSeats(b1)));

        allocateDepartmentToBuildings(department, buildings);
    }

    public List<SeatAllocation> getDepartmentAllocationStatus(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department == null) {
            return null;
        }

        return department.getSubDepartments().stream()
                .flatMap(subDepartment -> seatAllocationRepository.findBySubDepartment(subDepartment).stream())
                .collect(Collectors.toList());
    }
}