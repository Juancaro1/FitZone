package com.proyecto.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.modelos.Ubicacion;

@Repository
public interface RepositorioUbicacion extends CrudRepository<Ubicacion, Long>{

}
