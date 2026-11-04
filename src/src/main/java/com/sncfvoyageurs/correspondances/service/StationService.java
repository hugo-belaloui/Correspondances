package com.sncfvoyageurs.correspondances.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sncfvoyageurs.correspondances.dto.StationDto;
import com.sncfvoyageurs.correspondances.repository.StationRepository;

@Service
@Transactional(readOnly = true)
public class StationService {

	private final StationRepository stationRepository;

	public StationService(StationRepository stationRepository) {
		this.stationRepository = stationRepository;
	}

	public List<StationDto> findAll() {
		return stationRepository.findAllByOrderByNameAsc().stream()
				.map(StationDto::from)
				.toList();
	}
}
