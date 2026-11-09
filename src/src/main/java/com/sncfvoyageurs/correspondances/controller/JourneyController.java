package com.sncfvoyageurs.correspondances.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sncfvoyageurs.correspondances.dto.JourneyDto;
import com.sncfvoyageurs.correspondances.service.JourneyService;

@RestController
@RequestMapping("/journeys")
public class JourneyController {

	private final JourneyService journeyService;

	public JourneyController(JourneyService journeyService) {
		this.journeyService = journeyService;
	}

	// Example: /journeys?lineNumber=...&date=2026-12-05
	@GetMapping
	public List<JourneyDto> findRunningOn(
			@RequestParam UUID lineNumber,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return journeyService.findRunningOn(lineNumber, date);
	}
}
