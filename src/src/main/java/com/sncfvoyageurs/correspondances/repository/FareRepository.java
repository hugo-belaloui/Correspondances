package com.sncfvoyageurs.correspondances.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sncfvoyageurs.correspondances.model.Fare;

public interface FareRepository extends JpaRepository<Fare, UUID> {

	// validTo is excluded: a fare closed on 2026-01-01 is no longer valid that day
	@Query("""
			SELECT f FROM Fare f
			WHERE f.validFrom <= :date AND (f.validTo IS NULL OR f.validTo > :date)
			ORDER BY f.seatClass, f.profile""")
	List<Fare> findValidOn(@Param("date") LocalDate date);
}
