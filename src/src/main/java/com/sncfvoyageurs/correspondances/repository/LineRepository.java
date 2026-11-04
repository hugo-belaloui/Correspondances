package com.sncfvoyageurs.correspondances.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sncfvoyageurs.correspondances.model.Line;

public interface LineRepository extends JpaRepository<Line, UUID> {
}
