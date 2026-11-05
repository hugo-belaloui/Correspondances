package com.sncfvoyageurs.correspondances.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sncfvoyageurs.correspondances.dto.LineStopDto;
import com.sncfvoyageurs.correspondances.repository.LineRepository;
import com.sncfvoyageurs.correspondances.repository.LineStopRepository;

@Service
@Transactional(readOnly = true)
public class LineService {

	private final LineRepository lineRepository;
	private final LineStopRepository lineStopRepository;

	public LineService(LineRepository lineRepository, LineStopRepository lineStopRepository) {
		this.lineRepository = lineRepository;
		this.lineStopRepository = lineStopRepository;
	}

	public List<LineStopDto> findStops(UUID lineNumber) {
		if (!lineRepository.existsById(lineNumber)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Line not found");
		}
		return lineStopRepository.findByLineLineNumberOrderByPositionOnLine(lineNumber).stream()
				.map(LineStopDto::from)
				.toList();
	}
}
