package com.sncfvoyageurs.correspondances.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sncfvoyageurs.correspondances.model.Journey;
import com.sncfvoyageurs.correspondances.model.JourneySchedule;

public record JourneyDto(
		UUID journeyNumber,
		UUID trainNumber,
		LocalDateTime departureTime,
		Boolean defaultDirection,
		Boolean isDirect,
		JourneySchedule schedule) {

	// Reading the id of a lazy proxy does not trigger a query
	public static JourneyDto from(Journey journey) {
		return new JourneyDto(
				journey.getJourneyNumber(),
				journey.getTrain().getTrainNumber(),
				journey.getDepartureTime(),
				journey.getDefaultDirection(),
				journey.getIsDirect(),
				journey.getSchedule());
	}
}
