package com.sncfvoyageurs.correspondances.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sncfvoyageurs.correspondances.dto.JourneyDto;
import com.sncfvoyageurs.correspondances.model.JourneySchedule;
import com.sncfvoyageurs.correspondances.repository.JourneyRepository;
import com.sncfvoyageurs.correspondances.repository.LineRepository;

@Service
@Transactional(readOnly = true)
public class JourneyService {

	private final JourneyRepository journeyRepository;
	private final LineRepository lineRepository;

	public JourneyService(JourneyRepository journeyRepository, LineRepository lineRepository) {
		this.journeyRepository = journeyRepository;
		this.lineRepository = lineRepository;
	}

	public List<JourneyDto> findRunningOn(UUID lineNumber, LocalDate date) {
		if (!lineRepository.existsById(lineNumber)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Line not found");
		}
		return journeyRepository.findByLineAndSchedules(lineNumber, schedulesFor(date)).stream()
				.map(JourneyDto::from)
				.toList();
	}

	// SPECIAL_DAYS is never returned: the model does not store which dates are special
	private List<JourneySchedule> schedulesFor(LocalDate date) {
		DayOfWeek day = date.getDayOfWeek();
		boolean isWeekend = day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
		JourneySchedule dayType = isWeekend ? JourneySchedule.WEEKEND : JourneySchedule.WEEKDAYS;
		return List.of(JourneySchedule.EVERY_DAY, dayType);
	}
}
