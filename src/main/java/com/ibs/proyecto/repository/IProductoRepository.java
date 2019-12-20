package com.ibs.proyecto.repository;

import org.springframework.data.repository.CrudRepository;

import com.ibs.proyecto.model.Producto;

public interface IProductoRepository extends CrudRepository<Producto, Long>{

}
