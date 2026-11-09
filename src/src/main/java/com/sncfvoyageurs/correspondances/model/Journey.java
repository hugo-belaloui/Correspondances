package com.sncfvoyageurs.correspondances.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "journey")
public class Journey {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "journey_number")
	private UUID journeyNumber;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "train", nullable = false)
	private Train train;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "line", nullable = false)
	private Line line;

	@Column(name = "departure_time", nullable = false)
	private LocalDateTime departureTime;

	@Column(name = "stop_time_minutes", nullable = false)
	private Short stopTimeMinutes;

	@Column(name = "default_direction", nullable = false)
	private Boolean defaultDirection = true;

	@Column(name = "is_direct", nullable = false)
	private Boolean isDirect = false;

	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.NAMED_ENUM)
	@Column(name = "schedule", nullable = false)
	private JourneySchedule schedule;

	protected Journey() {
	}

	public UUID getJourneyNumber() {
		return journeyNumber;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public Short getStopTimeMinutes() {
		return stopTimeMinutes;
	}

	public void setStopTimeMinutes(Short stopTimeMinutes) {
		this.stopTimeMinutes = stopTimeMinutes;
	}

	public Boolean getDefaultDirection() {
		return defaultDirection;
	}

	public void setDefaultDirection(Boolean defaultDirection) {
		this.defaultDirection = defaultDirection;
	}

	public Boolean getIsDirect() {
		return isDirect;
	}

	public void setIsDirect(Boolean isDirect) {
		this.isDirect = isDirect;
	}

	public JourneySchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(JourneySchedule schedule) {
		this.schedule = schedule;
	}
}
