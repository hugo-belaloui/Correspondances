package com.sncfvoyageurs.correspondances.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.sncfvoyageurs.correspondances.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

@Query("""
        SELECT r FROM Reservation r
        JOIN FETCH r.departureStation
        JOIN FETCH r.arrivalStation
        JOIN FETCH r.seat
        WHERE r.traveler.travelerId = :travelerId
        ORDER BY r.departureDatetime DESC
    """)

List<Reservation> findByTravelerTravelerIdOrderByDepartureDatetimeDesc(@Param("travelerId") UUID travelerId);
}
