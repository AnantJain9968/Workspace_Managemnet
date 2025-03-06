package com.st.workspace.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.st.workspace.management.entity.Floor;
import com.st.workspace.management.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.cubical.cubicalRow.floor = :floor AND s.seatStatus = :seatStatus")
    List<Seat> findByFloorAndSeatStatus(@Param("floor") Floor floor, @Param("seatStatus") String seatStatus);

    @Query("SELECT COUNT(s) FROM Seat s WHERE s.cubical.cubicalRow.floor = :floor AND s.seatStatus = :seatStatus")
    int countByFloorAndSeatStatus(@Param("floor") Floor floor, @Param("seatStatus") String seatStatus);
}
