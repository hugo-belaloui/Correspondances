package com.sncfvoyageurs.correspondances.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sncfvoyageurs.correspondances.model.Line;

public interface LineRepository extends JpaRepository<Line, UUID> {

	@Query("SELECT ls.line FROM LineStop ls WHERE ls.station.stationId = :stationId ORDER BY ls.line.name")
	List<Line> findLinesServingStation(@Param("stationId") UUID stationId);
}
