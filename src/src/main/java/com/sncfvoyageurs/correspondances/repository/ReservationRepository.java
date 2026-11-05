package com.sncfvoyageurs.correspondances.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sncfvoyageurs.correspondances.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

	List<Reservation> findByTravelerTravelerIdOrderByDepartureDatetimeDesc(UUID travelerId);
}
