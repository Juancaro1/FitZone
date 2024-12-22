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

    // Mostrar perfil
    @GetMapping("/perfil")
public String mostrarPerfil(HttpSession sesion, Model modelo) {
    String emailUsuario = (String) sesion.getAttribute("emailUsuario");

    if (emailUsuario == null) {
        return "redirect:/login"; // Si el usuario no está logueado, redirige al login
    }

    Usuario usuario = this.serviciosUsuarios.obtenerPorEmail(emailUsuario);
    if (usuario == null) {
        return "redirect:/login"; // Si el usuario no existe, redirige al login
    }

    List<String> preferencias = usuario.getPreferencias(); // Cargar las preferencias actualizadas
    modelo.addAttribute("usuario", usuario);
    modelo.addAttribute("preferencias", preferencias); // Pasar las preferencias al modelo

    return "perfil"; // Redirige a la vista del perfil
}


    // Editar perfil
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

    // Actualiza los campos del usuario con los datos del formulario
    usuarioSession.setNombre(usuario.getNombre());
    usuarioSession.setApellido(usuario.getApellido());
    usuarioSession.setSobreMi(usuario.getSobreMi());

    try {
        this.serviciosUsuarios.actualizarUsuario(usuarioSession);
    } catch (Exception e) {
        return "error"; // Maneja errores de actualización
    }

    // Actualiza los datos en la sesión
    sesion.setAttribute("emailUsuario", usuarioSession.getEmail());

    // Agrega los datos actualizados al modelo para que estén disponibles en la vista
    model.addAttribute("nombreUsuario", usuarioSession.getNombre());
    model.addAttribute("apellidoUsuario", usuarioSession.getApellido());
    model.addAttribute("sobreMiUsuario", usuarioSession.getSobreMi());

    // Redirige a la página del perfil con los datos actualizados
    return "perfil"; // Redirige a la vista del perfil
}


    // Mostrar preferencias
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

        return "preferencias"; // Nombre de la vista de preferencias
    }

    // Guardar preferencia
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

        return "redirect:/perfil"; // Redirige al perfil después de agregar la preferencia
    }

    // Eliminar preferencia
    // Eliminar preferencia
@PostMapping("/perfil/preferencia/eliminar")
public String eliminarPreferencia(HttpSession sesion, @RequestParam("preferencia") String preferencia) {
    String emailUsuario = (String) sesion.getAttribute("emailUsuario");

    if (emailUsuario == null) {
        return "redirect:/login"; // Si no está autenticado, redirige al login
    }

    Usuario usuario = this.serviciosUsuarios.obtenerPorEmail(emailUsuario);
    if (usuario == null) {
        return "redirect:/login"; 
    }

    // Elimina la preferencia del usuario
    this.serviciosUsuarios.eliminarPreferencia(usuario, preferencia);

    // Recargar el usuario actualizado después de eliminar la preferencia
    usuario = this.serviciosUsuarios.obtenerPorEmail(emailUsuario);

    // Agregar el usuario con las preferencias actualizadas al modelo
    sesion.setAttribute("usuario", usuario);

    return "redirect:/perfil"; // Redirige al perfil después de eliminar la preferencia
}

}
