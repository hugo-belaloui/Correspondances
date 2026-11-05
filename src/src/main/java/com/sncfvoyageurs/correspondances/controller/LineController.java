package com.sncfvoyageurs.correspondances.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sncfvoyageurs.correspondances.dto.LineStopDto;
import com.sncfvoyageurs.correspondances.service.LineService;

@RestController
@RequestMapping("/lines")
public class LineController {

	private final LineService lineService;

	public LineController(LineService lineService) {
		this.lineService = lineService;
	}

	@GetMapping("/{lineNumber}/stops")
	public List<LineStopDto> findStops(@PathVariable UUID lineNumber) {
		return lineService.findStops(lineNumber);
	}
}
