package com.sncfvoyageurs.correspondances.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "line")
public class Line {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "line_number")
	private UUID lineNumber;

	@Column(name = "name", nullable = false, length = 63)
	private String name;

	protected Line() {
	}

	public UUID getLineNumber() {
		return lineNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
