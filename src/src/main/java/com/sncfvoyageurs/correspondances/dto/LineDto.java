package com.sncfvoyageurs.correspondances.dto;

import java.util.UUID;

import com.sncfvoyageurs.correspondances.model.Line;

public record LineDto(UUID lineNumber, String name) {

	public static LineDto from(Line line) {
		return new LineDto(line.getLineNumber(), line.getName());
	}
}
