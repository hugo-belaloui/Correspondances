package com.sncfvoyageurs.correspondances.dto;

import java.util.UUID;

import com.sncfvoyageurs.correspondances.model.Station;

public record StationDto(UUID stationId, String name) {

	public static StationDto from(Station station) {
		return new StationDto(station.getStationId(), station.getName());
	}
}
