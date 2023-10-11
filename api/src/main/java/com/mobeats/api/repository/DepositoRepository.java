package com.mobeats.api.repository;

import com.mobeats.api.model.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {}
