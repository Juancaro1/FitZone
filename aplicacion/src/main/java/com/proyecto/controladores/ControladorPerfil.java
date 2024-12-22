package com.proyecto.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    if (emailUsuario == null) {
        return "redirect:/login";
    }

    Usuario usuario = this.serviciosUsuarios.obtenerPorEmail(emailUsuario);
    if (usuario == null) {
        return "redirect:/login";
    }

    List<String> preferencias = usuario.getPreferencias();
    modelo.addAttribute("usuario", usuario);
    modelo.addAttribute("preferencias", preferencias);

    return "perfil"; 
}



    @PostMapping("/perfil/editar")
public String editarPerfil(@ModelAttribute Usuario usuario, HttpSession sesion, Model model) {
    String emailUsuario = (String) sesion.getAttribute("emailUsuario");

    if (emailUsuario == null) {
        return "redirect:/login";
    }

    Usuario usuarioSession = this.serviciosUsuarios.obtenerPorEmail(emailUsuario);

    if (usuarioSession == null) {
        return "redirect:/login";
    }

    usuarioSession.setNombre(usuario.getNombre());
    usuarioSession.setApellido(usuario.getApellido());
    usuarioSession.setSobreMi(usuario.getSobreMi());

    try {
        this.serviciosUsuarios.actualizarUsuario(usuarioSession);
    } catch (Exception e) {
        return "error"; 
    }

    sesion.setAttribute("emailUsuario", usuarioSession.getEmail());


    model.addAttribute("nombreUsuario", usuarioSession.getNombre());
    model.addAttribute("apellidoUsuario", usuarioSession.getApellido());
    model.addAttribute("sobreMiUsuario", usuarioSession.getSobreMi());

    return "perfil";
}


    @GetMapping("/perfil/preferencias")
    public String mostrarPreferencias(@RequestParam ("preferencia") String preferencia,Model modelo, HttpSession sesion) {
        String emailUsuario = (String) sesion.getAttribute("emailUsuario");

        if (emailUsuario == null) {
            return "redirect:/login";
        }

        Usuario usuario = this.serviciosUsuarios.obtenerPorEmail(emailUsuario);
        if (usuario == null) {
            return "redirect:/login";
        }

        List<String> preferencias = usuario.getPreferencias(); 
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("preferencias", preferencias); 

        return "preferencias";
    }

    @PostMapping("/perfil/preferencias/guardar/")
    public String guardarPreferencia(HttpSession sesion, @RequestParam("preferencia") String preferencia) {
        String emailUsuario = (String) sesion.getAttribute("emailUsuario");

        if (emailUsuario == null) {
            return "redirect:/login";
        }

        Usuario usuario = this.serviciosUsuarios.obtenerPorEmail(emailUsuario);
        if (usuario == null) {
            return "redirect:/login"; 
        }

        usuario.addPreferencia(preferencia);

        this.serviciosUsuarios.actualizarPreferencia(usuario);

        sesion.setAttribute("usuario", usuario);

        return "redirect:/perfil";
    }

@PostMapping("/perfil/preferencia/eliminar")
public String eliminarPreferencia(HttpSession sesion, @RequestParam("preferencia") String preferencia) {
    String emailUsuario = (String) sesion.getAttribute("emailUsuario");

    if (emailUsuario == null) {
        return "redirect:/login"; 
    }

    Usuario usuario = this.serviciosUsuarios.obtenerPorEmail(emailUsuario);
    if (usuario == null) {
        return "redirect:/login"; 
    }


    this.serviciosUsuarios.eliminarPreferencia(usuario, preferencia);

    usuario = this.serviciosUsuarios.obtenerPorEmail(emailUsuario);

    sesion.setAttribute("usuario", usuario);

    return "redirect:/perfil";
}

}
