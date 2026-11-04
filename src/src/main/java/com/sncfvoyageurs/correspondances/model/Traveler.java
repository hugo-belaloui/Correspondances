package com.sncfvoyageurs.correspondances.model;

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
@Table(name = "traveler")
public class Traveler {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "traveler_id")
	private UUID travelerId;

	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.NAMED_ENUM)
	@Column(name = "profile", nullable = false)
	private TravelerProfile profile = TravelerProfile.FULL_PRICE;

	@Column(name = "first_name", nullable = false, length = 127)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 127)
	private String lastName;

	@Column(name = "email", nullable = false, length = 255)
	private String email;

	protected Traveler() {
	}

	public UUID getTravelerId() {
		return travelerId;
	}

	public TravelerProfile getProfile() {
		return profile;
	}

	public void setProfile(TravelerProfile profile) {
		this.profile = profile;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
