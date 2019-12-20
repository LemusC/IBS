package com.ibs.proyecto.service;

import java.util.List;

import javax.transaction.Transactional;

import com.ibs.proyecto.repository.ICompraRepository;
import com.ibs.proyecto.repository.IProductoRepository;
import com.ibs.proyecto.repository.IProveedorRepository;
import com.ibs.proyecto.model.Compra;
import com.ibs.proyecto.model.Producto;
import com.ibs.proyecto.model.Proveedore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CompraService
 */
@Service
public class CompraService {

    @Autowired
    IProductoRepository rProducto;

    @Autowired
    IProveedorRepository rProveedor;

    @Autowired
    ICompraRepository rCompra;

    @Transactional
    public List<Compra> getAllCompra() {
        return (List<Compra>) rCompra.findAll();
    }

    @Transactional
    public Boolean save(Compra entity) {
        try {
            rCompra.save(entity);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public List<Proveedore> getAllProveedores() {
        return (List<Proveedore>) rProveedor.findAll();
    }

    @Transactional
    public List<Producto> getAllProductos() {
        return (List<Producto>) rProducto.findAll();
    }

    @Transactional
    public Proveedore getProveedor(Long id) {
        return rProveedor.findById(id).get();
    }

    @Transactional
    public Producto getProducto(Long id) {
        return rProducto.findById(id).get();
    }
}