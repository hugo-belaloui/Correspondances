package com.sncfvoyageurs.correspondances.model;

import java.util.UUID;

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
@Table(name = "wagon")
public class Wagon {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "wagon_number")
	private UUID wagonNumber;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "train", nullable = false)
	private Train train;

	protected Wagon() {
	}

	public UUID getWagonNumber() {
		return wagonNumber;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}
}
