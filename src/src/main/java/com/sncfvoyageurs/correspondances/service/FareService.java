package com.sncfvoyageurs.correspondances.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sncfvoyageurs.correspondances.dto.FareDto;
import com.sncfvoyageurs.correspondances.repository.FareRepository;

@Service
@Transactional(readOnly = true)
public class FareService {

	private final FareRepository fareRepository;

	public FareService(FareRepository fareRepository) {
		this.fareRepository = fareRepository;
	}

	public List<FareDto> findValidOn(LocalDate date) {
		return fareRepository.findValidOn(date).stream()
				.map(FareDto::from)
				.toList();
	}
}
