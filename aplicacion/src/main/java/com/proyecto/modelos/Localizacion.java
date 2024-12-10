package com.proyecto.modelos;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "localizacion")
    private List<Resena> resenas;

    public Localizacion(Long id,  String nombre, String direccion, List<Resena> resenas) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.resenas = resenas;
    }
    public Localizacion() {
        this.id = 0l;
        this.nombre = "";
        this.direccion = "";
        this.resenas = null;
    }

    public List<Resena> getResenas() {
        return this.resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
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
