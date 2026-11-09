package com.sncfvoyageurs.correspondances.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sncfvoyageurs.correspondances.dto.ReservationDto;
import com.sncfvoyageurs.correspondances.repository.ReservationRepository;
import com.sncfvoyageurs.correspondances.repository.TravelerRepository;

@Service
@Transactional(readOnly = true)
public class TravelerService {

	private final TravelerRepository travelerRepository;
	private final ReservationRepository reservationRepository;

	public TravelerService(TravelerRepository travelerRepository, ReservationRepository reservationRepository) {
		this.travelerRepository = travelerRepository;
		this.reservationRepository = reservationRepository;
	}

	public List<ReservationDto> findReservations(UUID travelerId) {
		if (!travelerRepository.existsById(travelerId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Traveler not found");
		}
		return reservationRepository.findByTravelerTravelerIdOrderByDepartureDatetimeDesc(travelerId).stream()
				.map(ReservationDto::from)
				.toList();
	}
}
