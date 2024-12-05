package com.proyecto.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "Este campo es obligatorio.")
    @Size(min = 3, message = "Debe contener al menos 3 caracteres.")
    private String nombre;

    @NotBlank(message= "Este campo es obligatorio.")
    @Size(min = 3, message = "Debe contener al menos 3 caracteres.")
    private String apellido;

    @Column(unique = true)
    @NotBlank(message= "Este campo es obligatorio.")
    @Email(message= "Debe ser un correo electronico valido.")
    private String email;

    @NotBlank(message= "Este campo es obligatorio.")
    @Size(min = 8, message= "Debe contener al menos 8 caracteres.")
    private String clave;

    @Transient
    private String confirmarClave;


    public Usuario(Long id, String nombre, String apellido, String email, String clave, String confirmarClave) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.clave = clave;
        this.confirmarClave = confirmarClave;
    }

    public Usuario() {
        this.id = 0l;
        this.nombre = "";
        this.apellido = "";
        this.email = "";
        this.clave = "";
        this.confirmarClave = "";
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getConfirmarClave() {
        return this.confirmarClave;
    }

    public void setConfirmarClave(String confirmarClave) {
        this.confirmarClave = confirmarClave;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", email='" + getEmail() + "'" +
            ", clave='" + getClave() + "'" +
            ", confirmarClave='" + getConfirmarClave() + "'" +
            "}";
    }

}
