package com.proyecto.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "localizaciones")
public class Localizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Debes ingresar un nombre a la localización.")
    private String nombre;

    @NotBlank(message = "Debes ingresar una dirección para la localización.")
    private String direccion;

    // @OnetoMany o @ManytoOne
    private Resena comentarios;

    public Localizacion(Long id,  String nombre, String direccion, Resena comentarios) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.comentarios = comentarios;
    }
    public Localizacion() {
        this.id = 0l;
        this.nombre = "";
        this.direccion = "";
        this.comentarios = null;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Resena getComentarios() {
        return comentarios;
    }
    public void setComentarios(Resena comentarios) {
        this.comentarios = comentarios;
    }
    
    


}
