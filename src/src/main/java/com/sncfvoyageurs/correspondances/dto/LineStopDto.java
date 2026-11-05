package com.sncfvoyageurs.correspondances.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.sncfvoyageurs.correspondances.model.LineStop;

public record LineStopDto(Short positionOnLine, UUID stationId, String stationName, BigDecimal distanceFromNext) {

	public static LineStopDto from(LineStop stop) {
		return new LineStopDto(
				stop.getPositionOnLine(),
				stop.getStation().getStationId(),
				stop.getStation().getName(),
				stop.getDistanceFromNext());
	}
}
