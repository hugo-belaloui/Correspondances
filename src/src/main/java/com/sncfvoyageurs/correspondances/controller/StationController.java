package com.sncfvoyageurs.correspondances.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
