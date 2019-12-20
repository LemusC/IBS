package com.ibs.proyecto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.List;



/**
 * The persistent class for the ventas database table.
 * 
 */
@Entity
@Table(name="ventas")
@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String estado;

	private Timestamp fechaVenta;

	private String nombre;

	private BigInteger numeroFactura;

	private String tipoVenta;

	private float totalVenta;

	//bi-directional many-to-one association to InventarioController
	/*@OneToMany(mappedBy="ventas", fetch=FetchType.LAZY)
	private List<Inventario> inventarios;*/

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente clientes;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuarios;

	//bi-directional many-to-one association to Ventasproducto
	@OneToMany(mappedBy="ventas", fetch=FetchType.LAZY)
	private List<Ventasproducto> ventasproductos;

	public Venta() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaVenta() {
		return this.fechaVenta;
	}

	public void setFechaVenta(Timestamp fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getNumeroFactura() {
		return this.numeroFactura;
	}

	public void setNumeroFactura(BigInteger numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getTipoVenta() {
		return this.tipoVenta;
	}

	public void setTipoVenta(String tipoVenta) {
		this.tipoVenta = tipoVenta;
	}

	public float getTotalVenta() {
		return this.totalVenta;
	}

	public void setTotalVenta(float totalVenta) {
		this.totalVenta = totalVenta;
	}

	public Cliente getClientes() {
		return this.clientes;
	}

	public void setClientes(Cliente clientes) {
		this.clientes = clientes;
	}

	public Usuario getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuario usuarios) {
		this.usuarios = usuarios;
	}

	public List<Ventasproducto> getVentasproductos() {
		return this.ventasproductos;
	}

	public void setVentasproductos(List<Ventasproducto> ventasproductos) {
		this.ventasproductos = ventasproductos;
	}

	public Ventasproducto addVentasproducto(Ventasproducto ventasproducto) {
		getVentasproductos().add(ventasproducto);
		ventasproducto.setVentas(this);

		return ventasproducto;
	}

	public Ventasproducto removeVentasproducto(Ventasproducto ventasproducto) {
		getVentasproductos().remove(ventasproducto);
		ventasproducto.setVentas(null);

		return ventasproducto;
	}

}