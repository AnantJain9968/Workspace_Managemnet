package com.st.workspace.management.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.workspace.management.entity.Building;
import com.st.workspace.management.entity.Cubical;
import com.st.workspace.management.entity.CubicalRow;
import com.st.workspace.management.entity.Floor;
import com.st.workspace.management.entity.Seat;
import com.st.workspace.management.entity.Site;
import com.st.workspace.management.entity.SiteSeatCapacity;
import com.st.workspace.management.repository.BuildingRepository;
import com.st.workspace.management.repository.CubicalRepository;
import com.st.workspace.management.repository.CubicalRowRepository;
import com.st.workspace.management.repository.FloorRepository;
import com.st.workspace.management.repository.SeatRepository;
import com.st.workspace.management.repository.SiteRepository;
import com.st.workspace.management.repository.SiteSeatCapacityRepository;

@Service
public class SiteSeatCapacityService {
    @Autowired
    private SiteSeatCapacityRepository siteSeatCapacityRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private CubicalRowRepository cubicalRowRepository;

    @Autowired
    private CubicalRepository cubicalRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Value("${rowsize}")
    private int rowSize;

    public List<SiteSeatCapacity> getAllSiteSeatCapacities() {
        return siteSeatCapacityRepository.findAll();
    }

    public SiteSeatCapacity saveSiteSeatCapacity(SiteSeatCapacity siteSeatCapacity) {
        return siteSeatCapacityRepository.save(siteSeatCapacity);
    }

    public List<SiteSeatCapacity> saveAllSiteSeatCapacities(List<SiteSeatCapacity> siteSeatCapacities) {
        return siteSeatCapacityRepository.saveAll(siteSeatCapacities);
    }

    @Transactional
    public void populateBuildingData() {
        List<SiteSeatCapacity> capacities = getAllSiteSeatCapacities();

        // Create or find the site
        Site site = siteRepository.findByNameAndLocation("ST", "Greater Noida");
        if (site == null) {
            site = new Site();
            site.setName("ST");
            site.setLocation("Greater Noida");
            site = siteRepository.save(site);
        }

        for (SiteSeatCapacity capacity : capacities) {
            // Create or find the building
            Building building = buildingRepository.findByNameAndSite(capacity.getBuilding(), site);
            if (building == null) {
                building = new Building();
                building.setName(capacity.getBuilding());
                building.setSite(site);
                building = buildingRepository.save(building);
            }

            // Create the floor
            Floor floor = new Floor();
            floor.setBuilding(building);
            floor.setTotalSeats(capacity.getTotalSeats());
            floor.setStartingSeatNum(1); // Initialize starting seat number
            floor.setName(capacity.getFloor());
            floor = floorRepository.save(floor);

            // Initialize seat number counter for the floor
            int seatNumberCounter = floor.getStartingSeatNum();

            // Create cubical rows
            List<CubicalRow> cubicalRows = createCubicalRows(floor);

            // Distribute cubicals and seats across rows
            seatNumberCounter = distributeCubicalsAndSeats(cubicalRows, "ENCLOSED", capacity.getEnclosedRoom(), 1, seatNumberCounter);
            seatNumberCounter = distributeCubicalsAndSeats(cubicalRows, "ONE", capacity.getOpenCubicalOf1Seat(), 1, seatNumberCounter);
            seatNumberCounter = distributeCubicalsAndSeats(cubicalRows, "TWO", (int) Math.ceil((double) capacity.getOpenCubicalOf2Seats() / 2), 2, seatNumberCounter);
            seatNumberCounter = distributeCubicalsAndSeats(cubicalRows, "FOUR", (int) Math.ceil((double) capacity.getOpenCubicalOf4Seats() / 4), 4, seatNumberCounter);
        }
    }

    private List<CubicalRow> createCubicalRows(Floor floor) {
        List<CubicalRow> cubicalRows = new ArrayList<>();
        for (int i = 0; i < rowSize; i++) {
            CubicalRow cubicalRow = new CubicalRow();
            cubicalRow.setFloor(floor);
            cubicalRow = cubicalRowRepository.save(cubicalRow);
            cubicalRows.add(cubicalRow);
        }
        return cubicalRows;
    }

    private int distributeCubicalsAndSeats(List<CubicalRow> cubicalRows, String cubicalType, Integer cubicalCount, int seatsPerCubical, int seatNumberCounter) {
        if (cubicalCount != null && cubicalCount > 0) {
            int rowIndex = 0;
            while (cubicalCount > 0) {
                CubicalRow cubicalRow = cubicalRows.get(rowIndex);
                Cubical cubical = new Cubical();
                cubical.setCubicalRow(cubicalRow);
                cubical.setCubicalType(cubicalType);
                cubical = cubicalRepository.save(cubical);

                for (int j = 0; j < seatsPerCubical; j++) {
                    Seat seat = new Seat();
                    seat.setCubical(cubical);
                    seat.setSeatNumber(String.valueOf(seatNumberCounter++));
                    seat.setSeatAreaType("Unrestricted");
                    seat.setSeatStatus("Vacant");
                    seat.setLastUpdated(new Date());
                    seatRepository.save(seat);
                }

                cubicalCount--;
                rowIndex = (rowIndex + 1) % rowSize;
            }
        }
        return seatNumberCounter;
    }

    public List<Floor> getAllFloorsWithRows() {
        List<Floor> floors = floorRepository.findAll();
        for (Floor floor : floors) {
            List<CubicalRow> cubicalRows = floor.getCubicalRows();
            for (CubicalRow cubicalRow : cubicalRows) {
                List<Cubical> cubicals = cubicalRow.getCubicals();
                cubicalRow.setCubicals(cubicals);
            }
            floor.setCubicalRows(cubicalRows);
        }
        return floors;
    }
}