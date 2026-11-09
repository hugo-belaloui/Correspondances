package com.sncfvoyageurs.correspondances.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "station")
public class Station {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "station_id")
	private UUID stationId;

	@Column(name = "name", nullable = false, length = 63)
	private String name;

	protected Station() {
	}

	public UUID getStationId() {
		return stationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
