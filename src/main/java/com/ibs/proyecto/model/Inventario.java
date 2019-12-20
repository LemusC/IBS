package com.ibs.proyecto.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the inventarios database table.
 * 
 */
@Entity
@Table(name = "inventarios")
@NamedQuery(name = "InventarioController.findAll", query = "SELECT i FROM Inventario i")
public class Inventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInventario;

	private Timestamp fecha;

	// bi-directional many-to-one association to Compra
	/*@ManyToOne
	@JoinColumn(name = "idCompra")
	private Compra compras;

	// bi-directional many-to-one association to Venta
	@ManyToOne
	@JoinColumn(name = "idVenta")
	private Venta ventas;*/

	// bi-directional many-to-one association to Inventariosproducto
	/*@ManyToOne
	@JoinColumn(name = "idInventariosProductos")
	private Inventariosproducto inventariosProductos;*/

	//bi-directional many-to-one association to Inventariosproducto
	@OneToMany(mappedBy = "inventarios", fetch = FetchType.LAZY)
	private List<Inventariosproducto> inventariosproductos;

	public Inventario() {
	}

	public Long getIdInventario() {
		return this.idInventario;
	}

	public void setIdInventario(Long idInventario) {
		this.idInventario = idInventario;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public List<Inventariosproducto> getInventariosproductos() {
		return inventariosproductos;
	}

	public void setInventariosproductos(List<Inventariosproducto> inventariosproductos) {
		this.inventariosproductos = inventariosproductos;
	}

}