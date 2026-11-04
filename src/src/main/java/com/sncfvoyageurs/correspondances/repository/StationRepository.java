package com.sncfvoyageurs.correspondances.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sncfvoyageurs.correspondances.model.Station;

public interface StationRepository extends JpaRepository<Station, UUID> {
}
