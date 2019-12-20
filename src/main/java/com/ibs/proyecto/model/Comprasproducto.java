package com.ibs.proyecto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;

/**
 * The persistent class for the comprasproductos database table.
 * 
 */
@Entity
@Table(name = "comprasproductos")
@NamedQuery(name = "Comprasproducto.findAll", query = "SELECT c FROM Comprasproducto c")
public class Comprasproducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idComprasProductos;

	private BigInteger cantidad;

	private String estado;

	private float precioCompra;

	@ManyToOne(cascade = CascadeType.REMOVE, optional = false, fetch = FetchType.EAGER)
	private Compra compra;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Producto producto;

	public Comprasproducto() {
	}

	public Long getIdComprasProductos() {
		return this.idComprasProductos;
	}

	public void setIdComprasProductos(Long idComprasProductos) {
		this.idComprasProductos = idComprasProductos;
	}

	public BigInteger getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigInteger cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public float getPrecioCompra() {
		return this.precioCompra;
	}

	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
}