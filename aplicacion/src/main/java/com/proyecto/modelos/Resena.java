package com.proyecto.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "resenas")
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Debes ingresar al menos la puntuación.")
    private int puntuacion;

    private String comentario;
    // más atributos por ver según trello.

    public Resena(Long id, @NotBlank(message = "Debes ingresar al menos la puntuación.") int puntuacion,
            String comentario) {
        this.id = id;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }
    public Resena() {
        this.id = 0l;
        this.puntuacion = 0;
        this.comentario = "";
}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }



}
