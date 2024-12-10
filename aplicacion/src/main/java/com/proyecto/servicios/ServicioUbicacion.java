
package com.proyecto.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelos.Ubicacion;
import com.proyecto.repositorios.RepositorioUbicacion;

@Service
public class servicioUbicacion {

    @Autowired 
    private RepositorioUbicacion repositorioUbicacion;

    public Ubicacion crearUbicacion(Ubicacion ubicacion, Long idUsuario){
        return repositorioUbicacion.save(ubicacion);
    }

    public List<Ubicacion> obtenerTodas(){
        return (List<Ubicacion>) this.repositorioUbicacion.findAll();
    }
    
    public Resena obtenerUbicacionPorId(Long id){
        return this.repositorioUbicacion.findById(id).orElse(null);
    }

    public Resena actualizarUbicacion(Long id, Resena actualizada){
        return this.repositorioUbicacion.save(ubicacion);
    }

    public void eliminarUbicacion(Long id){
        this.repositorioUbicacion.deleteById(id);
    }
}
