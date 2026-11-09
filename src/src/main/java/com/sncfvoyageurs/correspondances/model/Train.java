package com.sncfvoyageurs.correspondances.model;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "train")
public class Train {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "train_number")
	private UUID trainNumber;

	@Column(name = "avg_speed_kmh", nullable = false, precision = 5, scale = 2)
	private BigDecimal avgSpeedKmh;

	protected Train() {
	}

	public UUID getTrainNumber() {
		return trainNumber;
	}

	public BigDecimal getAvgSpeedKmh() {
		return avgSpeedKmh;
	}

	public void setAvgSpeedKmh(BigDecimal avgSpeedKmh) {
		this.avgSpeedKmh = avgSpeedKmh;
	}
}
