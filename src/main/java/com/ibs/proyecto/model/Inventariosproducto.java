package com.ibs.proyecto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the inventariosproductos database table.
 * 
 */
@Entity
@Table(name="inventariosproductos")
@NamedQuery(name="Inventariosproducto.findAll", query="SELECT i FROM Inventariosproducto i")
public class Inventariosproducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String evento;
	
	private String descripcion;

	private BigInteger cantidad;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idProducto")
	private Producto productos;
	
	@ManyToOne
	@JoinColumn(name="idInventario")
	private Inventario inventarios;

	public Inventariosproducto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigInteger getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigInteger cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProductos() {
		return this.productos;
	}

	public void setProductos(Producto productos) {
		this.productos = productos;
	}

	public Inventario getInventarios() {
		return inventarios;
	}

	public void setInventarios(Inventario inventarios) {
		this.inventarios = inventarios;
	}
	
}