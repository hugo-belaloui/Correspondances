package com.sncfvoyageurs.correspondances.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fare")
public class Fare {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "fare_id")
	private UUID fareId;

	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.NAMED_ENUM)
	@Column(name = "seat_class", nullable = false)
	private SeatClass seatClass;

	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.NAMED_ENUM)
	@Column(name = "profile", nullable = false)
	private TravelerProfile profile;

	@Column(name = "price_per_km_cents", precision = 6, scale = 2)
	private BigDecimal pricePerKmCents;

	@Column(name = "valid_from", nullable = false)
	private LocalDate validFrom;

	@Column(name = "valid_to")
	private LocalDate validTo;

	protected Fare() {
	}

	public UUID getFareId() {
		return fareId;
	}

	public SeatClass getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(SeatClass seatClass) {
		this.seatClass = seatClass;
	}

	public TravelerProfile getProfile() {
		return profile;
	}

	public void setProfile(TravelerProfile profile) {
		this.profile = profile;
	}

	public BigDecimal getPricePerKmCents() {
		return pricePerKmCents;
	}

	public void setPricePerKmCents(BigDecimal pricePerKmCents) {
		this.pricePerKmCents = pricePerKmCents;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDate getValidTo() {
		return validTo;
	}

	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}
}
