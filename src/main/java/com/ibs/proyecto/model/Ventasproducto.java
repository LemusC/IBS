package com.ibs.proyecto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the ventasproductos database table.
 * 
 */
@Entity
@Table(name="ventasproductos")
@NamedQuery(name="Ventasproducto.findAll", query="SELECT v FROM Ventasproducto v")
public class Ventasproducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idVentasproductos;

	private BigInteger cantidad;

	private float descuento;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idProducto")
	private Producto productos;

	//bi-directional many-to-one association to Venta
	@ManyToOne
	@JoinColumn(name="idVenta")
	private Venta ventas;

	public Ventasproducto() {
	}

	public Long getIdVentasproductos() {
		return this.idVentasproductos;
	}

	public void setIdVentasproductos(Long idVentasproductos) {
		this.idVentasproductos = idVentasproductos;
	}

	public BigInteger getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigInteger cantidad) {
		this.cantidad = cantidad;
	}

	public float getDescuento() {
		return this.descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public Producto getProductos() {
		return this.productos;
	}

	public void setProductos(Producto productos) {
		this.productos = productos;
	}

	public Venta getVentas() {
		return this.ventas;
	}

	public void setVentas(Venta ventas) {
		this.ventas = ventas;
	}

}