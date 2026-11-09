package com.sncfvoyageurs.correspondances.model;

import java.math.BigDecimal;
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
@Table(name = "line_stop")
public class LineStop {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "line_stop_id")
	private UUID lineStopId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "line", nullable = false)
	private Line line;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "station", nullable = false)
	private Station station;

	@Column(name = "position_on_line", nullable = false)
	private Short positionOnLine;

	@Column(name = "distance_from_next", precision = 7, scale = 3)
	private BigDecimal distanceFromNext;

	protected LineStop() {
	}

	public UUID getLineStopId() {
		return lineStopId;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public Short getPositionOnLine() {
		return positionOnLine;
	}

	public void setPositionOnLine(Short positionOnLine) {
		this.positionOnLine = positionOnLine;
	}

	public BigDecimal getDistanceFromNext() {
		return distanceFromNext;
	}

	public void setDistanceFromNext(BigDecimal distanceFromNext) {
		this.distanceFromNext = distanceFromNext;
	}
}
