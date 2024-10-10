package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "sedes")
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String departamento;

    @Column(nullable = false)
    private String distrito;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private Boolean disponibilidad;

    // Constructor vacío
    public Sede() {}

    // Constructor completo
    public Sede(int id, String departamento, String distrito, String direccion, Boolean disponibilidad) {

        this.id = id;


        this.departamento = departamento;
        this.distrito = distrito;
        this.direccion = direccion;
        this.disponibilidad = disponibilidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
