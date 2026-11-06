package com.sncfvoyageurs.correspondances.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.sncfvoyageurs.correspondances.model.Fare;
import com.sncfvoyageurs.correspondances.model.SeatClass;
import com.sncfvoyageurs.correspondances.model.TravelerProfile;

public record FareDto(
		SeatClass seatClass,
		TravelerProfile profile,
		BigDecimal pricePerKmCents,
		LocalDate validFrom,
		LocalDate validTo) {

	public static FareDto from(Fare fare) {
		return new FareDto(
				fare.getSeatClass(),
				fare.getProfile(),
				fare.getPricePerKmCents(),
				fare.getValidFrom(),
				fare.getValidTo());
	}
}
