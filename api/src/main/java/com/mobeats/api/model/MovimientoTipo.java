package com.mobeats.api.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

@Entity
@Table(name = "movimientos_tipo")
@EntityListeners(AuditingEntityListener.class)
public class MovimientoTipo{

    @Id // Anotación para marcar un campo como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia de generación de valores de clave primaria
    @Column(name = "id") // Nombre de la columna en la base de datos
    private Long id; // Campo de identificación

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "descripcion", nullable = false)
    private String description;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
    @Override
    public String toString() {
        return "MovimientoTipo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' + 
                '}';
    }
}