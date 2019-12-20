package com.ibs.proyecto.repository;

import org.springframework.data.repository.CrudRepository;

import com.ibs.proyecto.model.Usuario;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long>{
	
	Usuario findByUser(String user);
}
