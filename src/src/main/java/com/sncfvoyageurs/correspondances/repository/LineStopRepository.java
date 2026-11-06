package com.sncfvoyageurs.correspondances.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sncfvoyageurs.correspondances.model.LineStop;

public interface LineStopRepository extends JpaRepository<LineStop, UUID> {

	// Station is loaded in the same query, otherwise one extra query per stop
	@EntityGraph(attributePaths = "station")
	List<LineStop> findByLineLineNumberOrderByPositionOnLine(UUID lineNumber);
}
