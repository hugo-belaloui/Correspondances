package com.sncfvoyageurs.correspondances.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sncfvoyageurs.correspondances.model.Journey;

public interface JourneyRepository extends JpaRepository<Journey, UUID> {
}
