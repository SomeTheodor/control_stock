package com.mobeats.api.repository;

import com.mobeats.api.model.Movimiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {}

