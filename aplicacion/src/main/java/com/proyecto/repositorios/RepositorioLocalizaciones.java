package com.proyecto.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.modelos.Localizacion;

@Repository
public interface RepositorioLocalizaciones extends CrudRepository<Localizacion, Long>{

}
