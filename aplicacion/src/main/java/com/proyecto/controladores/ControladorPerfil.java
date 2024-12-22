package com.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.modelos.Usuario;
import com.proyecto.servicios.ServiciosUsuarios;

import jakarta.servlet.http.HttpSession;


@Controller
public class ControladorPerfil {

    @Autowired
    private ServiciosUsuarios serviciosUsuarios;

    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession sesion, Model modelo) {
        
        String emailUsuario = (String) sesion.getAttribute("emailUsuario");

        if(emailUsuario == null){
            return "redirect:/login";
        }

        Usuario usuario = this.serviciosUsuarios.obtenerPorEmail(emailUsuario);
        if(usuario == null){
            return "redirect:/login";
        }

        modelo.addAttribute("nombreUsuario", usuario.getNombre());
        modelo.addAttribute("apellidoUsuario", usuario.getApellido());
        modelo.addAttribute("sobreMiUsuario", usuario.getSobremi());
        modelo.addAttribute("preferencia", usuario.getPreferencia());

        return "perfil.html";
    }

    @PostMapping("/perfil/editar")
    public String editarPerfil(@ModelAttribute Usuario usuario, HttpSession sesion){
        Usuario usuarioSession = (Usuario) sesion.getAttribute("usuario");
        if(usuarioSession == null){
            return "redirect:/login";
        }
        usuarioSession.setNombre(usuario.getNombre());
        usuarioSession.setApellido(usuario.getApellido());
        usuarioSession.setSobremi(usuario.getSobremi());

        this.serviciosUsuarios.actualizarUsuario(usuarioSession);

        sesion.setAttribute("usuario", usuarioSession);
        return "redirect:/perfil";
    }
}
