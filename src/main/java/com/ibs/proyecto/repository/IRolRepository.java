package com.ibs.proyecto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibs.proyecto.model.Role;

 @Repository
public interface IRolRepository extends CrudRepository<Role, Long>{

	//Role findAllById(Integer idRol);

}
