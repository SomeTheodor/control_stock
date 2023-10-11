package com.mobeats.api.repository;

import com.mobeats.api.model.MovimientoTipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovimientoTipoRepository extends JpaRepository<MovimientoTipo, Long> {}

