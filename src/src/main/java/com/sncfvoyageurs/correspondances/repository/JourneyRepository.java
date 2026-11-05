package com.sncfvoyageurs.correspondances.repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sncfvoyageurs.correspondances.model.Journey;
import com.sncfvoyageurs.correspondances.model.JourneySchedule;

public interface JourneyRepository extends JpaRepository<Journey, UUID> {

	@Query("SELECT j FROM Journey j WHERE j.line.lineNumber = :lineNumber AND j.schedule IN :schedules ORDER BY j.departureTime")
	List<Journey> findByLineAndSchedules(
			@Param("lineNumber") UUID lineNumber,
			@Param("schedules") Collection<JourneySchedule> schedules);
}
