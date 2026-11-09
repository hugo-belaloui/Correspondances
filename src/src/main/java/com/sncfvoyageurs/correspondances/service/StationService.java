package com.sncfvoyageurs.correspondances.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sncfvoyageurs.correspondances.dto.LineDto;
import com.sncfvoyageurs.correspondances.dto.StationDto;
import com.sncfvoyageurs.correspondances.repository.LineRepository;
import com.sncfvoyageurs.correspondances.repository.StationRepository;

@Service
@Transactional(readOnly = true)
public class StationService {

	private final StationRepository stationRepository;
	private final LineRepository lineRepository;

	public StationService(StationRepository stationRepository, LineRepository lineRepository) {
		this.stationRepository = stationRepository;
		this.lineRepository = lineRepository;
	}

	public List<StationDto> findAll() {
		return stationRepository.findAllByOrderByNameAsc().stream()
				.map(StationDto::from)
				.toList();
	}

	public List<LineDto> findLinesServing(UUID stationId) {
		if (!stationRepository.existsById(stationId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found");
		}
		return lineRepository.findLinesServingStation(stationId).stream()
				.map(LineDto::from)
				.toList();
	}
}
