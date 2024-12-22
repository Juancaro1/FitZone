package com.proyecto.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

        modelo.addAttribute("nombreUsuario", usuario.getNombre());
        modelo.addAttribute("apellidoUsuario", usuario.getApellido());
        modelo.addAttribute("sobreMiUsuario", usuario.getSobreMi());
        modelo.addAttribute("preferencias", usuario.getPreferencias());

        return "perfil";
    }

    @PostMapping("/perfil/editar")
    @ResponseBody
    public ResponseEntity<Map<String, String>> editarPerfil(
            @ModelAttribute Usuario usuario,
            HttpSession sesion) {

        String emailUsuario = (String) sesion.getAttribute("emailUsuario");

        if (emailUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("success", "false", "message", "Usuario no autorizado"));
        }

        Usuario usuarioSession = this.serviciosUsuarios.obtenerPorEmail(emailUsuario);
        
        if (usuarioSession == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("success", "false", "message", "Usuario no encontrado"));
        }

        usuarioSession.setNombre(usuario.getNombre());
        usuarioSession.setApellido(usuario.getApellido());
        usuarioSession.setSobreMi(usuario.getSobreMi());

        try {
            this.serviciosUsuarios.actualizarUsuario(usuarioSession);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", "false", "message", "Error al actualizar los datos en la base de datos"));
        }
        sesion.setAttribute("emailUsuario", usuarioSession.getEmail());

        return ResponseEntity.ok(Map.of("success", "true", "message", "Perfil actualizado correctamente"));
    }

    @GetMapping("/perfil/preferencias")
    public String mostrarPreferencias(Model modelo, HttpSession sesion) {
        String emailUsuario = (String) sesion.getAttribute("emailUsuario");

        if (emailUsuario == null) {
            return "redirect:/login";
        }

        Usuario usuario = this.serviciosUsuarios.obtenerPorEmail(emailUsuario);
        if (usuario == null) {
            return "redirect:/login";
        }

        List<String> preferencias = usuario.getPreferencias(); 
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
    @ResponseBody
    public ResponseEntity<Void> eliminarPreferencia(HttpSession sesion, @RequestParam("preferencia") String preferencia) {
    String emailUsuario = (String) sesion.getAttribute("emailUsuario");

    if (emailUsuario == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
    }

    Usuario usuario = this.serviciosUsuarios.obtenerPorEmail(emailUsuario);
    if (usuario == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
    }

    boolean eliminada = usuario.getPreferencias().remove(preferencia);

    if (eliminada) {
        this.serviciosUsuarios.actualizarPreferencia(usuario);
        sesion.setAttribute("usuario", usuario);
        return ResponseEntity.ok().build();
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

}
