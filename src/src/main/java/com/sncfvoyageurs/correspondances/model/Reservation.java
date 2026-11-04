package com.sncfvoyageurs.correspondances.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "reservation_id")
	private UUID reservationId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "journey", nullable = false)
	private Journey journey;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "traveler", nullable = false)
	private Traveler traveler;

	@Column(name = "price_cents", nullable = false)
	private Integer priceCents;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "departure_station", nullable = false)
	private Station departureStation;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "arrival_station", nullable = false)
	private Station arrivalStation;

	@CreationTimestamp
	@Column(name = "reserved_at", nullable = false, updatable = false)
	private LocalDateTime reservedAt;

	@Column(name = "travel_time_minutes", nullable = false)
	private Integer travelTimeMinutes;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "seat", nullable = false)
	private Seat seat;

	@Column(name = "departure_datetime", nullable = false)
	private LocalDateTime departureDatetime;

	protected Reservation() {
	}

	public UUID getReservationId() {
		return reservationId;
	}

	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}

	public Traveler getTraveler() {
		return traveler;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}

	public Integer getPriceCents() {
		return priceCents;
	}

	public void setPriceCents(Integer priceCents) {
		this.priceCents = priceCents;
	}

	public Station getDepartureStation() {
		return departureStation;
	}

	public void setDepartureStation(Station departureStation) {
		this.departureStation = departureStation;
	}

	public Station getArrivalStation() {
		return arrivalStation;
	}

	public void setArrivalStation(Station arrivalStation) {
		this.arrivalStation = arrivalStation;
	}

	public LocalDateTime getReservedAt() {
		return reservedAt;
	}

	public Integer getTravelTimeMinutes() {
		return travelTimeMinutes;
	}

	public void setTravelTimeMinutes(Integer travelTimeMinutes) {
		this.travelTimeMinutes = travelTimeMinutes;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public LocalDateTime getDepartureDatetime() {
		return departureDatetime;
	}

	public void setDepartureDatetime(LocalDateTime departureDatetime) {
		this.departureDatetime = departureDatetime;
	}
}
