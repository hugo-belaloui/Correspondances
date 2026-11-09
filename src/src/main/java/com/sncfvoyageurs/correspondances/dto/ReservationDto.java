package com.sncfvoyageurs.correspondances.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sncfvoyageurs.correspondances.model.Reservation;
import com.sncfvoyageurs.correspondances.model.SeatClass;

public record ReservationDto(
		UUID reservationId,
		LocalDateTime departureDatetime,
		String departureStation,
		String arrivalStation,
		SeatClass seatClass,
		Integer priceCents,
		LocalDateTime reservedAt) {

	public static ReservationDto from(Reservation reservation) {
		return new ReservationDto(
				reservation.getReservationId(),
				reservation.getDepartureDatetime(),
				reservation.getDepartureStation().getName(),
				reservation.getArrivalStation().getName(),
				reservation.getSeat().getSeatClass(),
				reservation.getPriceCents(),
				reservation.getReservedAt());
	}
}
