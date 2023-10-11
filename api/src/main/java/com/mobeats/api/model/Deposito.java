package com.mobeats.api.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

@Entity
@Table(name = "depositos")
@EntityListeners(AuditingEntityListener.class)
public class Deposito {
@Id // Anotación para marcar un campo como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia de generación de valores de clave primaria
    @Column(name = "id") // Nombre de la columna en la base de datos
    private Long id; // Campo de identificación

    @Column(name = "nombre", nullable = false)
    private String name;

    // Getters y setters para id y name

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Deposito{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}