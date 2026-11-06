package com.sncfvoyageurs.correspondances.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sncfvoyageurs.correspondances.dto.FareDto;
import com.sncfvoyageurs.correspondances.service.FareService;

@RestController
@RequestMapping("/fares")
public class FareController {

	private final FareService fareService;

	public FareController(FareService fareService) {
		this.fareService = fareService;
	}

	@GetMapping
	public List<FareDto> findValidOn(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return fareService.findValidOn(date);
	}
}
