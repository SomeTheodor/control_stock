package com.mobeats.api.repository;

import com.mobeats.api.model.ProductoDeposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mobeats.api.model.Producto;
import com.mobeats.api.model.Deposito;


@Repository
public interface ProductoDepositoRepository extends JpaRepository<ProductoDeposito, Long> {
    ProductoDeposito findByProductoAndDeposito(Producto producto, Deposito deposito);
}