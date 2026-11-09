package com.sncfvoyageurs.correspondances.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sncfvoyageurs.correspondances.dto.ReservationDto;
import com.sncfvoyageurs.correspondances.service.TravelerService;

@RestController
@RequestMapping("/travelers")
public class TravelerController {

	private final TravelerService travelerService;

	public TravelerController(TravelerService travelerService) {
		this.travelerService = travelerService;
	}

	@GetMapping("/{travelerId}/reservations")
	public List<ReservationDto> findReservations(@PathVariable UUID travelerId) {
		return travelerService.findReservations(travelerId);
	}
}
