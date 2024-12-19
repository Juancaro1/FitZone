package com.proyecto.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.modelos.Usuario;

import jakarta.servlet.http.HttpSession;


@Controller
public class ControladorPerfil {

    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession sesion, Model modelo) {
        //Para verificar el usuario
        if(sesion.getAttribute("usuario") == null){
            return "redirect:/login";
        }

    Usuario usuario = (Usuario) sesion.getAttribute("usuario");

    modelo.addAttribute("nombreUsuario", usuario.getNombre());
    modelo.addAttribute("apellidoUsuario", usuario.getApellido());
    return "perfil";
    }
}
