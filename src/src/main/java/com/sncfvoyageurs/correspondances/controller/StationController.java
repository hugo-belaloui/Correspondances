package com.sncfvoyageurs.correspondances.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sncfvoyageurs.correspondances.dto.LineDto;
import com.sncfvoyageurs.correspondances.dto.StationDto;
import com.sncfvoyageurs.correspondances.service.StationService;

@RestController
@RequestMapping("/stations")
public class StationController {

	private final StationService stationService;

	public StationController(StationService stationService) {
		this.stationService = stationService;
	}

	@GetMapping
	public List<StationDto> findAll() {
		return stationService.findAll();
	}

	@GetMapping("/{stationId}/lines")
	public List<LineDto> findLines(@PathVariable UUID stationId) {
		return stationService.findLinesServing(stationId);
	}
}
